package geoff.andrewscardgameaddon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Buff extends AppCompatActivity {

    Persistance values;
    ImageView imageSign;
    boolean sign, speedOrDamage;
    ImageButton plus, altPlus, minus, altMinus;
    TextView value, turns, centerpart;
    int[] temp;
    Button bMinus, bPositive, bDamage, bSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buff);

        delcare();
        Intent intent = getIntent();
        values = (Persistance)intent.getSerializableExtra("Persist");
        switchTempToDamage(false);
        setupValues();
    }

    public void delcare(){
        sign = true;
        speedOrDamage = true;
        imageSign = (ImageView) findViewById(R.id.imageView);

        plus = (ImageButton) findViewById(R.id.button5);
        altPlus = (ImageButton) findViewById(R.id.button6);
        minus = (ImageButton) findViewById(R.id.button7);
        altMinus = (ImageButton) findViewById(R.id.button8);
        value = (TextView) findViewById(R.id.textView);
        centerpart = (TextView) findViewById(R.id.textView11);
        turns = (TextView) findViewById(R.id.textView12);

        bMinus = (Button) findViewById(R.id.button4);
        bPositive = (Button) findViewById(R.id.button3);
        SwapAlpha(bMinus, bPositive);

        bDamage = (Button) findViewById(R.id.button9);
        bSpeed = (Button) findViewById(R.id.imageButton11);
        SwapAlpha(bSpeed, bDamage);
    }


    ///******************************************///
    //modifies the screen UI, and keeps data from going negative.
    public void setupValues() {
        temp = Set.setupValuesHelper(0, temp, value);
        temp = Set.setupValuesHelper(1, temp, turns);
    }


    ///******************************************///
    //Controls the speed or damage selection on the UI
    public void speedDamage(View v){

        switch(v.getId()) {
            case R.id.imageButton11:
                speedOrDamage = false;
                switchTempToSpeed(false);
                switchTrackerHelper();
                SwapAlpha(bDamage,bSpeed);
                break;

            case R.id.button9:
                speedOrDamage = true;
                switchTempToDamage(false);
                switchTrackerHelper();
                SwapAlpha(bSpeed,bDamage);
                break;
        }
    }


    ///******************************************///
    //Controls the positive negative buttons
    public void positiveNegative(View v){

        switch(v.getId()) {
            case R.id.button4:
                switchPositiveNegative(0, bPositive, bMinus, R.drawable.minus);
                break;

            case R.id.button3:
                switchPositiveNegative(1, bMinus, bPositive, R.drawable.plus);
                break;
        }
    }


    ///******************************************///
    //Changes positive negative and reflects the change on the UI.
    public void switchPositiveNegative(int i, Button Half, Button Full, int ID){
        temp[2] = i;
        switchPositiveNegative(Half, Full, ID);
    }


    ///******************************************///
    //Decides if positive negative need to be switched on the UI.
    public void switchTrackerHelper(){
        if (temp[2]!=0) switchPositiveNegative(bMinus, bPositive, R.drawable.plus);
        else switchPositiveNegative(bPositive, bMinus, R.drawable.minus);
    }


    ///******************************************///
    //Switches positive negative on the UI.
    public void switchPositiveNegative(Button Half, Button Full, int ID){
        SwapAlpha(Half, Full);
        imageSign.setImageResource(ID);
    }


    ///******************************************///
    //Swaps alpha of two buttons.
    static public void SwapAlpha(Button half, Button full){
        half.setAlpha(.5f);
        full.setAlpha(1f);
    }


    ///******************************************///
    //Controls the numbers on the UI.
    public void incrimentValues(View v){

        switch(v.getId()) {
            case R.id.button5:
                temp = Set.incrimentValuesAddition(0, value, temp);
                break;

            case R.id.button7:
                temp = Set.incrimentValuesSubtraction(0, value, temp);
                break;

            case R.id.button6:
                temp = Set.incrimentValuesAddition(1, turns, temp);
                break;

            case R.id.button8:
                temp = Set.incrimentValuesSubtraction(1, turns, temp);
                break;
        }
    }


    ///******************************************///
    //Helper function to load the damage tracker.
    public void switchTempToDamage(boolean check){
        temp = values.getTrackDamage();
        switchHelper(check);
        values.setTrackDamage(temp);
    }


    ///******************************************///
    //Helper function to load the speed tracker.
    public void switchTempToSpeed(boolean check){
        temp = values.getTrackSpeed();
        switchHelper(check);
        values.setTrackSpeed(temp);
    }


    ///******************************************///
    //Given a bool, either calls to reset the UI values, or submits finalized temp for a sign change.
    public void switchHelper(boolean check){
        if(!check) setupValues();
        else {temp = Set.closingSign(temp);}
    }


    ///******************************************///
    //Resets both damage and speed tracker to 0, and reflects the change in the UI.
    public void reset(View v){
        int[] clean = {0,0,1};
        setupValues();
        values.setTrackDamage(clean);
        values.setTrackSpeed(clean);
    }


    ///******************************************///
    //Updates the newly changed information for final setup then Exits out of the screen.
    public void closing(View v){
        Intent intent = new Intent(this, Home.class);
        switchTempToDamage(true);
        switchTempToSpeed(true);

        intent.putExtra("Persist", values);
        startActivity(intent);
        finish();
    }
}
