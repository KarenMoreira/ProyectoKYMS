package com.facci.proyectokyms;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityKYMS extends AppCompatActivity {

    DBhelper dbSQLITE;

    EditText txtID, txtNombre,txtApellido,txtRecintoElectoral,txtañoNacimiento;

    Button btnInsertar, btnModificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_kyms);


        dbSQLITE = new DBhelper(this);


    }

    public void InsertarClick(View v){

        txtID = (EditText) findViewById(R.id.textID);
        txtNombre = (EditText) findViewById(R.id.textNombre);
        txtApellido = (EditText) findViewById(R.id.textApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.textRecintoElectoral);
        txtañoNacimiento = (EditText) findViewById(R.id.txtañoNacimiento);

        boolean estaInsertado = dbSQLITE.Insertar(txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecintoElectoral.getText() .toString(),Integer.parseInt(txtañoNacimiento.getText().toString());

        if (estaInsertado )
            Toast.makeText(MainActivityKYMS.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityKYMS.this,"Lo sentimos Ocurrió un Error",Toast.LENGTH_SHORT).show();



    }
    public void verTodosClick(View v){
        Cursor res = dbSQLITE.selecVerTodos();
        if (res.getCount()== 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getInt(3)+"\n\n");
            buffer.append("Año Nacimiento : "+res.getInt(4)+"\n\n");
        }
        mostrarMensaje("votantes", buffer.toString());

    }

    public void mostrarMensaje(String titulo,String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();

    }
    public void modificarRegistroClick(View v){


        txtID = (EditText) findViewById(R.id.textID);
        txtNombre = (EditText) findViewById(R.id.textNombre);
        txtApellido = (EditText) findViewById(R.id.textApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.textRecintoElectoral);
        txtañoNacimiento = (EditText) findViewById(R.id.txtañoNacimiento);



        boolean estaAcutalizado = dbSQLITE.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),Integer.parseInt(txtañoNacimiento.getText().toString());


        if (estaAcutalizado == true){
            Toast.makeText(MainActivityKYMS.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityKYMS.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }



    public void eliminarRegistroClick(View v){


        txtID = (EditText) findViewById(R.id.textID);


        Integer registrosEliminados = dbSQLITE.eliminarRegistro(txtID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityKYMS.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityKYMS.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }

}






