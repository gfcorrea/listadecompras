package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ListaViewHolder> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");
    private List<ListaModel> listaModel;
    private ListaViewModel listaViewModel;

    public ListasAdapter(ListaViewModel listaViewModel) {
        //listaViewModel = new ViewModelProvider(owner).get(ListaViewModel.class);
        this.listaViewModel = listaViewModel;
        this.listaModel = listaViewModel.getAll();
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
        TextView lblNomeLista = holder.itemView.findViewById(R.id.lblNomeLista);
        TextView lblTotalLista = holder.itemView.findViewById(R.id.lblTotalLista);
        TextView lblDateLista = holder.itemView.findViewById(R.id.lblDateLista);
        ImageView buttonExcluirLista = holder.itemView.findViewById(R.id.buttonExcluirLista);
        DecimalFormat precision = new DecimalFormat("0.00");

        int id = listaModel.get(position).getId();
        double total = listaModel.get(position).getValor_total();

        lblNomeLista.setText(listaModel.get(position).getNome());
        lblTotalLista.setText("Total: R$ " + precision.format(total));
        lblDateLista.setText(simpleDateFormat.format(listaModel.get(position).getData()));

        buttonExcluirLista.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                listaViewModel.deleteByID(id);
                listaModel.remove(position);

                notifyDataSetChanged();
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
        public void onClick(View view) {
            listaViewModel.setId(listaModel.get(getAdapterPosition()).getId());
            listaViewModel.setNome(listaModel.get(getAdapterPosition()).getNome());

            Navigation.findNavController(view).navigate(R.id.navigateToItensFragment);

        }

    }

}
