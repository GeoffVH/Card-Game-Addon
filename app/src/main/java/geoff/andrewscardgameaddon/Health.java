package geoff.andrewscardgameaddon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Health extends AppCompatActivity {

    Persistance values;
    ImageView imageSign;
    boolean sign;
    ImageButton plus, altPlus, minus, altMinus;
    TextView value, turns, centerpart;
    int[] temp;
    Button bMinus, bPositive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        delcare();

        Intent intent = getIntent();
        values = (Persistance)intent.getSerializableExtra("Persist");
        setupValues();
    }

    public void delcare(){
        sign = true;
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
    }


    ///******************************************///
    //Swaps alpha of two buttons.
    static public void SwapAlpha(Button half, Button full){
        half.setAlpha(.5f);
        full.setAlpha(1f);
    }


    ///******************************************///
    //Updates Values to reflect on both UI and inside the code.
    public void setupValues(){
        temp = values.getTrackHealth();
        temp = Set.setupValuesHelper(0, temp, value);
        temp = Set.setupValuesHelper(1, temp, turns);
    }


    ///******************************************///
    //Controls the positive negative buttons
    //Notifies program which is picked and has the selection reflected on UI.
    public void positiveNegative(View v){

        switch(v.getId()) {
            case R.id.button4:
                sign = false;
                imageSign.setImageResource(R.drawable.minus);
                SwapAlpha(bPositive, bMinus);
                break;

            case R.id.button3:
                sign = true;
                imageSign.setImageResource(R.drawable.plus);
                SwapAlpha(bMinus, bPositive);
                break;
        }
    }


    ///******************************************///
    //Controls the numbers on the UI.
    public void incrimentValues(View v){

        switch(v.getId()) {
            case R.id.button5:
                temp = Set.incrimentValuesAddition(0,value, temp);
                break;

            case R.id.button7:
                temp = Set.incrimentValuesSubtraction(0, value, temp);
                break;

            case R.id.button6:
                temp = Set.incrimentValuesAddition(1,turns, temp);
                break;

            case R.id.button8:
                temp = Set.incrimentValuesSubtraction(1, turns, temp);
                break;
        }
    }

    ///******************************************///
    //Exits out of the screen, and updates the newly changed information.
    public void closing(View v){
        
        Intent intent = new Intent(this, Home.class);

        temp = Set.closingSign(temp, sign);
        values.setTrackHealth(temp);
        intent.putExtra("Persist", values);
        startActivity(intent);
        finish();
    }
}
