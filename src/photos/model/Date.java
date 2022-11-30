package photos.model;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Date implements Serializable {
    LocalDate date;
    public Date() {
        date = LocalDate.now();
    }

    // yyyy-mm-dd
    /**
     * 
     * @param date the date of photo
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
 * @return date
 */

    public LocalDate getLocalDate() {
        return date;
    }

}
