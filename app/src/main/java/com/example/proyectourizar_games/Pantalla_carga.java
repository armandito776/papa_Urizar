package com.example.proyectourizar_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Pantalla_carga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        TextView tvGames = findViewById(R.id.tvGames);
        TextView tvUrizar = findViewById(R.id.tvUrizar);
        TextView tvGuion = findViewById(R.id.tvGuion);
        TextView tvBy = findViewById(R.id.tvBy);
        TextView tvUrizars = findViewById(R.id.tvUrizars);
        ImageView imageLogo = findViewById(R.id.imageLogo);

        tvGames.setAnimation(animacion2);
        tvUrizar.setAnimation(animacion2);
        tvGuion.setAnimation(animacion2);

        tvBy.setAnimation(animacion2);
        tvUrizars.setAnimation(animacion2);
        imageLogo.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Pantalla_carga.this,Bienvenido.class);
                startActivity(intent);
                finish();
            }
        }, 3600);
    }
}