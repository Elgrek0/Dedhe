/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_classes;

import DB_data_loader.StaticCachedData;
import DB_connection.DBConnection;
import DB_data_loader.StoreDatatoDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class Transformer implements DBItem {

    public PowerPlant parent_powerplant;

    public int id;
    public String name;
    public Vector<Breaker> breakers = new Vector<>();

    public Transformer(int id, String name, PowerPlant parent_powerplant) {
        this.id = id;
        this.name = name;
        this.parent_powerplant = parent_powerplant;
        parent_powerplant.transformers.add(this);

    }

    @Override
    public void store() {
        StoreDatatoDB.store("Transformer", id + ",'" + name + "'," + parent_powerplant.id);

    }

    @Override
    public String toString() {
        return name;
    }
}
