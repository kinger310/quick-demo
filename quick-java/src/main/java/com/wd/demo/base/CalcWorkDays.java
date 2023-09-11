package com.wd.demo.base;

import com.google.common.collect.ImmutableSet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Author: wangd
 * @Date: 2023/6/30 15:04
 */
public class CalcWorkDays {
    abstract static class StockExchange {
        abstract Set<String> getSpecialHolidays();

        abstract Set<String> getSpecialWorkdays();

    }

    static class SHStockExchange extends StockExchange {

        @Override
        Set<String> getSpecialHolidays() {
            return ImmutableSet.copyOf(Arrays.asList("2023-06-22", "2023-06-23", "2023-06-24"));
        }

        @Override
        Set<String> getSpecialWorkdays() {
            return ImmutableSet.copyOf(Arrays.asList("2023-06-25"));
        }
    }

    StockExchange stockExchange;

    Set<String> specialHolidays;
    Set<String> specialWorkdays;

    public CalcWorkDays(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
        specialWorkdays = stockExchange.getSpecialWorkdays();
        specialHolidays = stockExchange.getSpecialHolidays();
    }


    public static void main(String[] args) {

        CalcWorkDays c = new CalcWorkDays(new SHStockExchange());
        System.out.println(c.getWorkTime("2023-06-01", "2023-06-30"));
        System.out.println(c.getDateAfterNWorkdays("2023-06-18", 5));

        LocalDateTime parse = LocalDateTime.parse("2023-06-30T10:00:00");
        System.out.println(parse.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(parse.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    public String getDateAfterNWorkdays(String dateStr, int n) {
        LocalDate d = LocalDate.parse(dateStr);
        int workingDays = 0;

        while (workingDays < n) {
            d = d.plus(1, ChronoUnit.DAYS);
            if (!isHoliday(d)) {
                workingDays++;
            }
        }
        return formatDate(d);
    }

    public int getWorkTime(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        LocalDate d = startDate;
        int workingDays = 0;
        while (d.isBefore(endDate) || d.isEqual(endDate)) {
            if (!isHoliday(d))
                workingDays++;
            d = d.plus(1, ChronoUnit.DAYS);

        }
        return workingDays;
    }

    public boolean isHoliday(LocalDate date) {
        DayOfWeek w = date.getDayOfWeek();
        String dStr = formatDate(date);
        if (specialHolidays.contains(dStr)) return true;
        if (specialWorkdays.contains(dStr)) return false;
        return w == DayOfWeek.SATURDAY || w == DayOfWeek.SUNDAY;
    }

    private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
