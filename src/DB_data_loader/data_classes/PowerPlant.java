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
public class PowerPlant implements DBItem {

    public int id;
    public String name;
    public Vector<Transformer> transformers = new Vector<>();

    public PowerPlant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void store() throws CouldntStoreDataException {

        StoreDatatoDB.store("Power_Plant", id + ",'" + name+"'");

    }

    @Override
    public String toString() {
        return name;
    }
}
