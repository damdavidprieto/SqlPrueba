package com.example.usuario.sqlprueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 16/02/2018.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table usuarios(correo text primary key,contrasena text,nombre text, telefono text, cod_tipo int, cod_estado)");
        sqLiteDatabase.execSQL("create table tipo(cod_tipo int primary key,tipo text)");
        sqLiteDatabase.execSQL("create table estado(cod_estado int primary key,estado text)");

        sqLiteDatabase.execSQL("insert into tipo values (0,'administrador')");
        sqLiteDatabase.execSQL("insert into tipo values (1,'usuario')");

        sqLiteDatabase.execSQL("insert into estado values (0,'dispnible')");
        sqLiteDatabase.execSQL("insert into estado values (1,'ausente')");
        sqLiteDatabase.execSQL("insert into estado values (1,'no disponible')");

        sqLiteDatabase.execSQL("insert into usuarios values ('system','system','system',000000000,0,0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
