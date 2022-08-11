package com.gfcorrea.listadecompras.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gfcorrea.listadecompras.R;
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

        return new ItensViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItensViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView textViewItemProduto, textViewItemQuantidade, textViewItemValor, textViewItemTotal;
        CheckBox checkBoxMarcado;
        DecimalFormat precision = new DecimalFormat("0.00");

        textViewItemProduto = holder.itemView.findViewById(R.id.textViewItemProduto);
        textViewItemQuantidade = holder.itemView.findViewById(R.id.textViewItemQuantidade);
        textViewItemValor = holder.itemView.findViewById(R.id.textViewItemValor);
        textViewItemTotal = holder.itemView.findViewById(R.id.textViewItemTotal);
        checkBoxMarcado = holder.itemView.findViewById(R.id.checkBoxMarcado);

        ImageView buttonExcluirItem = holder.itemView.findViewById(R.id.buttonExcluirItem);

        textViewItemProduto.setText(lista.get(position).getProduto());
        textViewItemQuantidade.setText("Qtd: " + lista.get(position).getQuantidade());
        textViewItemValor.setText("Vlr: R$ " + precision.format(lista.get(position).getPreco()) + " ");
        textViewItemTotal.setText("Total: R$ " + precision.format(lista.get(position).getValor_total()) + " ");
        checkBoxMarcado.setChecked(lista.get(position).isMarcado());

        buttonExcluirItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                itemViewModel.deleteByID(lista.get(position).getId());

                lista.remove(position);
                notifyDataSetChanged();

                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        checkBoxMarcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executor.execute(()->{
                    ItemRepository itemRepository = new ItemRepository();
                    itemRepository.atualizarMarcacao(lista.get(position).getId(), checkBoxMarcado.isChecked());
                });
            }
        });

    }

    public class ItensViewHolder extends RecyclerView.ViewHolder {
        public ItensViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
