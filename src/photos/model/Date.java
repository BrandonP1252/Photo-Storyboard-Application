package photos.model;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Date {
    LocalDate date;
    public Date() {
        date = LocalDate.now();
        LocalDateTime test = LocalDateTime.now();
    }

    // yyyy-mm-dd
    public Date(String date) {
        String[] info = date.split("-");
        this.date = LocalDate.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
    }

    @Override
    public String toString() {
        return date.toString();
    }

    public LocalDate getLocalDate() {
        return date;
    }

}
