/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

/**
 *
 * @author Paris
 */
public class YearCache {

    int year;

    public YearCache(int year) {
        this.year = year;
        for (int i = 0; i < 12; i++) {
            months[i] = new MonthCache();
        }
    }

    MonthCache[] months = new MonthCache[12];

}
