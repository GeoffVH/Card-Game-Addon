package geoff.andrewscardgameaddon;

import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Geoff on 3/10/2016.
 * Global Helper class built to handle functions shared by multiple activities.
 */
public class Set {

    ///******************************************///
    //Modifies the text on a given textview/button to reflect the passed x value.
    static public void modTextView(TextView v, int x){v.setText(Integer.toString(x));}
    static public void modTextView(Button b, int x){b.setText(Integer.toString(x));}
    static public void Turn(TextView t, int x) {t.setText("Current Turn: " + Integer.toString(x));}

    ///******************************************///
    //This method should work but it dosn't because I don't know the right syntax yet.
    // The SwapAlpha function was added to both activiy classes instead.
    static public void SwapAlpha(Button half, Button full){
        half.setAlpha(.5f);
        full.setAlpha(1f);
    }

    ///******************************************///
    //Sets up the given textview to reflect temp[i] unless it's negative where it replaces it with 0.
    //If it found temp[i] to be negative, it will reset that value to 0 as well.
    static public int[] setupValuesHelper(int i, int[] temp, TextView v){

        if(temp[i]>=0) Set.modTextView(v, temp[i]);
        else{
            modTextView(v, 0);
            temp[i]=0;
        }
        return temp;
    }

    ///******************************************///
    //Switches from positive to negative on the value part of a given tracker array.
    static public int[] closingSign(int[] temp, boolean sign){
        if(sign==false){temp[0] = temp[0]*-1;}
        return temp;
    }
    static public int[] closingSign(int[] temp){
        if(temp[2]==0){temp[0] = temp[0]*-1;}
        return temp;
    }

    ///******************************************///
    //Helper function to incrimenting UI and actual value.
    static public int[] incrimentValuesAddition(int i, TextView v, int[] temp){
        temp[i]++;
        Set.modTextView(v, temp[i]);
        return temp;
    }

    ///******************************************///
    //Helper function to decrimenting UI and actual value.
    static public int[] incrimentValuesSubtraction(int i, TextView v, int[] temp){
        if(temp[i]!=0) temp[i]--;
        Set.modTextView(v, temp[i]);
        return temp;
    }


}
