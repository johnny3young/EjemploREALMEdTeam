package com.example.ejemplorealmedteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplorealmedteam.Models.Item;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    EditText name, age;
    Button save, viewall, query;
    Realm realm;
    RealmAsyncTask realmAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        name  = findViewById(R.id.name);
        age = findViewById(R.id.age);
        save = findViewById(R.id.save);
        viewall = findViewById(R.id.viewData);
        query = findViewById(R.id.query);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Con esto creamos los objetos que queremos guardar
                final String n = name.getText().toString();
                final int a = Integer.valueOf(age.getText().toString());
                //Usamos una funcion de JAVA UUID para que nos cree un ID
                final String id = UUID.randomUUID().toString();

                realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
                                                                   @Override
                                                                   public void execute(Realm realm) {
                                                                       Item item = realm.createObject(Item.class);
                                                                       item.setID(id);
                                                                       item.setName(n);
                                                                       item.setAge(a);
                                                                   }
                                                                   //Usamos los 2 métodos que nos da REALM para saber cuando sale bien o mal el proceso de creación
                                                               }, new Realm.Transaction.OnSuccess() {
                                                                   @Override
                                                                   public void onSuccess() {
                                                                       Toast.makeText(MainActivity.this, "Creado Exitosamente", Toast.LENGTH_SHORT).show();
                                                                   }
                                                               }, new Realm.Transaction.OnError() {
                                                                   @Override
                                                                   public void onError(Throwable error) {
                                                                       Toast.makeText(MainActivity.this, "No se guardó el registro", Toast.LENGTH_SHORT).show();
                                                                   }
                                                               }
                );
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para que se vean los datos los pedimos por medio de um MÉTODO (display) y se debe crear un LISTVIEW pero los mostraremos en el LOG
                //Le pedimos los datos que queremos a REALM para mostrarlos en display
                RealmResults<Item> items = realm.where(Item.class).findAll();
                display(items);
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    public void display (RealmResults<Item> items){
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item:items){
            stringBuilder.append(" \n id :" + item.getID());
            stringBuilder.append(" \n name: " + item.getName());
            stringBuilder.append(" \n age: " + item.getAge());
        }
        Log.e("DATA", stringBuilder.toString());
    }

    //Para definir una query (las condiciones para buscar o filtrar), hacemos lo siguiente
    public void query (){
        RealmQuery<Item> realmQuery = realm.where(Item.class);
        realmQuery.greaterThan("age",10);
        realmQuery.contains("name","johnny");

        RealmResults<Item> items = realmQuery.findAll();
        display(items);
    }
}
