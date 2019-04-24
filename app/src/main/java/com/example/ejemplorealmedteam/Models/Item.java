package com.example.ejemplorealmedteam.Models;

import io.realm.RealmObject;

//Para que este objero se pueda trabajar desde REALM, le hacemos herencia
public class Item extends RealmObject {

    private String ID;
    private String name;
    private int age;


    public Item(){

    }

    //Definimos un constructor
    public Item(String ID, String name, int age){
        this.ID = ID;
        this.name = name;
        this.age = age;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
