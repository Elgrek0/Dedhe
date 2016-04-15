/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader.data_classes;

import java.util.Vector;
import org.joda.time.LocalDate;

/**
 *
 * @author Paris
 */
public class ElectricalValueCollection {

    public PowerPlant plant;
    public Transformer transformer;
    public Breaker breaker;

    public ElectricalValueCollection(PowerPlant p, Transformer t) {
        this.plant = p;
        this.transformer = t;
    }

    public ElectricalValueCollection(PowerPlant plant, Transformer transformer, Breaker breaker) {
        this.plant = plant;
        this.transformer = transformer;
        this.breaker = breaker;
    }

    @Override
    public String toString() {
        if (breaker == null) {
            return plant.toString() + " " + transformer.toString();
        } else {
            return plant.toString() + " " + transformer.toString() + " " + breaker.toString();
        }
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof ElectricalValueCollection)) {
            return false;
        }
        ElectricalValueCollection otherMyClass = (ElectricalValueCollection) other;
        if (plant == otherMyClass.plant && transformer == otherMyClass.transformer && breaker == otherMyClass.breaker) {
            return true;
        } else {
            return false;
        }

    }

}
