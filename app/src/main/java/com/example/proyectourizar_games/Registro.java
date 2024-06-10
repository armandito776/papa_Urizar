package com.example.proyectourizar_games;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    EditText edUsuario, edPass, edEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        edUsuario=(EditText) findViewById(R.id.edUsuario);
        edPass=(EditText) findViewById(R.id.edPass);
        edEmail=(EditText) findViewById(R.id.edEmail);

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



