package DB_connection;

import exceptions.BadTimeInputException;
import exceptions.BadDateInputException;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 *
 * @author Paris
 */
public class FixValues {

    /**
     *
     * @param datetime is the datetime from the excel but the library changes
     * @param date_splitter is the way the date is seperated
     * @param time_splitter is the way the time is seperated
     * @return should return the datetime as mysql expects it
     * @throws BadDateInputException date or time is wrong
     */
    public static String reversedate(String datetime, char date_splitter, char time_splitter) throws BadDateInputException, BadTimeInputException {
        if (datetime == null) {
            throw new BadDateInputException("date is null");
        }
        String datetime_parts[] = datetime.split(" ");
        LocalTime lt;
        LocalDate ld;
        if (datetime_parts.length == 1) {//there is no time
            lt = new LocalTime(0, 0, 0);
        } else {
            String time_parts[];
            time_parts = datetime_parts[1].split("" + time_splitter);
            try {
                lt = new LocalTime(Integer.parseInt(time_parts[0]), Integer.parseInt(time_parts[1]), 0);
            } catch (Exception e) {
                throw new BadTimeInputException("time format error");
            }

        }

        String dateparts[];
        dateparts = datetime_parts[0].split("" + date_splitter);

        try {
            ld = new LocalDate(Integer.parseInt(dateparts[2]), Integer.parseInt(dateparts[1]), Integer.parseInt(dateparts[0]));
            
        } catch (Exception e) {
            throw new BadDateInputException("date format error");
        }
        return (ld.toString("yyyy-MM-dd") + " " + lt.toString("HH:mm:ss"));
    }

}
