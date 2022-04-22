package com.example.clientesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientesqlite.db.DbClientes;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtRuc, txtRepresentante, txtDireccion, txtTelefono,txtProductos, txtCredito;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtRuc = findViewById(R.id.txtRuc);
        txtRepresentante = findViewById(R.id.txtRepresentante);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtProductos = findViewById(R.id.txtProductos);
        txtCredito = findViewById(R.id.txtCredito);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbClientes dbClientes = new DbClientes(NuevoActivity.this);
                long id = dbClientes.insertarCliente(txtNombre.getText().toString(), txtRuc.getText().toString(), txtRepresentante.getText().toString(),txtDireccion.getText().toString(),
                        txtTelefono.getText().toString(), txtProductos.getText().toString(),txtCredito.getText().toString());

                if(id>0){
                    Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private  void limpiar(){
        txtNombre.setText("");
        txtRuc.setText("");
        txtRepresentante.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtProductos.setText("");
        txtCredito.setText("");

    }
}