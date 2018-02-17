package com.example.usuario.sqlprueba;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    private Button bAceptar,bCancelar;
    private EditText eCorreo,eContra,eTelefono,eNombre;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);

        eCorreo=(EditText)findViewById(R.id.etIcorreo);
        eContra=(EditText)findViewById(R.id.etIcontr);
        eTelefono=(EditText)findViewById(R.id.etItel);
        eNombre=(EditText)findViewById(R.id.etInombre);

        bAceptar=(Button) findViewById(R.id.btIaceptar);
        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
            }
        });

        bCancelar=(Button) findViewById(R.id.btIcancelar);
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void insertar(){
        bd=admin.getWritableDatabase();
        String correo=eCorreo.getText().toString();
        String contrasena=eContra.getText().toString();
        String telefono=eTelefono.getText().toString();
        String nombre=eNombre.getText().toString();

        ContentValues registro=new ContentValues();
        registro.put("correo", correo);
        registro.put("contrasena", contrasena);
        registro.put("nombre", nombre);
        registro.put("telefono", telefono);

        try {
            bd.insert("usuarios", null, registro);
            Toast.makeText(this, "Se inserto el registro", Toast.LENGTH_LONG).show();
            finish();
        }catch (Exception e){
            Toast.makeText(this, "No se cargó el registro en la BD", Toast.LENGTH_LONG).show();
        }

        bd.close();
    }
}
