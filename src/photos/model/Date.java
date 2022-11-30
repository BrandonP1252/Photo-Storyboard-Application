package photos.model;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Represents the date object.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class Date implements Serializable {
    /**
     * Represents date of photo.
     */
    LocalDate date;

    /**
     * Initializes date.
     */
    public Date() {
        date = LocalDate.now();
    }

    // yyyy-mm-dd

    /**
     * Initializes date with input.
     * @param date Represents date input.
     */
    public Date(String date) {
        String[] info = date.split("-");
        this.date = LocalDate.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
    }

    /**
     * ToString method for date object.
     * @return date Represents date as a string.
     */
    @Override
    public String toString() {
        return date.toString();
    }

    /**
     * Gets date of photo.
     * @return date Represents date of photo.
     */
    public LocalDate getLocalDate() {
        return date;
    }

}
