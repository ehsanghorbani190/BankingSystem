package server.types;

import java.security.MessageDigest;
import java.util.ArrayList;

import server.util.Filer;
import server.util.Identifiable;

public class User implements Identifiable {
    private String melliCode;
    private String name, passwordHash, email, phone;
    private ArrayList<Account> accounts, fAccounts;

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
            accounts = new ArrayList<Account>();
            fAccounts = new ArrayList<Account>();
            setPassword(password);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // NOTE Getters
    /**
     * @return the accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
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

    public Account getAccount(String id) {
        Account temp = Filer.<Account>read(id, Account.class);
        if(temp == null || !accounts.contains(temp) ) return null;
        return accounts.get(accounts.indexOf(temp));
    }

    // NOTE Setters
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

    public void openAccount(String password, boolean isFavorite, String alias) {
        Account temp = new Account(this, password, isFavorite, alias);
        accounts.add(temp);
        if (isFavorite)
            fAccounts.add(temp);
        update();
    }

    public void deleteAccount(String id , String password , String toID) {
        Account temp = getAccount(id);
        if(temp == null || !temp.login(password))return;
        temp = accounts.get(accounts.indexOf(temp));
        if (temp.getFavorite())
            fAccounts.remove(temp);
        accounts.remove(temp);
        temp.delete(password, toID);
        update();
    }

    public void addAccountToFavorites(String id) {
        Account temp = getAccount(id);
        if(temp == null) return;
        temp.setFavorite(true);
        if(!fAccounts.contains(temp)) fAccounts.add(temp);
        update();
    }

    public void addAliasTo(String id, String alias) {
        Account temp = getAccount(id);
        if(temp == null) return;
        temp.setAlias(alias);
        update();
    }

    public boolean transfer(String from, String password, String to, long val) {
        Account temp = getAccount(from);
        if(temp == null  || !temp.login(password)) return false;
        boolean i = temp.transfer(password, to, val);
        update();
        return i;

    }

    // NOTE File methods
    public boolean delete() {
        return Filer.delete(getUniqueID(), User.class);
    }

    public void update() {
        Filer.<User>write(this);
    }

    @Override
    public String getUniqueID() {
        return melliCode;
    }
}
