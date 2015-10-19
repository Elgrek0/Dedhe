package MySQlConnection;

/**
 *
 * @author Paris
 */
public class FixValues {

    public static String reversedate(String date, char splitter) {
        String array[]; // c
        array = date.split(" "); // c
        //System.out.println(array.length); // c
        String fixeddate1 = "";

        String fixeddate = "";
        if (array.length == 1) {
            fixeddate1 = "00:00:00";
        } else {
            String dateparts1[];
            dateparts1 = date.split(" ")[1].split("" + splitter);
            for (int j = dateparts1.length - 1; j >= 0; j--) {
                fixeddate1 += dateparts1[j];

                if (j != 0) {
                    fixeddate1 += ':';
                    // comment//
                }
                if (j == 0) {
                    fixeddate1 += ":00";
                    // comment//
                }

            }
        }

        String dateparts[];
        dateparts = date.split(" ")[0].split("" + splitter);

        
        fixeddate += dateparts[2]+"-";
        fixeddate += dateparts[0]+"-";
        fixeddate += dateparts[1]+" ";


        fixeddate = fixeddate.concat(fixeddate1);
        return (fixeddate);

    }
}
