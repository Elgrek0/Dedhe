/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader.data_classes;

import DB_data_loader.StoreDatatoDB;
import exceptions.CouldntStoreDataException;

/**
 *
 * @author Paris
 */
public class Breaker extends DataItem implements DBItem {

    public Transformer parent_transformer;

    public Breaker(int id, String name, Transformer parent_transformer) {
        super(id,name);
        this.parent_transformer = parent_transformer;
        parent_transformer.breakers.add(this);
    }

    @Override
    public void store() throws CouldntStoreDataException {

        StoreDatatoDB.store("Breaker", id + ",'" + name + "'," + parent_transformer.id);

    }

    @Override
    public String toString() {
        return name;
    }
}
