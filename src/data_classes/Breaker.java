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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class Breaker implements DBItem {

    public Transformer parent_transformer;

    public int id;
    public String name;

    public Breaker(int id, String name, Transformer parent_transformer) {
        this.id = id;
        this.name = name;
        this.parent_transformer = parent_transformer;
        parent_transformer.breakers.add(this);
    }
    @Override
    public void store() {
        
        StoreDatatoDB.store("Breaker", id + ",'" + name + "',"+parent_transformer.id);
        
    }
    @Override
    public String toString(){
        return name;
    }
}
