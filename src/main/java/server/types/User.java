package main.java.server.types;
 
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;

class User implements Serializable {
    private String melliCode, name, passwordHash, email, phone;
    private ArrayList<Account> accounts;

    /**
     * @param melliCode
     * @param name
     * @param password
     * @param email
     * @param phone
     */
    public User(String melliCode, String name, String password, String email, String phone) {
        this.melliCode = melliCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        accounts = new ArrayList<Account>();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        passwordHash = new String(messageDigest.digest());
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
}
