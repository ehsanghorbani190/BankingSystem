package server.types;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class Account implements Serializable {
    private String id, passwordHash;
    private long balance;
    private boolean isFavorite;
    private User owner;
    private String alias;

    public Account(User owner, String password, boolean isFavorite, String alias) throws NoSuchAlgorithmException {
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
    
   public boolean getFavorite() {
       return isFavorite;
   }
   
   /**
    * @param isFavorite the isFavorite to set
    */
   public void setFavorite(boolean isFavorite) {
       this.isFavorite = isFavorite;
   }
   /**
    * @param alias the alias to set
    */
   public void setAlias(String alias) {
       this.alias = alias;
   }
}
