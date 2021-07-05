package server.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import server.util.Filer;
import server.util.Identifiable;

public class Transaction implements Identifiable {
    private static final long serialVersionUID = 1L;
    private String id;
    private long val;
    private String type;
    private String date;

    public Transaction(String id, long val, String type) {
        this.id = id;
        this.val = val;
        this.type = type;
        date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        Filer.<Transaction>write(this);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the val
     */
    public long getVal() {
        return val;
    }
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    @Override
    public String getUniqueID() {
        return id;
    }
    @Override
    public String toString() {
        return "["+ id + " , " + val + " , " + type+"]";
    }
}