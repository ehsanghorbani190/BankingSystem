package server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Filer<Type> {
    private File file;
    private Type obj;
    private String path;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Filer(String path, Type object) {
        try {
            File temp = new File(path);
            if (!temp.exists())
                temp.mkdir();
            file = new File(path, String.valueOf(object.hashCode()));
            out = new ObjectOutputStream(new FileOutputStream(file, true));
            in = new ObjectInputStream(new FileInputStream(file));
            obj = object;
            this.path = path;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void write() {
        try {
            out.writeObject(obj);
            resetOut();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //We prove that the object is always Type , so we suppress the warning
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
            file = new File(path, String.valueOf(object.hashCode()));
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