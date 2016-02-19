/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQlConnection;

import DB_connection.FixValues;
import dedheproject.exceptions.BadDateInputException;
import dedheproject.exceptions.BadTimeInputException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paris
 */
public class FixValuesTest {

    public FixValuesTest() {
    }

    /**
     * Test of reversedate method, of class FixValues.
     */
    @Test
    public void testReversedate() throws Exception {
        System.out.println("date convertion test");
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
        }
    }

}
