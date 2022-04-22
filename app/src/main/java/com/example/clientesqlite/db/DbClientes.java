package com.example.clientesqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.clientesqlite.entidades.Clientes;

import java.util.ArrayList;

public class DbClientes extends DbHelper {

    Context context;

    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCliente(String nombre, String ruc, String representante, String direccion, String telefono, String productos, String credito){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("ruc",ruc);
            values.put("representante",representante);
            values.put("direccion",direccion);
            values.put("telefono",telefono);
            values.put("productos",productos);
            values.put("credito",credito);

            id = db.insert(TABLE_CONTACTOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Clientes> mostrarClientes(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursorClientes = null;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);
        if(cursorClientes.moveToFirst()){
            do{
                cliente = new Clientes();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setRuc(cursorClientes.getString(2));
                cliente.setRepresentante(cursorClientes.getString(3));
                cliente.setDireccion(cursorClientes.getString(4));
                cliente.setTelefono(cursorClientes.getString(5));
                cliente.setProductos(cursorClientes.getString(6));
                cliente.setCredito(cursorClientes.getString(7));
                listaClientes.add(cliente);
            }while(cursorClientes.moveToNext());

        }
        cursorClientes.close();
        return listaClientes;
    }


    public Clientes verClientes(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Clientes cliente = null;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);
        if(cursorClientes.moveToFirst()){

                cliente = new Clientes();

            cliente.setId(cursorClientes.getInt(0));
            cliente.setNombre(cursorClientes.getString(1));
            cliente.setRuc(cursorClientes.getString(2));
            cliente.setRepresentante(cursorClientes.getString(3));
            cliente.setDireccion(cursorClientes.getString(4));
            cliente.setTelefono(cursorClientes.getString(5));
            cliente.setProductos(cursorClientes.getString(6));
            cliente.setCredito(cursorClientes.getString(7));

        }
        cursorClientes.close();
        return cliente;
    }


    public boolean editarCliente(int id,String nombre,String ruc, String representante, String direccion, String telefono, String productos, String credito){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre = '" + nombre + "', ruc = '" + ruc + "', representante = '" + representante + "', direccion = '" + direccion + "',telefono = '" + telefono + "',productos = '" + productos + "', credito = '" + credito + "' WHERE id = '" +id+ "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }



    public boolean eliminarCliente(int id){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id = '" + id + "'" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }


}
