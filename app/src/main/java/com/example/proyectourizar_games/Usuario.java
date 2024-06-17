package com.example.proyectourizar_games;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Usuario extends AppCompatActivity {

    boolean isBarActive = false;
    ConstraintLayout barraLateral_ContenedorPrincipal, all_content_user;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        userEmail = getIntent().getStringExtra("userEmail");
        barraLateral_ContenedorPrincipal = findViewById(R.id.barraLateral_ContenededorPrincipal);
        all_content_user = findViewById(R.id.all_content_user);
        if (userEmail.isEmpty()) {
            all_content_user.setVisibility(View.GONE);
            Toast.makeText(this, "Debes iniciar sesion para ver tu cuenta", Toast.LENGTH_LONG).show();
        }

    }
    public void crearBarraLateral(View view)
    {
        if (isBarActive)
        {
            barraLateral_ContenedorPrincipal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_to_left));
            barraLateral_ContenedorPrincipal.setVisibility(View.GONE);
            isBarActive = false;
            return;
        }

        barraLateral_ContenedorPrincipal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_to_right));
        barraLateral_ContenedorPrincipal.setVisibility(View.VISIBLE);
        isBarActive = true;

    }
}