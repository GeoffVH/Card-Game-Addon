package geoff.andrewscardgameaddon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.transition.Transition;
import geoff.andrewscardgameaddon.Set;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    int[] trackSpeed, trackHealth, trackDamage;
    TextView damage, speed, turn,trackDamageHome, trackSpeedHome;
    Button Health;
    Persistance values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        values = (Persistance)intent.getSerializableExtra("Persist");
        declare();
        Set.modTextView(damage, values.getDamage());
        Set.modTextView(speed, values.getSpeed());
        Set.modTextView(Health, values.getHealth());
        Set.Turn(turn, values.getTurn());
    }

    public void declare(){
        damage = (TextView) findViewById(R.id.Damage_value);
        speed = (TextView) findViewById(R.id.speed_value);
        Health = ((Button) findViewById(R.id.imageButton9));
        turn = (TextView) findViewById(R.id.textView10);
        trackDamageHome = (TextView) findViewById(R.id.textView14);
        trackSpeedHome = (TextView) findViewById(R.id.textView15);

        trackDamage = values.getTrackDamage();
        trackHealth = values.getTrackHealth();
        trackSpeed = values.getTrackSpeed();

        ((TextView)findViewById(R.id.home_characterName)).setText(values.getName());
        updateCheck();
    }

    ///******************************************///
    //Starts the health screen.
    public void optionsHealth(View v){
        Intent intent = new Intent(this, Health.class);
        intent.putExtra("Persist", values);
        startActivity(intent);
        finish();
    }

    ///******************************************///
    //Starts the buff screen
    public void optionsBuff(View v){
        Intent intent = new Intent(this, Buff.class);
        intent.putExtra("Persist", values);
        startActivity(intent);
        finish();
    }

    ///******************************************///
    //Adds and subtracts one health, tied to + and - on home screen.
    public void modHealth(View v){

        switch(v.getId()) {
            case R.id.imageButton7:
                values.incrimentHealth();
                Set.modTextView(Health, values.getHealth());
                break;

            case R.id.imageButton8:
                values.decrementHealth();
                Set.modTextView(Health, values.getHealth());
                break;
        }
    }

    ///******************************************///
    //Ends the turn and updating all information.
    public void endTurn(View v){
        incrimentTurn();
        updateHealth(values.getTrackHealth());
        updateDamage(values.getTrackDamage());
        updateSpeed(values.getTrackSpeed());
        updateCheck();
    }

    ///******************************************///
    //Updates health based on how much should be gain/lost per turn.
    public void updateHealth(int [] temp){

        if(temp[1]<=0)
            Set.modTextView(Health, values.getHealth());
        else {
            values.setHealth(values.getHealth() + temp[0]);
            Set.modTextView(Health, values.getHealth());
        }

        temp[1]--;
        values.setTrackHealth(temp);
    }

    ///******************************************///
    //Updates damage based on how much should be gain/lost per turn.
    public void updateDamage(int [] temp){

        if(temp[1]<=0)
            Set.modTextView(damage, values.getDamage());
        else {
            values.setDamage(values.getDamage() + temp[0]);
            Set.modTextView(damage, values.getDamage());
        }

        temp[1]--;
        values.setTrackDamage(temp);
    }

    ///******************************************///
    //Updates speed based on how much should be gain/lost per turn.
    public void updateSpeed(int [] temp){

        if(temp[1]<=0)
            Set.modTextView(speed, values.getSpeed());
        else {
            values.setSpeed(values.getSpeed() + temp[0]);
            Set.modTextView(speed, values.getSpeed());
        }

        temp[1]--;
        values.setTrackSpeed(temp);
    }

    ///******************************************///
    //updates the notice by speed/damage.
    public void updateCheck(){
        updateVisibility(values.getTrackDamage(),trackDamageHome);
        updateValue(values.getTrackDamage(), trackDamageHome);
        updateVisibility(values.getTrackSpeed(), trackSpeedHome);
        updateValue(values.getTrackSpeed(), trackSpeedHome);
    }

    ///******************************************///
    //Updates the text and values by speed/damage
    public void updateValue(int[] check, TextView v) {
        v.setText(" | " + Integer.toString(check[0]) + " for " + Integer.toString(check[1]) + " turns.");
    }

    ///******************************************///
    //updates weather the text by speed/damage should be shown and the correct colors.
    public void updateVisibility(int[] check, TextView v){
        v.setVisibility(View.INVISIBLE);

        if(check[1]>0) {
            if (check[0] > 0) {
                v.setVisibility(View.VISIBLE);
                v.setTextColor(Color.GREEN);
            } else {
                v.setVisibility(View.VISIBLE);
                v.setTextColor(Color.RED);
            }
        }
    }

    ///******************************************///
    //incriments the turn
    public void incrimentTurn(){
        values.incrimentTurn();
        Set.Turn(turn, values.getTurn());
    }
}
