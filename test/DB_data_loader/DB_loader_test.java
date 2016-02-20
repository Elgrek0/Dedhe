/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader;

import DB_connection.FixValues;
import DB_data_loader.data_classes.Breaker;
import DB_data_loader.data_classes.PowerPlant;
import DB_data_loader.data_classes.Transformer;
import exceptions.BadDateInputException;
import exceptions.BadTimeInputException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paris
 */
public class DB_loader_test {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    public DB_loader_test() {
    }

    /**
     * Test of reversedate method, of class FixValues.
     */
    @Test
    public void testReversedate() throws Exception {

        PowerPlant p = new PowerPlant(0, "test");
        Transformer t = new Transformer(0, "test t", p);
        Breaker b = new Breaker(0, "test b", t);

        System.out.println("data loading test");
        /*
         assertEquals("2015-01-13 00:00:00", FixValues.reversedate("1/13/2015", '/', ':'));
         assertEquals("2015-01-13 12:30:00", FixValues.reversedate("1/13/2015 12:30", '/', ':'));
         assertEquals("2015-01-13 23:30:00", FixValues.reversedate("1/13/2015 23:30", '/', ':'));
         try {
         FixValues.reversedate("13/13/2015 00:30", '/', ':');
         fail("IllegalArgumentException was expected");
         } catch (BadDateInputException ex) {
         }
         try {
         FixValues.reversedate("1/13/2015 24:30", '/', ':');
         fail("IllegalArgumentException was expected");
         } catch (BadTimeInputException ex) {
         }
         try {
         FixValues.reversedate("1/13/2015 23:70", '/', ':');
         fail("IllegalArgumentException was expected");
         } catch (BadTimeInputException ex) {
         }*/
    }

}
