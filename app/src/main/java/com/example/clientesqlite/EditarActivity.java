package com.example.clientesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientesqlite.db.DbClientes;
import com.example.clientesqlite.entidades.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtRuc, txtRepresentante, txtDireccion,txtTelefono,
            txtProductos, txtCredito;
    Button btnGuarda,btnEditar,btnEliminar;
    //FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
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
        btnEditar.setVisibility(View.INVISIBLE);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setVisibility(View.INVISIBLE);

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

        DbClientes dbClientes = new DbClientes(EditarActivity.this);
        cliente = dbClientes.verClientes(id);

        if(cliente !=null){
            txtNombre.setText(cliente.getNombre());
            txtRuc.setText(cliente.getRuc());
            txtRepresentante.setText(cliente.getRepresentante());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtProductos.setText(cliente.getProductos());
            txtCredito.setText(cliente.getCredito());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtRuc.getText().toString().equals("") && !txtRepresentante.getText().toString().equals("") && !txtDireccion.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtProductos.getText().toString().equals("") &&!txtCredito.getText().toString().equals("")){
                    correcto = dbClientes.editarCliente(id, txtNombre.getText().toString(),txtRuc.getText().toString(),txtRepresentante.getText().toString(),
                            txtDireccion.getText().toString(),txtTelefono.getText().toString(),txtProductos.getText().toString(),txtCredito.getText().toString());
                    if(correcto){
                        Toast.makeText(EditarActivity.this, "Registro Modificado", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "Error al Modificado Registro", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(EditarActivity.this, "Llenar los campos obligatorios", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity2.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
