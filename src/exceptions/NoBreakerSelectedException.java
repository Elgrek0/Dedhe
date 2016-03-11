/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Paris
 */
public class NoBreakerSelectedException extends Exception {

    public NoBreakerSelectedException() {
        super("No breaker was selected");
    }

}
