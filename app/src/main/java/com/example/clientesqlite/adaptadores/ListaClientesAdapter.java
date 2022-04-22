package com.example.clientesqlite.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientesqlite.R;
import com.example.clientesqlite.VerActivity2;
import com.example.clientesqlite.entidades.Clientes;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {

    ArrayList<Clientes> listaClientes;

    public  ListaClientesAdapter(ArrayList<Clientes> listaClientes){
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente,null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombre.setText(listaClientes.get(position).getNombre());
        holder.viewRuc.setText(listaClientes.get(position).getRuc());
        holder.viewRepresentante.setText(listaClientes.get(position).getRepresentante());
        holder.viewDireccion.setText(listaClientes.get(position).getDireccion());
        holder.viewTelefono.setText(listaClientes.get(position).getTelefono());
        holder.viewProductos.setText(listaClientes.get(position).getProductos());
        holder.viewCredito.setText(listaClientes.get(position).getCredito());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewRuc, viewRepresentante, viewDireccion, viewTelefono, viewProductos, viewCredito;
        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewRuc = itemView.findViewById(R.id.viewRuc);
            viewRepresentante = itemView.findViewById(R.id.viewRepresentante);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewTelefono= itemView.findViewById(R.id.viewTelefono);
            viewProductos = itemView.findViewById(R.id.viewProductos);
            viewCredito = itemView.findViewById(R.id.viewCredito);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity2.class);
                    intent.putExtra("ID",listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
