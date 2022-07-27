package com.gfcorrea.listadecompras.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.entity.ItemLista;

import java.util.List;

public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.ItensViewHolder> {

    List<ItemLista> lista;

    public ItensAdapter(List<ItemLista> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_recycler, parent, false);
        ItensAdapter.ItensViewHolder mvh = new ItensAdapter.ItensViewHolder(itemView);

        return mvh;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItensViewHolder holder, int position) {
        TextView textViewItemProduto;
        TextView textViewItemQuantidade;

        textViewItemProduto = holder.itemView.findViewById(R.id.textViewItemProduto);
        textViewItemQuantidade = holder.itemView.findViewById(R.id.textViewItemQuantidade);

        textViewItemProduto.setText( lista.get(position).getProduto());
        textViewItemQuantidade.setText( String.valueOf(lista.get(position).getQuantidade()));
    }

    public class ItensViewHolder extends RecyclerView.ViewHolder {
        public ItensViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
