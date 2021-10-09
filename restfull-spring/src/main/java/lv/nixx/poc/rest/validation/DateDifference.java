package lv.nixx.poc.rest.validation;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

public class DateDifference {

    private final Calendar date;
    private final Calendar now;

    public DateDifference(Date date, Date now) {
        this.date = getCalendar(date);
        this.now = getCalendar(now);
    }

    public int years() {
        int diff = now.get(YEAR) - date.get(YEAR);
        if (date.get(MONTH) > now.get(MONTH) ||
                (date.get(MONTH) == now.get(MONTH) && date.get(DATE) > now.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
