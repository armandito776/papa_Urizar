package com.example.proyectourizar_games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Inicio_sesion extends AppCompatActivity {

    boolean isBarActive = false;
    ConstraintLayout barraLateral_ContenedorPrincipal;
    EditText usuario, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        barraLateral_ContenedorPrincipal = (ConstraintLayout) findViewById(R.id.barraLateral_ContenededorPrincipal);

        usuario = (EditText) findViewById(R.id.etUsuario);
        contraseña = (EditText) findViewById(R.id.etPassword);

    }
    public void registro(View v){
        Intent sig = new Intent(this, Registro.class);

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

    public void verifyUser (View view)
    {
        AdminOpenHelper Ad = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        if (usuario.getText().toString().isEmpty() || contraseña.getText().toString().isEmpty()) { return; }

        String check_if_user_exists = "SELECT user, pass, email FROM Usuarios WHERE user="
                + "'" + usuario.getText().toString() +"' AND pass=" + "'" + contraseña.getText().toString() +"'";

        try (Cursor Query_check = Bd.rawQuery(check_if_user_exists, null)) {
            if (!Query_check.moveToFirst()) {
                Toast.makeText(this, "El Nombre De Usuario O Contraseña Son Incorrectos", Toast.LENGTH_LONG).show();
                return;
            }
            Intent tienda_pantalla = new Intent(this, Tienda.class);
            tienda_pantalla.putExtra("userEmail", Query_check.getString(2));
            startActivity(tienda_pantalla);
        }
    }

    public void sidebar_Tienda(View view)
    {
        Intent tienda_ = new Intent(this, Tienda.class);
        tienda_.putExtra("userEmail", "");
        startActivity(tienda_);
    }

    public void sidebar_Library(View view)
    {
        Intent library_ = new Intent(this, Biblioteca.class);
        library_.putExtra("userEmail", "");
        startActivity(library_);
    }

    public void sidebar_User(View view)
    {
        Intent user_ = new Intent(this, Usuario.class);
        user_.putExtra("userEmail", "");
        startActivity(user_);
    }

    public void sidebar_Login(View view)
    {
        Intent login_ = new Intent(this, Inicio_sesion.class);

        startActivity(login_);
    }

    public void sidebar_register(View view)
    {
        Intent register_ = new Intent(this, Registro.class);

        startActivity(register_);
    }


}