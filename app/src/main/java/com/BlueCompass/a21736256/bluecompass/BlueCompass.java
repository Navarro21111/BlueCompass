package com.BlueCompass.a21736256.bluecompass;

import android.app.Application;

import com.BlueCompass.a21736256.bluecompass.javabean.PlayaItem;
import com.BlueCompass.a21736256.bluecompass.javabean.Usuario;



public class BlueCompass extends Application {
   Usuario user;
   PlayaItem playa;
    public void onCreate(){
        super.onCreate();
    }

    public Usuario getUser() {
        return user;
    }


    public void setUser(Usuario user) {
        this.user = user;
    }

    public PlayaItem getPlaya() {
        return playa;
    }

    public void setPlaya(PlayaItem playa) {
        this.playa = playa;
    }
}
