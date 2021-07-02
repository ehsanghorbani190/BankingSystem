package server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Filer {

    public static <T extends Identifiable> void write(T obj) {
        try {
            if (obj == null)
                throw new IllegalArgumentException("Cannot write null object");
            String path = obj.getClass().getSimpleName() + "s";
            File temp = new File(path);
            if (!temp.exists())
                temp.mkdir();
            File file = new File(path, obj.getUniqueID() + "." + obj.getClass().getSimpleName());
            if (file.exists())
                file.delete();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(obj);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T extends Identifiable> T read(String ID, Class<T> type) {
        try {
            String path = type.getSimpleName() + "s";
            File file = new File(path, ID + "." + type.getSimpleName());
            Object temp;
            if (file.exists()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                temp = in.readObject();
                in.close();
            } else
                temp = null;

            return (T) temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static  boolean delete(String ID , Class<?> type) {
        String path = type.getSimpleName() + "s";
        File file = new File(path, ID + "." + type.getSimpleName());
        return file.delete();
    }
    public static  boolean exists(String id , Class<?> type) {
        String path = type.getSimpleName() + "s";
        File file = new File(path, id + "." + type.getSimpleName());
        return file.exists();
    }
}