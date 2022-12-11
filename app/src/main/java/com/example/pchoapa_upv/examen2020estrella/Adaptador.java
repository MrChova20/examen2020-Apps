package com.example.pchoapa_upv.examen2020estrella;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderDatos> {

    String[] listDatos;

    public Adaptador(String[] listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos[position]);
    }

    @Override
    public int getItemCount() {
        return listDatos.length;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tiempo;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tiempo = (TextView) itemView.findViewById(R.id.segundos);

        }
        public void asignarDatos(String datos) {
            tiempo.setText(datos);
        }
    }
}
