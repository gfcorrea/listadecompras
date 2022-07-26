package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.BdConection;
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
    public void onBindViewHolder(@NonNull ListaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView tID   = holder.itemView.findViewById(R.id.textViewID);
        TextView tNome  = holder.itemView.findViewById(R.id.textViewNome);
        TextView tTotal = holder.itemView.findViewById(R.id.textViewTotal);
        Button buttonExcluirLista = holder.itemView.findViewById(R.id.buttonExcluirLista);

        String id    =  String.valueOf(lista.get(position).id);
        String total =  String.valueOf(lista.get(position).valor_total);

        tID.setText( id );
        tNome.setText(lista.get(position).nome);
        tTotal.setText( total );

        buttonExcluirLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaDao listaDao = BdConection.getConexao().listaDao();
                listaDao.deleteById( Integer.parseInt(id));


                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                lista.remove(position);
                notifyItemRemoved(position);
            }
        });
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
