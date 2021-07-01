package main.java.server.types;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Random;

import main.java.server.util.Filer;

class Account implements Serializable {
    private String id, passwordHash;
    private long balance;
    private boolean isFavorite;
    private User owner;
    private String alias;

    /**
     * @param id
     * @param password
     * @param balance
     * @param isFavorite
     * @param alias
     */
    public Account(User owner, String password, boolean isFavorite, String alias) {
        this.balance = 0;
        this.isFavorite = isFavorite;
        this.alias = alias;
        this.owner = owner;
        Random r = new Random();
        this.id = owner.getMelliCode() + r.nextInt();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        passwordHash = new String(messageDigest.digest());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

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

    public void save(){
        Filer<Account> filer = new Filer<Account>();
    }
}
