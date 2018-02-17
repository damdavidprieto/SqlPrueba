package com.example.usuario.sqlprueba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button bIngreso,bRegistro;
    private EditText eCorreo,eCon;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = new AdminSQLiteOpenHelper(this, "coleccionUsu", null, 1);

        eCorreo=(EditText)findViewById(R.id.etCorreo);
        eCon=(EditText)findViewById(R.id.etContrasena);

        bIngreso=(Button) findViewById(R.id.bIngresar);
        bIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
        bRegistro=(Button) findViewById(R.id.bRegistrar);

        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent().setClass(MainActivity.this, RegActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    public void ingresar(){
        bd=admin.getWritableDatabase();
        String correo=eCorreo.getText().toString();
        String contrasena=eCon.getText().toString();
        Cursor cursor=bd.rawQuery("select * from usuarios where correo ='" +correo+"'", null);
        if(cursor.moveToNext()){
            Usuario usuario=new Usuario(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            Toast.makeText(this,"Login correcto",Toast.LENGTH_LONG).show();
            Intent mainIntent = new Intent().setClass(MainActivity.this, UsuarioActivity.class);
            mainIntent.putExtra("usuario", usuario);
            startActivity(mainIntent);
        }else{
            Toast.makeText(this,"Login incorrecto",Toast.LENGTH_LONG).show();
            eCon.setText("");
        }
        bd.close();
    }
}
