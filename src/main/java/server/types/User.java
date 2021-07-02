package server.types;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import server.util.Filer;
import server.util.Identifiable;

public class User implements Serializable, Identifiable {
    private String melliCode, name, passwordHash, email, phone;
    private ArrayList<Account> accounts, fAccounts;
    private Filer<User> filer;

    /**
     * @param melliCode
     * @param name
     * @param password
     * @param email
     * @param phone
     * @throws NoSuchAlgorithmException
     */
    public User(String melliCode, String name, String password, String email, String phone)
            throws NoSuchAlgorithmException {
        this.melliCode = melliCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        accounts = new ArrayList<Account>();
        fAccounts = new ArrayList<Account>();
        setPassword(password);
        filer = new Filer<User>(this);
        filer.write();
    }

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
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the melliCode
     */
    public String getMelliCode() {
        return melliCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            passwordHash = new String(messageDigest.digest());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getUniqueID() {
        return melliCode;
    }

    public void openAccount(String password, boolean isFavorite, String alias) {
        accounts.add(new Account(this, password, isFavorite, alias));
    }

    public void deleteAccount(Account account) {
        if (account.getFavorite())
            fAccounts.remove(account);
        accounts.remove(account);
        account.delete();
    }

    public void addAccountToFavorites(Account account) {
        account.setFavorite(true);
        fAccounts.add(account);
    }

    public void addAliasTo(Account account, String alias) {
        account.setAlias(alias);
    }

    public boolean transfer(Account from, Account to, long val) {
        return from.transfer(to, val);
    }

}
