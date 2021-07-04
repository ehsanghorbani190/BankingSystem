package util;

import java.util.HashMap;

public class DataDealer {
    private int status;
    private HashMap<String,String> data;
    private String err;
    public DataDealer(int status , String err){
        this.status = status;
        this.err = err;
    }
    /**
     * @return the err
     */
    public String getErr() {
        return err;
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
    public void addData(String key, String value){
        data.put(key, value);
    }
}
