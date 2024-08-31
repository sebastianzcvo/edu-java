package time;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.IsoEra;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static java.lang.System.out;

/**
 * LocalDateTime represents a date with time, without timezone
 * ZonedDateTime represents a date with time and timezone
 * <p>
 * DateTimeFormatter for printing and parsing date-time objects
 * <p>
 * Instant represents a precise moment in the time (timestamp)
 * Duration represents an amount of time in terms of seconds and nanoseconds
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Time {

    @Test
    @Order(0)
    void localDate() {
        // LocalDate represents an immutable date (with no time nor timezone) in terms of year, month and day fields

        // date.toString() follows ISO-8601 format: uuuu-MM-dd
        out.println(LocalDate.now());
        // LocalDate.of() validates ranges for each field
        // LocalDate.of() throws DateTimeException "Invalid date" when day is invalid for the month e.g. nov/31, feb/29 (if year isn't leap year)
        out.println(LocalDate.of(1999, Month.DECEMBER, 31)); // 1999-12-31
        out.println(LocalDate.of(1999, 12, 31));      // 1999-12-31
        out.println(LocalDate.of(-1, 12, 31));        // -0000-12-31
        out.println(LocalDate.ofYearDay(0, 32));              // 0000-02-01
        out.println(LocalDate.EPOCH);          // 1970-01-01
        out.println(LocalDate.ofEpochDay(1));  // 1970-01-02; 1 day after epoch
        out.println(LocalDate.ofEpochDay(31)); // 1970-02-01; 31 days after epoch
        out.println(LocalDate.ofEpochDay(-1)); // 1969-12-31; -1 day after epoch
        // date from datetime
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate dateFromDateTime = dateTime.toLocalDate();
        // date from instant and zone
        Instant instant = Instant.now();
        ZoneId zone = ZoneId.of("Europe/Berlin");
        LocalDate dateFromInstant = LocalDate.ofInstant(instant, zone);

        // get
        LocalDate date = LocalDate.of(1999, 12, 31); // 1999-12-31
        int anyField = date.get(ChronoField.YEAR); // 1999; get any SUPPORTED field, not recommended as unsupported fields throw exception
        int year = date.getYear(); // 1999
        int monthVal = date.getMonthValue(); // 12
        Month month = date.getMonth(); // DECEMBER
        int dayOfMonth = date.getDayOfMonth(); // 31
        int dayOfYear = date.getDayOfYear(); // 365
        DayOfWeek dayOfWeek = date.getDayOfWeek(); // FRIDAY

        // operations are pure, and return a new object, thus can be piped
        LocalDate otherDate = date              // 1999-12-31
                .plusDays(1)          // 2000-01-01; increments/decrements other fields as necessary
                .minusYears(2)    // 1998-01-01; increments/decrements other fields as necessary
                .withMonth(3);                  // 1998-03-01; validates ranges for the field
        out.println("otherDate: " + otherDate);
        // compare
        int compare = date.compareTo(otherDate); // 1
        boolean isBefore = date.isBefore(otherDate); // false
        boolean isEquals = date.equals(otherDate); // false
        boolean isAfter = date.isAfter(otherDate); // true
        // other methods
        boolean isLeapYear = date.isLeapYear(); // false; is leap if the year is divisible by 4, but not divisible by 100 unless also divisible by 400
        int lengthOfYear = date.lengthOfYear(); // 365; 365 or 366
        int lengthOfMonth = date.lengthOfMonth(); // 31; because it's january
        IsoEra era = date.getEra(); // CE; CE(current-era) year >= 1, BCE(before-current-era) year <= 0
    }

    @Test
    @Order(1)
    void localTime() {
        // LocalTime represents an immutable time (with no date nor timezone) in terms of hour, minute, second and nano fields

        // time.toString() follows ISO-8601 formats: HH:mm, HH:mm:ss, HH:mm:ss.SSS, HH:mm:ss.SSSSSS, HH:mm:ss.SSSSSSSSS
        out.println(LocalTime.now());
        // LocalTime.of() validates ranges for each field
        out.println(LocalTime.of(23, 59));                                      // 23:59
        out.println(LocalTime.of(23, 59, 59));                           // 23:59:59
        out.println(LocalTime.of(23, 59, 59, 100_000_000)); // 23:59:59.100
        out.println(LocalTime.of(23, 59, 59, 10_000_000));  // 23:59:59.010
        out.println(LocalTime.of(23, 59, 59, 1_000_000));   // 23:59:59.001
        out.println(LocalTime.of(23, 59, 59, 100_000));     // 23:59:59.000100
        out.println(LocalTime.of(23, 59, 59, 1_000));       // 23:59:59.000001
        out.println(LocalTime.of(23, 59, 59, 100));         // 23:59:59.000000100
        out.println(LocalTime.of(23, 59, 59, 1));           // 23:59:59.000000001
        out.println(LocalTime.ofNanoOfDay(1));                                              // 00:00:00.000000001
        out.println(LocalTime.ofSecondOfDay(1));                                            // 00:00:01
        // time from datetime
        LocalDateTime dateTime = LocalDateTime.now();
        LocalTime timeFromDateTime = dateTime.toLocalTime();
        // time from instant and zone
        Instant instant = Instant.now();
        ZoneId zone = ZoneId.of("Europe/Berlin");
        LocalTime timeFromInstant = LocalTime.ofInstant(instant, zone);

        // get()
        LocalTime time = LocalTime.of(23, 59, 59, 999_000_000);
        int anyField = time.get(ChronoField.HOUR_OF_DAY); // 23; get any SUPPORTED field, not recommended as unsupported fields throw exception
        int hour = time.getHour(); // 23
        int minute = time.getMinute(); // 59
        int second = time.getSecond(); // 59
        int nano = time.getNano(); // 999_000_000

        // operations are pure, and return a new object, thus can be piped
        LocalTime otherTime = time               // 23:59:59.999
                .plusNanos(1_000_000) // 00:00
                .minusHours(2)     // 22:00
                .withMinute(3);                  // 22:03
        out.println("otherTime: " + otherTime);
        // compare
        int compare = time.compareTo(otherTime); // 1
        boolean isBefore = time.isBefore(otherTime); // false
        boolean isEquals = time.equals(otherTime); // false
        boolean isAfter = time.isAfter(otherTime); // true
    }

    @Test
    @Order(2)
    void localDateTime() {
        // LocalDateTime represents an immutable datetime (with no timezone) in terms of year, month, day, hour, minute, second and nano fields

        // datetime.toString() follows ISO-8601 formats: uuuu-MM-dd'T'HH:mm, uuuu-MM-dd'T'HH:mm:ss, uuuu-MM-dd'T'HH:mm:ss.SSS, uuuu-MM-dd'T'HH:mm:ss.SSSSSS, uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS
        out.println(LocalDateTime.now());
        // LocalDateTime.of() validates ranges for each field
        // LocalDateTime.of() throws DateTimeException "Invalid date" when day is invalid for the month e.g. nov/31, feb/29 (if year isn't leap year)
        out.println(LocalDateTime.of(1999, Month.DECEMBER, 31, 23, 59));                                 // 1999-12-31T23:59
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59));                                       // 1999-12-31T23:59
        out.println(LocalDateTime.of(-1, 12, 31, 23, 59));                                         //-0001-12-31T23:59
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59, 59));                            // 1999-12-31T23:59:59
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59, 59, 100_000_000));  // 1999-12-31T23:59:59.100
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59, 59, 10_000_000));   // 1999-12-31T23:59:59.010
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59, 59, 1_000_000));    // 1999-12-31T23:59:59.001
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59, 59, 100_000));      // 1999-12-31T23:59:59.000100
        out.println(LocalDateTime.of(1999, 12, 31, 23, 59, 59, 100));          // 1999-12-31T23:59:59.000000100
        // datetime from date and time
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        LocalDateTime datetimeFromDateAndTime = LocalDateTime.of(date, time);
        LocalDateTime dateAtTime = date.atTime(time);
        LocalDateTime timeAtDate = time.atDate(date);
        // datetime from instant and zone
        Instant instant = Instant.now();
        ZoneId zone = ZoneId.of("Europe/Berlin");
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, zone);

        // get()
        LocalDateTime datetime = LocalDateTime.of(1999, Month.DECEMBER, 31, 23, 59, 59, 999_000_000);
        int anyField = date.get(ChronoField.YEAR); // 1999; get any SUPPORTED field, not recommended as unsupported fields throw exception
        int year = datetime.getYear(); // 1999
        int monthVal = datetime.getMonthValue(); // 12
        Month month = datetime.getMonth(); // DECEMBER
        int dayOfMonth = datetime.getDayOfMonth(); // 31
        int dayOfYear = datetime.getDayOfYear(); // 365
        DayOfWeek dayOfWeek = datetime.getDayOfWeek(); // FRIDAY
        int hour = datetime.getHour(); // 23
        int minute = datetime.getMinute(); // 59
        int second = datetime.getSecond(); // 59
        int nano = datetime.getNano(); // 999_000_000

        // operations are pure, and return a new object, thus can be piped
        LocalDateTime otherDatetime = datetime // 1999-12-31T23:59:59.999
                .plusNanos(1_000_000)          // 2000-01-01T00:00
                .minusYears(2)                 // 1998-01-01T00:00
                .withMonth(3);                 // 1998-03-01T00:00
        out.println("otherDatetime: " + otherDatetime);
        // compare
        int compare = datetime.compareTo(otherDatetime); // 1
        boolean isBefore = datetime.isBefore(otherDatetime); // false
        boolean isEquals = datetime.equals(otherDatetime); // false
        boolean isAfter = datetime.isAfter(otherDatetime); // true
    }

    @Test
    @Order(3)
    void zonedDateTime() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        ZoneId zone;
        zone = ZoneId.of("America/New_York");
        zone = ZoneId.of("GMT-4");
        zone = ZoneId.of("UTC-05:00");
        zone = ZoneId.systemDefault(); // America/Bogota

        // creation
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // move to a zone (same instant; different local-datetime)
        zonedDateTime = zonedDateTime.withZoneSameInstant(zone);
        // date, time and datetime can be extracted from a zoned-datetime
        LocalDate date = zonedDateTime.toLocalDate();
        LocalTime time = zonedDateTime.toLocalTime();
        LocalDateTime datetime = zonedDateTime.toLocalDateTime();
        // date, time, datetime and zone can be combined into a new zoned-datetime
        zonedDateTime = ZonedDateTime.of(date, time, zone);
        zonedDateTime = date.atTime(time).atZone(zone);
        zonedDateTime = time.atDate(date).atZone(zone);
        zonedDateTime = ZonedDateTime.of(datetime, zone);
        zonedDateTime = datetime.atZone(zone);
        // ZonedDateTime.of() throws DateTimeException "Invalid date" when day is invalid for the month e.g. nov/31 or feb/29 when year isn't a leap year
        zonedDateTime = ZonedDateTime.of(1999, 12, 31, 23, 59, 59, 999_999_999, zone);

        // zonedDateTime.toString() follows ISO-8601 format: LocalDateTime followed by the ZoneOffset
        out.println(zonedDateTime); // 1999-12-31T23:59:59.999999999-05:00[America/Bogota]

        // get()
        int year = zonedDateTime.getYear(); // 1999
        int monthVal = zonedDateTime.getMonthValue(); // 12
        Month month = zonedDateTime.getMonth(); // DECEMBER
        int dayOfMonth = zonedDateTime.getDayOfMonth(); // 31
        int dayOfYear = zonedDateTime.getDayOfYear(); // 365
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek(); // FRIDAY
        int hour = zonedDateTime.getHour(); // 23
        int minute = zonedDateTime.getMinute(); // 59
        int second = zonedDateTime.getSecond(); // 59
        int nano = zonedDateTime.getNano(); // 999999999
        ZoneId zoneId = zonedDateTime.getZone(); // America/Bogota
        ZoneOffset offset = zonedDateTime.getOffset(); // -05:00

        // operations are pure, and return a new object, thus can be piped
        ZonedDateTime oldZonedDateTime = zonedDateTime // 1999-12-31T23:59:59.999999999-05:00[America/Bogota]
                .plusNanos(1)      // plus 1 nano = 2000-01-01T00:00-05:00[America/Bogota]
                .minusYears(2)     // minus 2 year = 1998-01-01T00:00-05:00[America/Bogota]
                .withMonth(3);     // set month to 3 = 1998-03-01T00:00-05:00[America/Bogota]

        // compare
        int compare = zonedDateTime.compareTo(oldZonedDateTime); // 1
        boolean isBefore = zonedDateTime.isBefore(oldZonedDateTime); // false
        boolean isEquals = zonedDateTime.equals(oldZonedDateTime); // false
        boolean isAfter = zonedDateTime.isAfter(oldZonedDateTime); // true
    }

    @Test
    @Order(4)
    void dateTimeFormatter() {
        // parse String to LocalTime
        LocalTime localTime;
        localTime = LocalTime.parse("23:59");
        localTime = LocalTime.parse("23:59:59");
        localTime = LocalTime.parse("23:59:59.999999999");
        localTime = LocalTime.parse("23:59:59.999999999", DateTimeFormatter.ISO_LOCAL_TIME); // the default formater is DateTimeFormatter.ISO_LOCAL_TIME
        localTime = LocalTime.parse("23:59:59.999999999", DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSSSS")); // pattern must match or DateTimeParseException is thrown
        // format LocalTime to String
        String string;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        string = localTime.format(formatter); // both ways are equivalent
        string = formatter.format(localTime); // both ways are equivalent
        out.println(string); // 23:59:59.999999999
    }

    @Test
    @Order(5)
    void period() {
        // Period represents an amount of time in terms of positive or negative years, months and days
        // Useful to represent a temporal amount between 2 DATES
        // Doesn't automatically add fields e.i. days don't add to months, months don't add to years

        // period.toString() follows the ISO-8601 period format: P0D
        out.println(Period.of(10, 20, -30)); // P10Y20M-30D
        out.println(Period.ZERO); // P0D
        out.println(Period.ofDays(1)); // P1D
        out.println(Period.ofMonths(1)); // P1M
        out.println(Period.ofYears(1)); // P1Y
        // period between two DATES
        LocalDate now = LocalDate.now();
        LocalDate past = now.minusDays(1);
        Period between = Period.between(now, past); // P-1D
        Period until = now.until(past); // P-1D, equivalent to between, recommended

        // get units
        Period period = Period.of(1, 2, -3);
        int years = period.getYears(); // 1
        int months = period.getMonths(); // 2
        int days = period.getDays(); // -3

        // operations are pure, return a new object, thus can be piped
        Period newPeriod = period               // P1Y2M-3D
                .minusYears(1)    // P2M-3D
                .plusMonths(2)      // P4M-3D
                .withDays(3)                    // P4M3D
                .multipliedBy(2);         // P8M6D
        out.println("newPeriod: " + newPeriod); // P8M6D

        // other operations
        long totalMonths = period.toTotalMonths(); // 14; totalMonths = years*12 + months
        boolean isZero = period.isZero(); // false; true if all 3 units are 0
        boolean isNegative = period.isNegative(); // true; true if any unit is negative
        boolean isEquals = period.equals(newPeriod); // false; true if all 3 units are equals
    }

    @Test
    @Order(6)
    void duration() {
        Duration duration = Duration.ofMinutes(1);
        out.println(duration);
        out.println(duration.getSeconds());
        out.println(duration.getNano());

        Duration durationOf = Duration.of(1, ChronoUnit.DAYS);
        out.println(durationOf);

        Duration between = Duration.between(Instant.now(), Instant.now());
        out.println(between);
    }

    @Test
    @Order(7)
    void instant() {
        Instant now = Instant.now();
        out.println(now);
        out.println(now.getEpochSecond());
        out.println(now.getNano());

        Instant later = Instant.now().plus(Duration.of(1, ChronoUnit.DAYS));
        out.println(now.isBefore(later));
        out.println(now.isAfter(later));
        out.println(now.equals(later));

        Duration duration = Duration.between(now, later);
        out.println(duration);
    }

}
