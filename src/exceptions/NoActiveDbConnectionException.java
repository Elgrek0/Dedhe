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
public class NoActiveDbConnectionException extends Exception {

    public NoActiveDbConnectionException() {

        super("There was no connection with the database");
    }

}
