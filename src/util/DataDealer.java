package util;

import java.io.Serializable;
import java.util.HashMap;

public class DataDealer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private HashMap<String, String> data;

    public DataDealer(int status) {
        this.status = status;
        data = new HashMap<String , String>();
    }

    /**
     * @return the error
     */
    public String getError() {
        return getData("error");
    }

    public void setError(String err) {
        addData("error", err);
    }

    /**
     * @return the data
     */
    public String getData(String key) {
        return data.get(key);
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public void addData(String key, String value) {
        data.put(key, value);
    }
}
