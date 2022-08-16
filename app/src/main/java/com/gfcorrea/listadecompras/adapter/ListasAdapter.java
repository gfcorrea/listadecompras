package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.databinding.ListaRecyclerBinding;
import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ListaViewHolder> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");
    private List<ListaModel> listaListas;
    private ListaViewModel listaViewModel;

    public ListasAdapter(ListaViewModel listaViewModel) {
        this.listaViewModel = listaViewModel;
        this.listaListas = listaViewModel.getAll();
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaViewHolder(
                ListaRecyclerBinding.inflate(LayoutInflater.from( parent.getContext() ), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DecimalFormat precision = new DecimalFormat("0.00");

        int id = listaListas.get(position).getId();
        double total = listaListas.get(position).getValor_total();

        holder.binding.lblNomeLista.setText(listaListas.get(position).getNome());
        holder.binding.lblTotalLista.setText("Total: R$ " + precision.format(total));
        holder.binding.lblDateLista.setText(simpleDateFormat.format(listaListas.get(position).getData()));

        holder.binding.buttonExcluirLista.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                listaViewModel.deleteByID(id);
                listaListas.remove(position);

                notifyDataSetChanged();
                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaListas.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ListaRecyclerBinding binding;

        public ListaViewHolder(ListaRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        //Implementa View.OnClickListener
        @Override
        public void onClick(View view) {
            listaViewModel.setId(listaListas.get(getAdapterPosition()).getId());
            listaViewModel.setNome(listaListas.get(getAdapterPosition()).getNome());

            Navigation.findNavController(view).navigate(R.id.navigateToItensFragment);

        }

    }

}
