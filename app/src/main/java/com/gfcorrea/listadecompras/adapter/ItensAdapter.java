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

public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.ItensViewHolder> {

    private List<ItemListaModel> lista;
    private ItemViewModel itemViewModel;


    public ItensAdapter(ItemViewModel itemViewModel) {
        this.itemViewModel = itemViewModel;
        this.lista = itemViewModel.getLista();
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

        String id    =  String.valueOf(lista.get(position).getId());

        textViewItemProduto.setText( lista.get(position).getProduto());
        textViewItemQuantidade.setText( "Qtd: " + String.valueOf(lista.get(position).getQuantidade()));
        textViewItemValor.setText( "Vlr: R$ " + precision.format( lista.get(position).getPreco() ) + " "  );
        textViewItemTotal.setText( "Total: R$ " + precision.format( lista.get(position).getValor_total() ) + " "  );
        checkBoxMarcado.setChecked(lista.get(position).isMarcado());

        buttonExcluirItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemRepository itemRepository = new ItemRepository();
                itemRepository.apagarID(Integer.parseInt(id));

                lista.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                itemViewModel.atualizaTotal();

                Toast.makeText(holder.itemView.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        checkBoxMarcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemRepository itemRepository = new ItemRepository();
                itemRepository.atualizarMarcacao(lista.get(position).getId(), checkBoxMarcado.isChecked());
            }
        });

    }

    public class ItensViewHolder extends RecyclerView.ViewHolder {
        public ItensViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
