package photos.model;

import java.time.LocalDate;

public class Date {
    LocalDate date;

    public Date() {
        date = LocalDate.now();
    }

    @Override
    public String toString() {
        return date.toString();
    }

    public LocalDate getLocalDate() {
        return date;
    }
}
