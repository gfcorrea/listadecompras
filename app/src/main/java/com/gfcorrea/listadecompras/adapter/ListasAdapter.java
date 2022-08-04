package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.fragments.ItensFragment;
import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;

import java.text.DecimalFormat;
import java.util.List;

public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ListaViewHolder> {

    private List<ListaModel> listaModel;
    private ListaViewModel listaViewModel;

    public ListasAdapter( ListaViewModel listaViewModel) {
        this.listaModel = listaViewModel.getAll();
        this.listaViewModel = listaViewModel;
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
        ImageView buttonExcluirLista = holder.itemView.findViewById(R.id.buttonExcluirLista);
        DecimalFormat precision = new DecimalFormat("0.00");

        int id       =  listaModel.get(position).getId();
        double total =  listaModel.get(position).getValor_total();

        tNome.setText(listaModel.get(position).getNome());
        tTotal.setText( "R$ "+ precision.format(total) );

        buttonExcluirLista.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                listaViewModel.deleteByID(id);

                listaModel.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();

                listaViewModel.atualizaTotal();
                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaModel.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        //Implementa View.OnClickListener
        @Override
        public void onClick(View v) {
            listaViewModel.setId(listaModel.get( getAdapterPosition()).getId());
            listaViewModel.setNome(listaModel.get( getAdapterPosition()).getNome());

            FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ItensFragment fragment = new ItensFragment();
            fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
            fragmentTransaction.commit();
        }

    }

}
