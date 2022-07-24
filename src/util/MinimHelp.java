/* CLASS COMMENT: 
 * This file contains a helper file for loading
 * Minim and sounds that was taken from the week 10 lab.*/
package util;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelp {

    public String sketchPath( String fileName ) {
        return "assets/"+fileName;
    }

    public InputStream createInput(String fileName) {
        InputStream is = null;
        try{
            is = new FileInputStream(sketchPath(fileName));
        }
        catch(Exception e){
            System.out.println(e);
        }
        return is;
    }
}