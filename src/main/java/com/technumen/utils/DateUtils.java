package com.technumen.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class DateUtils implements Serializable {

    /**
     * Returns output date in MM/dd/yyyy format for any given input date.
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            log.error("Parse Exception: " + e);
            return null;
        }
    }

    /**
     * Returns java.util weekend date (Sunday) based on the current local date.
     *
     * @return
     */
    public static Date getCurrentTimesheetWeekEndDate() {
        LocalDate lastSunday = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        log.info("lastSunday: " + lastSunday);
        return Date.from(lastSunday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Returns java.time LocalDate weekend date (Sunday) based on the current local date.
     *
     * @return
     */
    public static LocalDate getLocalTimesheetWeekEndDate() {
        log.info("Inside getLocalTimesheetWeekEndDate");
        LocalDate lastSunday = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        log.info("lastSunday: " + lastSunday);
        return lastSunday;
    }

    /**
     * Returns java.util weekend date (Sunday) for a given date.
     *
     * @return
     */
    public static Date getTimesheetWeekEndDate(Date inputDate) {
        log.info("Inside getTimesheetWeekEndDate :: inputDate: " + inputDate);
        if (inputDate != null) {
            LocalDate localDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate lastSunday = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
            log.info("Inside inputdate not null loop, lastSunday: " + lastSunday);
            return Date.from(lastSunday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            log.error("Input date not found. So returning null.");
            return null;
        }
    }

    /**
     * Returns java.time LocalDate weekend date (Sunday) for a given date.
     *
     * @return
     */
    public static LocalDate getLocalTimesheetWeekEndDate(Date inputDate) {
        log.info("Inside getLocalTimesheetWeekEndDate :: inputDate: " + inputDate);
        if (inputDate != null) {
            LocalDate localDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate lastSunday = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
            log.info("Inside inputDate not null loop, lastSunday: " + lastSunday);
            return lastSunday;
        } else {
            log.error("Input date not found. So returning null.");
            return null;
        }
    }

    /**
     * Returns list of LocaDate for end of timesheet week for last three months from current weekend Date.
     *
     * @return
     */
    public static List<Date> getListEndDatesOfLastThreeMonths() {
        log.info("Inside getListEndDatesOfLastThreeMonths method of DateUtils.");
        LocalDate lastSunday = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        log.info("lastSunday: " + lastSunday);
        long weeks = ChronoUnit.WEEKS.between(lastSunday.minusMonths(3L), lastSunday);
        log.info("Number of weeks in last three months: " + weeks);
        List<Date> collect = Stream.iterate(lastSunday.minusWeeks(weeks), d -> d.plusWeeks(1L))
                .limit(weeks + 1)
                .map(d -> Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .collect(Collectors.toList());
        log.info("list of java.util end dates: " + collect);
        return collect;
    }

    /**
     * Returns list of java util end of timesheet week Dates for last three months from current weekend Date.
     *
     * @return
     */
    public static List<LocalDate> getListLocalEndDatesOfLastThreeMonths() {
        log.info("Inside getListLocalEndDatesOfLastThreeMonths method of DateUtils.");
        LocalDate lastSunday = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        log.info("lastSunday: " + lastSunday);
        long weeks = ChronoUnit.WEEKS.between(lastSunday.minusMonths(3L), lastSunday);
        log.info("Number of weeks in last three months: " + weeks);
        List<LocalDate> collect = Stream.iterate(lastSunday.minusWeeks(weeks), d -> d.plusWeeks(1L))
                .limit(weeks + 1)
                .collect(Collectors.toList());
        log.info("list of java.time LocalDate end dates: " + collect);
        return collect;
    }


}
