package server.types;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

import server.util.Filer;
import server.util.Identifiable;

public class Account implements Identifiable {
    private static final long serialVersionUID = 2L;
    private String id;
    private String passwordHash;
    private long balance;
    private boolean isFavorite;
    private User owner;
    private String alias;
    private ArrayList<Transaction> transactions;

    public Account(User owner, String password, boolean isFavorite, String alias) {
        try {
            this.balance = 0;
            this.isFavorite = isFavorite;
            this.alias = alias;
            this.owner = owner;
            Random r = new Random();
            int rand = r.nextInt();
            if (rand < 0) {
                rand *= -1;
            }
            this.id = owner.getUniqueID() + rand;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            passwordHash = new String(messageDigest.digest());
            transactions = new ArrayList<Transaction>();
            update();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean login(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            String hash = new String(messageDigest.digest());
            return hash.equals(passwordHash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // NOTE Setters
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
            e.printStackTrace();
        }
    }

    /**
     * @param isFavorite the isFavorite to set
     */
    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
        update();
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
        update();
    }

    // NOTE Getters
    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @return the balance
     */
    public long getBalance() {
        return balance;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return the password
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    /**
     * @return the transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // NOTE File methods
    public boolean delete(String password, String toID) {
        if (login(password)) {
            if (getBalance() != 0)
                transfer(password, toID, balance);
            return Filer.delete(getUniqueID(), Account.class);
        } else
            return false;
    }

    public void update() {
        Filer.<Account>write(this);
    }

    // NOTE Money methods
    public boolean addMoney(long val) {
        if (val < 0)
            return false;
        balance += val;
        transactions.add(new Transaction(id, val, "addMoney"));
        update();
        return true;
    }

    public boolean getMoney(long val) {
        if (val > balance || val < 0)
            return false;
        balance -= val;
        transactions.add(new Transaction(id, val, "getMoney"));
        update();
        return true;
    }

    public boolean transfer(String password, String toID, long val) {
        Account to = Filer.<Account>read(toID, Account.class);
        if (to == null || val < 0 || val > balance || !login(password))
            return false;
        to.balance += val;
        balance -= val;
        transactions.add(new Transaction(id, val, "move money to " + to.getUniqueID()));
        to.transactions.add(new Transaction(to.getUniqueID(), val, "got money from " + id));
        to.update();
        update();
        return true;
    }

    public boolean payBill(String code, String payCode) {
        if (code == null || payCode == null || code.length() != 13 || payCode.length() != 8)
            return false;
        balance -= (code.hashCode() + payCode.hashCode()) / 100;
        transactions.add(new Transaction(id, (code.hashCode() + payCode.hashCode()) / 100,
                "pay Bill , code: " + code + " , payCode: " + payCode));
        update();
        return true;
    }

    @Override
    public String getUniqueID() {
        return id;
    }

}
