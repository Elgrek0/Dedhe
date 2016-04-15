/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paris
 */
public class Localization {

    String english;
    String greek;

    boolean showgreek=true;
    
    public Localization(String english) {
        this.english = english;
        greek=null;
    }
     public Localization(String english,String greek) {
        this.english = english;
        this.greek=greek;
    }
    
    @Override
    public String toString(){
        if(showgreek){
            if(greek!=null){
                return greek;
            }                
        }
        return english;
    }
    public void showenglish(){
        showgreek=false;
    }
    public void showgreek(){
        showgreek=true;
    }

}
