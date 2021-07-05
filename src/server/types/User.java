package server.types;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import server.util.Filer;
import server.util.Identifiable;

public class User implements Identifiable {
    private static final long serialVersionUID = 1L;
    private String melliCode;
    private String name, passwordHash, email, phone;
    private int aCount;

    /**
     * @param melliCode
     * @param name
     * @param password
     * @param email
     * @param phone
     */
    public User(String melliCode, String name, String password, String email, String phone) {
        try {
            this.melliCode = melliCode;
            this.name = name;
            this.email = email;
            this.phone = phone;
            aCount = 0;
            setPassword(password);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // SECTION - Getters
    /**
     * @return the accounts
     */
    public ArrayList<Account> getAccounts() {
        ArrayList<Account> res = new ArrayList<Account>();
        for (int i = 0; i < aCount; i++) {
            Account temp = Filer.<Account>read(getUniqueID() + i, Account.class);
            if (temp != null) {
                res.add(temp);
            }
        }
        return res;
    }

    public ArrayList<Account> getFavoriteAccounts() {
        ArrayList<Account> res = new ArrayList<Account>();
        for (int i = 0; i < aCount; i++) {
            Account temp = Filer.<Account>read(getUniqueID() + i, Account.class);
            if (temp != null && temp.getFavorite()) {
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    public Account getAccount(int id) {
        return Filer.<Account>read(getUniqueID() + id, Account.class);
    }
    // !SECTION

    // SECTION Setters
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
        update();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
        update();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
        update();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            passwordHash = new String(messageDigest.digest());
            update();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // !SECTION

    // SECTION - Account methods
    public Account openAccount(String password, boolean isFavorite, String alias) {
        Account temp = new Account(getUniqueID() + aCount, password, isFavorite, alias);
        aCount++;
        update();
        return temp;
    }

    public void deleteAccount(int id, String password, String toID) {
        Account temp = getAccount(id);
        if (temp == null || !temp.login(password))
            return;
        temp.delete(password, toID);
    }

    public void addAccountToFavorites(int id) {
        Account temp = getAccount(id);
        if (temp == null)
            return;
        temp.setFavorite(true);
    }

    public void addAliasTo(int id, String alias) {
        Account temp = getAccount(id);
        if (temp == null)
            return;
        temp.setAlias(alias);
    }

    // !SECTION
    public boolean transfer(int from, String password, String to, long val) {
        Account temp = getAccount(from);
        if (temp == null || !temp.login(password))
            return false;
        return temp.transfer(password, to, val);

    }

    public boolean withdraw(int id, long val) {
        Account temp = getAccount(id);
        if (temp == null)
            return false;
        return temp.getMoney(val);
    }

    public boolean deposit(int id, long val) {
        Account temp = getAccount(id);
        if (temp == null)
            return false;
        return temp.addMoney(val);
    }

    public boolean payBill(int id, String code, String payCode) {
        Account temp = getAccount(id);
        if (temp == null)
            return false;
        return temp.payBill(code, payCode);
    }

    public void getLoan(int id, long val, int periodInDays) {
        Account temp = getAccount(id);
        if (temp == null)
            return;
        temp.addMoney(val);
        Timer timer = new Timer();
        timer.schedule(new PayLoanDebts(temp, val, timer), 0, periodInDays * 24 * 60 * 60 / val);
    }

    // NOTE File methods
    public synchronized boolean delete() {
        return Filer.delete(getUniqueID(), User.class);
    }

    public synchronized void update() {
        Filer.<User>write(this);
    }

    @Override
    public String getUniqueID() {
        return melliCode;
    }

    public static User login(String id, String password) {
        User user = Filer.<User>read(id, User.class);
        if (user == null)
            return user;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            String ph = new String(messageDigest.digest());
            if (user.getPasswordHash().equals(ph))
                return user;
            else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

class PayLoanDebts extends TimerTask {
    private Account account;
    private long count = 0;
    private long times;
    private Timer timer;

    public PayLoanDebts(Account account, long val, Timer timer) {
        this.account = account;
        times = val / 1000;
        this.timer = timer;
    }

    @Override
    public void run() {
        if (count == times - 1) {
            this.cancel();
            timer.cancel();
            timer.purge();
        }

        boolean i = account.getMoney(1000);
        if (i)
            count++;
    }

}