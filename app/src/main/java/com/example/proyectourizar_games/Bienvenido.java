package com.example.proyectourizar_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }
    public void tercera(View v){
        Intent sig = new Intent(this,Inicio_sesion.class);


        startActivity(sig);
    }
}