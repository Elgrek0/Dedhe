/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import DB_data_loader.data_classes.Breaker;
import DB_data_loader.data_classes.DataItem;
import DB_data_loader.data_classes.ElectricalValue;
import DB_data_loader.data_classes.Transformer;
import exceptions.NoActiveDbConnectionException;
import exceptions.NoItemSelectedException;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import org.joda.time.LocalDate;

/**
 *
 * @author Paris
 */
public class BaseCache {

    Vector<ActionListener> changelisteners = new Vector<>();

    public void addChangeListener(ActionListener changelistener) {
        changelisteners.add(changelistener);

    }

    private void statechangedevent() {
        for (int i = 0; i < changelisteners.size(); i++) {
            changelisteners.get(i).actionPerformed(null);
        }

    }

    public int size_MB;

    public BaseCache(int size_MB) {
        if (size_MB > 1) {
            this.size_MB = size_MB;
        } else {
            this.size_MB = 1;
        }
    }

    List<BreakerCache> cached_breakers = new LinkedList<BreakerCache>();
    List<TransformerCache> cached_transformers = new LinkedList<TransformerCache>();

    List<DayCache> cached_days = new LinkedList<DayCache>();

    public Vector<ElectricalValue> get_data(DataItem item, LocalDate startDate, LocalDate endDate) throws NoActiveDbConnectionException, NoItemSelectedException {
        Vector<ElectricalValue> toreturn = new Vector<>();
        if (item == null) {
            throw new NoItemSelectedException();
        }
        if (item.getClass() == Breaker.class) {
            Breaker breaker = (Breaker) item;
            BreakerCache wanted_breaker = null;
            for (BreakerCache bc : cached_breakers) {
                if (bc.breaker.id == breaker.id) {
                    wanted_breaker = bc;
                    break;
                }

            }
            if (wanted_breaker == null) {

                wanted_breaker = new BreakerCache(breaker);
                cached_breakers.add(wanted_breaker);

            }
            toreturn = wanted_breaker.get_data(breaker, startDate, endDate, this);

        } else if (item.getClass() == Transformer.class) {
            Transformer transformer = (Transformer) item;
            TransformerCache wanted_transformer = null;
            for (TransformerCache tc : cached_transformers) {
                if (tc.transformer.id == transformer.id) {
                    wanted_transformer = tc;
                    break;
                }

            }
            if (wanted_transformer == null) {

                wanted_transformer = new TransformerCache(transformer);
                cached_transformers.add(wanted_transformer);

            }
            toreturn = wanted_transformer.get_data(transformer, startDate, endDate, this);

        }

        //System.out.println("Cache has "+cached_days.size()+" items");
        //System.out.println("Size "+getSizeKB()+" KB");
        statechangedevent();
        return toreturn;
    }

    void cache_day(DayCache wanted_day) {

        if (cached_days.contains(wanted_day)) {
            cached_days.remove(wanted_day);
            cached_days.add(wanted_day);
        } else {
            if (cached_days.size() * 150 > size_MB * 1000000) {
                cached_days.get(0).data = null;
                cached_days.remove(0);
            }
            cached_days.add(wanted_day);
        }
    }

    public float getSizeKB() {
        return (float) (cached_days.size() * 150.0 / 1000.0);
    }

    public float getSizeMB() {
        return (float) (cached_days.size() * 150.0 / 1000000.0);
    }

}
