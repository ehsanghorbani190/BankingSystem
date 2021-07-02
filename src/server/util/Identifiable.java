package server.util;

import java.io.Serializable;

public interface Identifiable extends Serializable {
    public String getUniqueID();
}
