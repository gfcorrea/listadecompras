package com.gfcorrea.listadecompras.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.ArrayList;
import java.util.List;

public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ListaViewHolder> {

    private List<Lista> lista = new ArrayList<>();

    public ListasAdapter(List<Lista> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_recycler, parent, false);
        ListaViewHolder mvh = new ListaViewHolder(itemView);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        TextView tID   = holder.itemView.findViewById(R.id.textViewID);
        TextView tNome  = holder.itemView.findViewById(R.id.textViewNome);
        TextView tTotal = holder.itemView.findViewById(R.id.textViewTotal);

        String id    =  String.valueOf(lista.get(position).id);
        String total =  String.valueOf(lista.get(position).valor_total);

        tID.setText( id );
        tNome.setText(lista.get(position).nome);
        tTotal.setText( total );
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder{

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
