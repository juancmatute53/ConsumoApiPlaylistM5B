package com.example.clientesqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clientesqlite.db.DbClientes;
import com.example.clientesqlite.entidades.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity2 extends AppCompatActivity {

    EditText txtNombre, txtRuc, txtRepresentante, txtDireccion,  txtTelefono, txtProductos, txtCredito;
    Button btnGuarda,btnEditar,btnEliminar;
    //FloatingActionButton fabEditar, fabEliminar;

    Clientes cliente;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver2);

        txtNombre = findViewById(R.id.txtNombre);
        txtRuc = findViewById(R.id.txtRuc);
        txtRepresentante = findViewById(R.id.txtRepresentante);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtProductos = findViewById(R.id.txtProductos);
        txtCredito = findViewById(R.id.txtCredito);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbClientes dbClientes = new DbClientes(VerActivity2.this);
        cliente = dbClientes.verClientes(id);

        if(cliente !=null){
            txtNombre.setText(cliente.getNombre());
            txtRuc.setText(cliente.getRuc());
            txtRepresentante.setText(cliente.getRepresentante());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtProductos.setText(cliente.getProductos());
            txtCredito.setText(cliente.getCredito());
            btnGuarda.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtRuc.setInputType(InputType.TYPE_NULL);
            txtRepresentante.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtProductos.setInputType(InputType.TYPE_NULL);
            txtCredito.setInputType(InputType.TYPE_NULL);
        }
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity2.this,EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity2.this);
                builder.setMessage("¿Esta seguro de eliminar este Proveedor?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbClientes.eliminarCliente(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}