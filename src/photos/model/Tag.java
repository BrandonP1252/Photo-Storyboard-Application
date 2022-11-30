package photos.model;

import java.io.Serializable;

/**
 * Represents the tag object.
 * @author Brandon Perez bkp49
 * @author Julian Calle joc24
 */
public class Tag implements Serializable {
    /**
     * Represents the key.
     */
    private String key;
    /**
     * Represents the value.
     */
    private String value;

    /**
     * Initializes tag
     * @param key represents key
     * @param value represents value
     */
    public Tag(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets key from tag.
     * @return key represents key value.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets value from tag.
     * @return value represents value value.
     */
    public String getValue() {
        return value;
    }

    /**
     * ToString method for tag object.
     * @return String Returns String of object.
     */
    @Override
    public String toString() {
        return key + " = " + value;
    }

    /**
     * Checks if two objects are equal.
     * @param obj Represents object being compared
     * @return boolean Returns boolean of two objects.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        return key.equals(tag.key) && value.equals(tag.value);
    }
}
