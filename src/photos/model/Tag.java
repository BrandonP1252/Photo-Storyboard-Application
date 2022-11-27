package photos.model;

public class Tag {
    private String key;
    private String value;

    public Tag(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        return key.equals(tag.key) && value.equals(tag.value);
    }
}
