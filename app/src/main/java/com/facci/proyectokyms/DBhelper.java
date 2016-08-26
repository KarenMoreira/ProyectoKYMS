package com.facci.proyectokyms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PERSONAL on 25/08/2016.
 */
public class DBhelper extends SQLiteOpenHelper {


    public static final String DB_NOMBRE = " CNE_KYMS";
    public static final String TABLA_NOMBRE = " VOTANTES_KYMS";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO ELECTORAL";
    public static final String COL_5 = "AÑO DE NACIMIENTO";


    public DBhelper(Context context) {
        super(context, DB_NOMBRE, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create tabla %s (ID INTEGER PRIMARY KEY AUTOINCREMENT ,%s TEXT, %s TEXT,%s INTEGER)",TABLA_NOMBRE,COL_2,COL_3,COL_4,COL_5));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_NOMBRE));
        onCreate(db);

    }
    public boolean Insertar(String nombre, String apellido,String recintoElectoral, String añoNacimiento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoElectoral);
        contentValues.put(COL_5,añoNacimiento);
        Long resultado = db.insert(TABLA_NOMBRE,null,contentValues);


        if (resultado == -1)
            return false;
        else
            return true;

    }
    public Cursor selecVerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("selec * from %s", TABLA_NOMBRE),null);
        return res;


    }
    //Modifica un registro desde la base de datos
    public boolean modificarRegistro(String id,String nombre,String apellido,String recintoElectoral,String añoNacimiento){
        // Colocamos la base de datos en modo de escritura
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoElectoral);
        contentValues.put(COL_5,añoNacimiento);

        db.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});


        return true;
    }


    public Integer eliminarRegistro(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLA_NOMBRE,"id = ?",new String[]{id});

    }

}