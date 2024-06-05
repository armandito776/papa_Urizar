package com.example.proyectourizar_games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Biblioteca extends AppCompatActivity {
    ImageView imgBtnBarra;
    ConstraintLayout barraLateral_ContenedorPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);
        imgBtnBarra = (ImageView) findViewById(R.id.imgBarra);
        barraLateral_ContenedorPrincipal = (ConstraintLayout) findViewById(R.id.barraLateral_ContenededorPrincipal);
        barraLateral_ContenedorPrincipal.setMaxWidth(122);

    }

    public void crearBarraLateral(View view)
    {

    }
}