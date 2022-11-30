package photos.model;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * 
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class Date implements Serializable {
    LocalDate date;
    public Date() {
        date = LocalDate.now();
    }

    // yyyy-mm-dd
    /**
     * 
     * @param sets the date of the photo to the image
     */
    public Date(String date) {
        String[] info = date.split("-");
        this.date = LocalDate.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
    }

    @Override
    public String toString() {
        return date.toString();
    }
/**
 * 
 * @return finds the date of the photo and displays
 */

    public LocalDate getLocalDate() {
        return date;
    }

}
