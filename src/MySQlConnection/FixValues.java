/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQlConnection;

/**
 *
 * @author Paris
 */
public class FixValues {

    public static String reversedate(String date, char splitter) {
        String dateparts[] = date.split(" ")[0].split("" + splitter);
        String fixeddate = "";
        for (int i = dateparts.length - 1; i >= 0; i--) {
            fixeddate += dateparts[i];
            if (i != 0) {
                fixeddate += '-';
            }
        }
        return (fixeddate);
    }
}
