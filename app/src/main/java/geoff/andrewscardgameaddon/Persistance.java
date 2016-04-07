package geoff.andrewscardgameaddon;

/**
 * Created by Geoff on 3/10/2016.
 * This ADT class contains all current variables that is supposed to persist in between activities.
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class Persistance implements Serializable{
    String name;
    int[] trackHealth, trackSpeed, trackDamage;
    int turn, health, damage, speed;

     public Persistance(){
         turn =0;
         health =0;
         damage =0;
         speed =0;

         //[0] = values, [1] = loop, [2] = Negative/Positive
         trackHealth = new int[] {0,0};
         trackSpeed = new int[] {0,0,1};
         trackDamage = new int[] {0,0,1};
    }

    ///******************************************///
    // All Get requests.
    public int[] getTrackSpeed(){return trackSpeed;}
    public int[] getTrackHealth(){return trackHealth;}
    public int[] getTrackDamage(){return trackDamage;}
    public String getName() {return name;}
    public int getTurn() {return turn;}
    public int getHealth(){return health;}
    public int getDamage() {return damage;}
    public int getSpeed() {return speed;}

    ///******************************************///
    //All Set Requests.
    public void setHealth(int x){health = x;}
    public void setSpeed(int x){speed = x;}
    public void setDamage(int x){damage = x;}
    public void setName(String s) {name = s;}

    public void setTrackHealth(int[] temp){
        trackHealth[0] = temp[0];
        trackHealth[1] = temp[1];
    }

    public void setTrackSpeed(int[] temp){
        trackSpeed[0] = temp[0];
        trackSpeed[1] = temp[1];
    }

    public void setTrackDamage(int[] temp){
        trackDamage[0] = temp[0];
        trackDamage[1] = temp[1];
    }

    ///******************************************///
    // Specialized requests.
    public void incrimentTurn(){turn++;}
    public void incrimentHealth(){health++;}
    public void decrementHealth() {health--;}

}
