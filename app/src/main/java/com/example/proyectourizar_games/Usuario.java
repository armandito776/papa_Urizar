package com.example.proyectourizar_games;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Usuario extends AppCompatActivity {

    boolean isBarActive = false;
    ConstraintLayout barraLateral_ContenedorPrincipal, all_content_user;
    String userEmail, userName;
    EditText etUsuario, etContraseña, etCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        userEmail = getIntent().getStringExtra("userEmail");
        barraLateral_ContenedorPrincipal = findViewById(R.id.barraLateral_ContenededorPrincipal);
        all_content_user = findViewById(R.id.all_content_user);

        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        etCorreo = findViewById(R.id.etCorreo);

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

    public void changeUsername(View view)
    {
        if (etUsuario.getText().toString().isEmpty()) {
            etUsuario.setError("No Dejes Este Campo Vacio");
            return;
        }

        AdminOpenHelper Ad = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        ContentValues newuser_data = new ContentValues();
        newuser_data.put("user", etUsuario.getText().toString());
        int check_update = Bd.update("Usuarios", newuser_data, "email=" +"'" +userEmail +"'", null);
        Bd.close();
        if (check_update != 1) {
            Toast.makeText(this,"Algo ha salido mal...", Toast.LENGTH_LONG).show();
            return;
        }
        userName = etUsuario.getText().toString();
        Toast.makeText(this,"Nuevo Usuario: " + etUsuario.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void changePassword(View view)
    {

    }

    public void changeEmail(View view)
    {

    }

    public void sidebar_Tienda(View view)
    {
        Intent tienda_ = new Intent(this, Tienda.class);
        tienda_.putExtra("userEmail", userEmail);
        tienda_.putExtra("userName", userName);
        startActivity(tienda_);
    }

    public void sidebar_Library(View view)
    {
        Intent library_ = new Intent(this, Biblioteca.class);
        library_.putExtra("userEmail", userEmail);
        startActivity(library_);
    }

    public void sidebar_User(View view)
    {
        Intent user_ = new Intent(this, Usuario.class);
        user_.putExtra("userEmail", userEmail);
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