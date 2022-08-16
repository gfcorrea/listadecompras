package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.databinding.ItensRecyclerBinding;
import com.gfcorrea.listadecompras.repository.ItemRepository;
import com.gfcorrea.listadecompras.model.ItemListaModel;
import com.gfcorrea.listadecompras.viewmodel.ItemViewModel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.ItensViewHolder> {

    private List<ItemListaModel> lista;
    private ItemViewModel itemViewModel;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public ItensAdapter(ItemViewModel itemViewModel) {
        this.itemViewModel = itemViewModel;
        this.lista = itemViewModel.getLista();
    }

    @NonNull
    @Override
    public ItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_recycler, parent, false);

        return new ItensViewHolder(
                ItensRecyclerBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                )
        );
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItensViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DecimalFormat precision = new DecimalFormat("0.00");

        holder.binding.lblItemProduto.setText(lista.get(position).getProduto());
        holder.binding.lblItemQuantidade.setText("Qtd: " + lista.get(position).getQuantidade());
        holder.binding.lblItemValor.setText("Vlr: R$ " + precision.format(lista.get(position).getPreco()) + " ");
        holder.binding.lblItemTotal.setText("Total: R$ " + precision.format(lista.get(position).getValor_total()) + " ");
        holder.binding.chkMarcado.setChecked(lista.get(position).isMarcado());

        holder.binding.btnExcluirItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                itemViewModel.deleteByID(lista.get(position).getId());

                lista.remove(position);
                notifyDataSetChanged();

                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        holder.binding.chkMarcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executor.execute(()->{
                    ItemRepository itemRepository = new ItemRepository();
                    itemRepository.atualizarMarcacao(lista.get(position).getId(), holder.binding.chkMarcado.isChecked());
                });
            }
        });

    }

    public class ItensViewHolder extends RecyclerView.ViewHolder {
        private ItensRecyclerBinding binding;
        public ItensViewHolder(ItensRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
