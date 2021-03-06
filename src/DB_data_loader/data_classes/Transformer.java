/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader.data_classes;

import DB_data_loader.StoreDatatoDB;
import exceptions.CouldntStoreDataException;
import java.util.Vector;

/**
 *
 * @author Paris
 */
public class Transformer extends DataItem implements DBItem {

    public PowerPlant parent_powerplant;

    public Vector<Breaker> breakers = new Vector<>();

    public Transformer(int id, String name, PowerPlant parent_powerplant) {
        super(id, name);
        this.parent_powerplant = parent_powerplant;
        parent_powerplant.transformers.add(this);

    }

    @Override
    public void store() throws CouldntStoreDataException {
        StoreDatatoDB.store("Transformer", id + ",'" + name + "'," + parent_powerplant.id);

    }

    @Override
    public String toString() {
        return name;
    }
}
