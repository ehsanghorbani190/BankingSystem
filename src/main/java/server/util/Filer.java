package server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Filer<Type extends Identifiable> {
    private File file;
    private Type obj;
    private String path;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Filer(Type object) {
        try {
            path = object.getClass().getSimpleName() + "s";
            File temp = new File(path);
            if (!temp.exists())
                temp.mkdir();
            file = new File(path, object.getUniqueID() + "." + object.getClass().getSimpleName());
            out = new ObjectOutputStream(new FileOutputStream(file, true));
            in = new ObjectInputStream(new FileInputStream(file));
            obj = object;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Filer(String ID, Class<?> type) {
        try {
            path = type.getSimpleName() + "s";
            file = new File(path, ID + "." + type.getClass().getSimpleName());
            if (file.exists()) {
                out = new ObjectOutputStream(new FileOutputStream(file, true));
                in = new ObjectInputStream(new FileInputStream(file));
                obj = read();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void write() {
        try {
            if (!file.exists())
                out.writeObject(obj);
            resetOut();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // We prove that the object is always Type , so we suppress the warning
    @SuppressWarnings("unchecked")
    public Type read() {
        try {
            Object temp = in.readObject();
            resetIn();
            return (temp != null) ? (Type) (temp) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public void update(Type object) {
        try {
            file.delete();
            file = new File(path, String.valueOf(object.getUniqueID() + "." + object.getClass().getSimpleName()));
            resetOut();
            resetIn();
            obj = object;
            write();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean exists() {
        return file.exists();
    }

    public boolean delete() {
        return file.delete();
    }

    public void resetIn() {
        try {
            in = new ObjectInputStream(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void resetOut() {
        try {
            out = new ObjectOutputStream(new FileOutputStream(file, true));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void close() {
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}