/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject.exceptions;

/**
 *
 * @author Paris
 */
public class CouldntConnectException extends Exception {

    public CouldntConnectException(String s) {
        super(s);
    }

    CouldntConnectException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
