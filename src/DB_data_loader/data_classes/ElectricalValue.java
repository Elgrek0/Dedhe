/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader.data_classes;

import org.joda.time.DateTime;

/**
 *
 * @author Paris
 */
public class ElectricalValue {
    
    
    public float value;
    public DateTime datetime;

    public ElectricalValue(DateTime datetime,float value) {
        this.value = value;
        this.datetime = datetime;
    }  
    
}
