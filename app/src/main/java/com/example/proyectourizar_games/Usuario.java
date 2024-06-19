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
        userName = getIntent().getStringExtra("userName");
        barraLateral_ContenedorPrincipal = findViewById(R.id.barraLateral_ContenededorPrincipal);
        all_content_user = findViewById(R.id.all_content_user);

        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        etCorreo = findViewById(R.id.etCorreo);

        if (userEmail.isEmpty() || userName.isEmpty())
        {

            Toast.makeText(this, "Debes Iniciar Sesion Para Ver Tu Cuenta", Toast.LENGTH_LONG).show();
            return;
        }

        AdminOpenHelper Ad = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        String get_user_password = "SELECT pass FROM Usuarios WHERE email="
                + "'"+userEmail+"'";

        Cursor Query_get_user_password = Bd.rawQuery(get_user_password, null);

        if (!Query_get_user_password.moveToFirst())
        {
            Toast.makeText(this, "La Contraseña No Fue Encontrada", Toast.LENGTH_LONG).show();
            return;
        }

        etUsuario.setText(userName);
        etContraseña.setText(Query_get_user_password.getString(0));
        etCorreo.setText(userEmail);

        Query_get_user_password.close();
        Bd.close();

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
        if (etUsuario.getText().toString().isEmpty())
        {
            etUsuario.setError("No Dejes Este Campo Vacio");
            return;
        }

        if (etUsuario.getText().length() < 5)
        {
            etUsuario.setError("El Usuario Debe Tener 5 Caracteres O Mas");
            return;
        }

        AdminOpenHelper Ad = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        String new_userName = etUsuario.getText().toString().trim();

        String check_if_user_exists = "SELECT email FROM Usuarios WHERE user="
                + "'"+new_userName+"'";

        Cursor Query_check = Bd.rawQuery(check_if_user_exists, null);
        if (Query_check.moveToFirst())
        {
            Toast.makeText(this, "El usuario: " + new_userName + " Ya existe", Toast.LENGTH_LONG).show();
            return;
        }
        Query_check.close();

        ContentValues newuser_data = new ContentValues();
        newuser_data.put("user", new_userName);

        int check_update = Bd.update("Usuarios", newuser_data, "email=" +"'" +userEmail +"'", null);
        Bd.close();
        if (check_update != 1)
        {
            Toast.makeText(this,"Algo ha salido mal...", Toast.LENGTH_LONG).show();
            return;
        }
        userName = etUsuario.getText().toString();
        Toast.makeText(this,"Nuevo Usuario: " + etUsuario.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void changePassword(View view)
    {
        if (etContraseña.getText().toString().isEmpty()) {
            etContraseña.setError("No Dejes Este Campo Vacio");
            return;
        }

        if (etContraseña.getText().length() < 5)
        {
            etContraseña.setError("La Contraseña Debe Tener 5 Caracteres O Mas");
            return;
        }

        AdminOpenHelper Ad = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        ContentValues newuser_data = new ContentValues();
        newuser_data.put("pass", etContraseña.getText().toString());

        int check_update = Bd.update("Usuarios", newuser_data, "email=" +"'" +userEmail +"'", null);
        Bd.close();
        if (check_update != 1)
        {
            Toast.makeText(this,"Algo ha salido mal...", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,"Nueva Contraseña: " + etContraseña.getText().toString(), Toast.LENGTH_LONG).show();

    }

    public void changeEmail(View view)
    {
        String email_format = "[a-zA-Z][\\w-]{1,20}@\\w{2,20}\\.\\w{3,3}$";
        if (etCorreo.getText().toString().isEmpty())
        {
            etCorreo.setError("No Dejes El Campo Vacio");
            return;
        }

        if (!etCorreo.getText().toString().matches(email_format))
        {
            etCorreo.setError("El Correo No Es Valido");
            return;
        }

        AdminOpenHelper Ad_user = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd_user = Ad_user.getWritableDatabase();

        AdminOpenHelper Ad_selectedgames = new AdminOpenHelper(this, "selectedGames", null, 1);
        SQLiteDatabase Bd_selectedGames = Ad_selectedgames.getWritableDatabase();

        String check_if_user_exists = "SELECT email FROM Usuarios WHERE email="
                + "'"+etCorreo.getText().toString()+"'";

        Cursor Query_check = Bd_user.rawQuery(check_if_user_exists, null);
        if (Query_check.moveToFirst())
        {
            Toast.makeText(this, "El Correo Ya Existe", Toast.LENGTH_LONG).show();
            return;
        }
        Query_check.close();

        ContentValues newemail_data_user, newemail_data_selectedGames;
        newemail_data_user = new ContentValues();
        newemail_data_selectedGames = new ContentValues();

        newemail_data_user.put("email", etCorreo.getText().toString());

        int update_email_user_check = Bd_user.update("Usuarios", newemail_data_user, "email=" +"'" +userEmail+"'", null);

        Bd_user.close();
        if (update_email_user_check != 1)
        {
            Toast.makeText(this,"Algo Ha Salido Mal...", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this,"Nuevo Correo: " + etCorreo.getText().toString(), Toast.LENGTH_LONG).show();
        System.out.println(userEmail);
        newemail_data_selectedGames.put("userEmail", etCorreo.getText().toString());
        Bd_selectedGames.update("selectedGames", newemail_data_selectedGames, "userEmail=" +"'" +userEmail+"'", null);

        Bd_selectedGames.close();
        userEmail = etCorreo.getText().toString();

    }

    public void deleteAcc(View view)
    {
        AdminOpenHelper Ad_user = new AdminOpenHelper(this, "Usuarios", null, 1);
        SQLiteDatabase Bd_user = Ad_user.getWritableDatabase();

        AdminOpenHelper Ad_selectedgames = new AdminOpenHelper(this, "selectedGames", null, 1);
        SQLiteDatabase Bd_selectedGames = Ad_selectedgames.getWritableDatabase();

        int delete_user_acc_tbl = Bd_user.delete("Usuarios", "email="+"'" +userEmail+"'", null);
        if (delete_user_acc_tbl != 1)
        {
            Toast.makeText(this,"La Cuenta No Pudo Ser Eliminada...", Toast.LENGTH_LONG).show();
            Bd_user.close();
            return;
        }
        Bd_selectedGames.delete("selectedGames", "userEmail="+"'" +userEmail+"'", null);

        Toast.makeText(this,"La Cuenta Ha Sido Eliminada (Recuerda Volver A Iniciar Sesion)", Toast.LENGTH_LONG).show();

        etCorreo.setText("");
        etUsuario.setText("");
        etContraseña.setText("");

        Bd_selectedGames.close();
        Bd_user.close();

        Intent inicio_sesion = new Intent(this, Inicio_sesion.class);
        startActivity(inicio_sesion);
    }

    public void sidebar_Tienda(View view)
    {
        Intent tienda_ = new Intent(this, Tienda.class);
        userName = etUsuario.getText().toString();
        tienda_.putExtra("userEmail", userEmail);
        tienda_.putExtra("userName", userName);
        startActivity(tienda_);
    }

    public void sidebar_Library(View view)
    {
        Intent library_ = new Intent(this, Biblioteca.class);
        library_.putExtra("userEmail", userEmail);
        library_.putExtra("userName", userName);
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