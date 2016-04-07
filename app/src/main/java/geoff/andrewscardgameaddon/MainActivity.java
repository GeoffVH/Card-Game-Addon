package geoff.andrewscardgameaddon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Persistance values = new Persistance();
    TextView Health, Damage, Speed;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Health = (TextView)findViewById(R.id.textView5);
        Damage = (TextView)findViewById(R.id.textView6);
        Speed = (TextView)findViewById(R.id.textView7);
        name = (EditText)findViewById(R.id.editText);
    }

    ///******************************************///
    //Controls the addition buttons on the UI
    public void add(View v){

        switch(v.getId()) {
            case R.id.imageButton:
                values.setHealth(values.getHealth() +5);
                Set.modTextView(Health, values.getHealth());
                break;

            case R.id.imageButton3:
                values.setDamage(values.getDamage()+5);
                Set.modTextView(Damage, values.getDamage());
                break;

            case R.id.imageButton5:
                values.setSpeed(values.getSpeed() +5);
                Set.modTextView(Speed, values.getSpeed());
                break;
        }
    }

    ///******************************************///
    //Controls the subtraction buttons on the UI
    public void subtract(View v){

        switch(v.getId()) {
            case R.id.imageButton2:
                values.setHealth(values.getHealth()-1);
                Set.modTextView(Health, values.getHealth());
                break;

            case R.id.imageButton4:
                values.setDamage(values.getDamage()-1);
                Set.modTextView(Damage, values.getDamage());
                break;

            case R.id.imageButton6:

                values.setSpeed(values.getSpeed()-1);
                Set.modTextView(Speed, values.getSpeed());
                break;
        }
    }

    ///******************************************///
    //Stores finalized data and begins next activity.
    public void Switch(View v){

        values.setName(name.getText().toString());
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("Persist", values);
        startActivity(intent);
        finish();
    }
}
