package com.example.proyectourizar_games;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    boolean isBarActive = false;
    ConstraintLayout barraLateral_ContenedorPrincipal;
    EditText etUsuario, etPass, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        barraLateral_ContenedorPrincipal = (ConstraintLayout) findViewById(R.id.barraLateral_ContenededorPrincipal);

        etUsuario=(EditText) findViewById(R.id.edUsuario);
        etPass=(EditText) findViewById(R.id.edPass);
        etEmail=(EditText) findViewById(R.id.edEmail);

    }

    public void inicio(View v){
        Intent sig = new Intent(this, Inicio_sesion.class);

        startActivity(sig);
    }

    public void insertUser(View view) {
        AdminOpenHelper Ad = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        if (etUsuario.getText().toString().isEmpty() || etPass.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()) {
            etUsuario.setError("Debes llenar todos los campos");
            etEmail.setError("Debes llenar todos los campos");
            etPass.setError("Debes llenar todos los campos");
            return;
        }
        String userName = etUsuario.getText().toString().trim();

        String email_format = "[a-zA-Z][\\w-]{1,20}@\\w{2,20}\\.\\w{3,3}$";
        if (!etEmail.getText().toString().matches(email_format)) {
            etEmail.setError("El Correo No Es Valido");
            return;
        }

        if (etPass.getText().length() < 5)
        {
            etPass.setError("La Contraseña Debe Tener 5 Caracteres O Mas");
            return;
        }

        if (etUsuario.getText().length() < 5)
        {
            etUsuario.setError("El Usuario Debe Tener 5 Caracteres O Mas");
            return;
        }

        String check_if_email_already_exists = "SELECT email FROM Usuarios WHERE email="
                + "'" +etEmail.getText().toString()+"'";

        String check_if_user_already_exists = "SELECT email FROM Usuarios WHERE user="
                + "'" +userName+"'";

        Cursor Query_check_email = Bd.rawQuery(check_if_email_already_exists, null);
        Cursor Query_check_user = Bd.rawQuery(check_if_user_already_exists, null);

        if (Query_check_user.moveToFirst())
        {
            etUsuario.setError("El Usuario Ya Existe");
            return;
        }

        if (Query_check_email.moveToFirst())
        {
            etEmail.setError("El Correo Ya Existe");
            return;
        }
        Query_check_email.close();
        Query_check_user.close();

        ContentValues userData = new ContentValues();
        userData.put("user", userName);
        userData.put("pass", etPass.getText().toString());
        userData.put("email", etEmail.getText().toString());

        Bd.insert("Usuarios", null, userData);
        Bd.close();
        Toast.makeText(this, "Usuario: " + userName, Toast.LENGTH_LONG).show();
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



