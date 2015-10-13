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
        String dateparts[];
        dateparts = date.split(" ")[0].split("" + splitter );
        String fixeddate = "";
        for (int i = dateparts.length - 1; i >= 0; i--) { 
            fixeddate += dateparts[i];
            if (i !=0) {
                fixeddate += '-';
                // comment//
            }
             if (i==0) {
                fixeddate += ' ';
                // comment//
            }
        }
        String dateparts1 [];
        dateparts1 = date.split(" ")[1].split("" + splitter );
        
        String fixeddate1 = "";
         for (int j = dateparts1.length - 1; j >= 0;j--) { 
            fixeddate1 += dateparts1[j];
            if (j !=0) {
                fixeddate1 += ':';
                // comment//
            }
            if (j ==0) {
                fixeddate1 += ":00";
                // comment//
            }
        }
        fixeddate=fixeddate.concat(fixeddate1);
        return (fixeddate);
        
    }
}
