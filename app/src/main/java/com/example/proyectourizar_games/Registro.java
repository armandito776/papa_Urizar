package com.example.proyectourizar_games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Registro extends AppCompatActivity {

    boolean isBarActive = false;
    ConstraintLayout barraLateral_ContenedorPrincipal;
    EditText edUsuario, edPass, edEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        barraLateral_ContenedorPrincipal = (ConstraintLayout) findViewById(R.id.barraLateral_ContenededorPrincipal);

        edUsuario=(EditText) findViewById(R.id.edUsuario);
        edPass=(EditText) findViewById(R.id.edPass);
        edEmail=(EditText) findViewById(R.id.edEmail);

    }

    public void inicio(View v){
        Intent sig = new Intent(this, Inicio_sesion.class);

        startActivity(sig);
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
    /*
        public void alta(View v){
            AdminOpenHelper ad = new AdminOpenHelper(this,"bdCuenta", null, 1);
            SQLiteDatabase bd=ad.getWritableDatabase();

            String Usuario=edUsuario.getText().toString();
            String Pass = edPass.getText().toString();
            String Email =edEmail.getText().toString();


            ContentValues reg =new ContentValues();


            reg.put("Usuario", Usuario);
            reg.put("Pass",Pass);
            reg.put("Email",Email);

            bd.insert("Cuenta",null,reg);
            bd.close();
            edEmail.setText("");
            edPass.setText("");
            edUsuario.setText("");

            Toast.makeText(this,"DATOS INGRESADOS",Toast.LENGTH_LONG).show();
        }
        */


        }



