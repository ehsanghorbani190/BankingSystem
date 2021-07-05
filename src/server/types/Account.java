package server.types;

import java.security.MessageDigest;
import java.util.ArrayList;

import server.util.Filer;
import server.util.Identifiable;

public class Account implements Identifiable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String passwordHash;
    private long balance;
    private boolean isFavorite;
    private String alias;
    private int tCount;

    public Account(String id, String password, boolean isFavorite, String alias) {
        try {
            this.balance = 10000;
            this.isFavorite = isFavorite;
            this.alias = alias;
            this.id = id;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            passwordHash = new String(messageDigest.digest());
            tCount = 0;
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

    // SECTION - Setters
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
    // !SECTION

    // SECTION - Getters
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
        ArrayList<Transaction> res = new ArrayList<Transaction>();
        for (int i = 0; i < tCount; i++) {
            res.add(Filer.<Transaction>read(getUniqueID() + i, Transaction.class));
        }
        return res;
    }

    public Transaction getTransaction(int id) {
        return Filer.<Transaction>read(getUniqueID() + id, Transaction.class);
    }

    // !SECTION
    // NOTE File methods
    public synchronized boolean delete(String password, String toID) {
        if (login(password)) {
            if (getBalance() != 0)
                transfer(password, toID, balance);
            return Filer.delete(getUniqueID(), Account.class);
        } else
            return false;
    }

    public synchronized void update() {
        Filer.<Account>write(this);
    }

    // SECTION - Money methods
    public synchronized boolean addMoney(long val) {
        if (val < 0)
            return false;
        balance += val;
        new Transaction(id + tCount, val, "addMoney");
        tCount++;
        update();
        return true;
    }

    public synchronized boolean getMoney(long val) {
        if (val > balance || val < 0)
            return false;
        balance -= val;
        new Transaction(id + tCount, val, "getMoney");
        tCount++;
        update();
        return true;
    }

    public synchronized boolean transfer(String password, String toID, long val) {
        Account to = Filer.<Account>read(toID, Account.class);
        if (to == null || val < 0 || val > balance || !login(password))
            return false;
        to.balance += val;
        balance -= val;
        new Transaction(getUniqueID() + tCount, val, "move money to " + to.getUniqueID());
        new Transaction(to.getUniqueID() + to.tCount, val, "got money from " + id);
        tCount++;
        to.tCount++;
        to.update();
        update();
        return true;
    }

    public synchronized boolean payBill(String code, String payCode) {
        if (code == null || payCode == null || code.length() != 13 || payCode.length() != 8)
            return false;
        balance -= (code.hashCode() + payCode.hashCode()) / 100;
        new Transaction(getUniqueID() + tCount, (code.hashCode() + payCode.hashCode()) / 100,
                "pay Bill , code: " + code + " , payCode: " + payCode);
        tCount++;
        update();
        return true;
    }

    // !SECTION
    @Override
    public String getUniqueID() {
        return id;
    }

    @Override
    public String toString() {
        return getUniqueID() + " , " + balance + " , " + getTransactions().toString();
    }
}
