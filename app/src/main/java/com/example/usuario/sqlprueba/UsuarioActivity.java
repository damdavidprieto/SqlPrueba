package com.example.usuario.sqlprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UsuarioActivity extends AppCompatActivity {

    private Usuario usuario;

    private TextView tvTitulo,tvTipoUsu;
    private Spinner sTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        if(getIntent().getExtras().getSerializable("usuario")!=null) {
            usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        }else{
            finish();
        }

        tvTitulo=(TextView)findViewById(R.id.tvUsuarioTitle);
        tvTipoUsu=(TextView)findViewById(R.id.tvTipoUsu);

        sTipo=(Spinner)findViewById(R.id.spEstado);

        if(!tvTitulo.getText().toString().contains(usuario.getNombre())){
            tvTitulo.setText(tvTitulo.getText()+" "+usuario.getNombre());
            tvTipoUsu.setText(tvTipoUsu.getText()+" "+usuario.getTipo());
        }

    }
}
