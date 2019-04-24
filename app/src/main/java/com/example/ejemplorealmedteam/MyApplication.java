package com.example.ejemplorealmedteam;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    //Creamos esta clase para hacer configuraciones en el proyecto


    @Override
    public void onCreate() {
        super.onCreate();
        //Iniciamos Realm en todo el proyecto
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
