package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.controller.ListaController;
import com.gfcorrea.listadecompras.fragments.ItensFragment;
import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.entity.Lista;
import com.gfcorrea.listadecompras.viewmodel.ListaVM;

import java.text.DecimalFormat;
import java.util.List;

public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ListaViewHolder> {

    private List<Lista> lista;
    ListaVM listaVM;

    public ListasAdapter( ListaVM listaVM ) {
        this.lista = listaVM.Listas_getAll();
        this.listaVM = listaVM;
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
        TextView tNome  = holder.itemView.findViewById(R.id.textViewNome);
        TextView tTotal = holder.itemView.findViewById(R.id.textViewTotal);
        Button buttonExcluirLista = holder.itemView.findViewById(R.id.buttonExcluirLista);
        DecimalFormat precision = new DecimalFormat("0.00");

        int id       =  lista.get(position).getId();
        double total =  lista.get(position).getValor_total();

        tNome.setText(lista.get(position).getNome());
        tTotal.setText( "R$ "+ precision.format(total) );

        buttonExcluirLista.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                ListaController listaController = new ListaController();
                listaController.excluirID(id);

                lista.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();

                listaVM.atualizaTotal();
                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        //Implementa View.OnClickListener
        @Override
        public void onClick(View v) {
            listaVM.setId(lista.get( getAdapterPosition()).getId());
            listaVM.setNome(lista.get( getAdapterPosition()).getNome());

            FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ItensFragment fragment = new ItensFragment();
            fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
            fragmentTransaction.commit();
        }

    }

}
