/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import DB_data_loader.LoadDataFromDB;
import DB_data_loader.data_classes.ElectricalValue;
import DB_data_loader.data_classes.Transformer;
import exceptions.NoActiveDbConnectionException;
import exceptions.NoItemSelectedException;
import exceptions.NoTransformerSelectedException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import org.joda.time.LocalDate;

/**
 *
 * @author Paris
 */
public class TransformerCache {

    Transformer transformer;
    List<YearCache> years = new ArrayList<YearCache>();

    TransformerCache(Transformer transformer) {
        this.transformer = transformer;
    }

    Vector<ElectricalValue> get_data(Transformer transformer, LocalDate startDate,LocalDate endDate, BaseCache cache) throws NoActiveDbConnectionException,  NoItemSelectedException {
Vector<ElectricalValue> toreturn = new Vector<>();
        LocalDate currdate = null;

        int cacheditems = 0;
        int totalitems = 0;

        currdate = new LocalDate(startDate);
        while (currdate.isBefore(endDate)) {

            YearCache wanted_year = null;
            for (YearCache yc : years) {
                if (yc.year == currdate.getYear()) {
                    wanted_year = yc;
                    break;
                }

            }
            if (wanted_year == null) {

                wanted_year = new YearCache(currdate.getYear());
                years.add(wanted_year);

            }

            MonthCache wanted_month = wanted_year.months[currdate.getMonthOfYear() - 1];
            DayCache wanted_day = wanted_month.days[currdate.getDayOfMonth() - 1];

            if (wanted_day.data != null) {
                cacheditems++;
            }
            totalitems++;

            currdate = currdate.plusDays(1);
        }

        if (cacheditems / totalitems > 0.9) {

            currdate = new LocalDate(startDate);
            while (currdate.isBefore(endDate)) {

                YearCache wanted_year = null;
                for (YearCache yc : years) {
                    if (yc.year == currdate.getYear()) {
                        wanted_year = yc;
                        break;
                    }

                }
                if (wanted_year == null) {

                    wanted_year = new YearCache(currdate.getYear());
                    years.add(wanted_year);

                }

                MonthCache wanted_month = wanted_year.months[currdate.getMonthOfYear() - 1];
                DayCache wanted_day = wanted_month.days[currdate.getDayOfMonth() - 1];

                cache.cache_day(wanted_day);
                if (wanted_day.data == null) {
                    wanted_day.data = LoadDataFromDB.get_data(transformer, currdate, currdate.plusDays(1));
                }
                toreturn.addAll(wanted_day.data);
                currdate = currdate.plusDays(1);

            }
        } else {
            toreturn = LoadDataFromDB.get_data(transformer, startDate, endDate);

            currdate = new LocalDate(startDate);

            YearCache wanted_year = null;
            for (YearCache yc : years) {
                if (yc.year == currdate.getYear()) {
                    wanted_year = yc;
                    break;
                }

            }
            if (wanted_year == null) {
                wanted_year = new YearCache(currdate.getYear());
                years.add(wanted_year);
            }

            MonthCache wanted_month = wanted_year.months[currdate.getMonthOfYear() - 1];
            DayCache wanted_day = wanted_month.days[currdate.getDayOfMonth() - 1];

            currdate = currdate.plusDays(1);

            Vector<ElectricalValue> daydata = new Vector<>();
            for (int i = 0; i < toreturn.size(); i++) {
                if (toreturn.get(i).datetime.toLocalDate().isBefore(currdate)) {
                    daydata.add(toreturn.get(i));

                } else {

                    if (daydata.size() > 0) {
                        wanted_day.data = daydata;
                        cache.cache_day(wanted_day);
                        daydata = new Vector<>();
                    }

                    wanted_year = null;
                    for (YearCache yc : years) {
                        if (yc.year == currdate.getYear()) {
                            wanted_year = yc;
                            break;
                        }

                    }
                    if (wanted_year == null) {
                        wanted_year = new YearCache(currdate.getYear());
                        years.add(wanted_year);
                    }

                    wanted_month = wanted_year.months[currdate.getMonthOfYear() - 1];
                    wanted_day = wanted_month.days[currdate.getDayOfMonth() - 1];

                    currdate = currdate.plusDays(1);

                }

            }

        }
        return toreturn;
    }

}
