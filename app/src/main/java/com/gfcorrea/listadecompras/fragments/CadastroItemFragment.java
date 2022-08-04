package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.model.ItemListaModel;
import com.gfcorrea.listadecompras.repository.ItemRepository;
import com.gfcorrea.listadecompras.databinding.FragmentCadastroItemBinding;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;



public class CadastroItemFragment extends Fragment {
    private FragmentCadastroItemBinding binding;

    public CadastroItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadastroItemBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();

        ListaViewModel listaViewModel = new ViewModelProvider(getActivity()).get(ListaViewModel.class);

        binding.btnSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validaCampos()) { return; }

                ItemListaModel item = new ItemListaModel();
                item.setProduto( binding.txtProduto.getText().toString() );
                item.setQuantidade( Double.parseDouble(binding.txtQuantidade.getText().toString()) );
                item.setPreco( Double.parseDouble(binding.txtPreco.getText().toString()) );
                item.setValor_total( item.getPreco() * item.getQuantidade() );
                item.setId_lista( listaViewModel.getId() );

                ItemRepository controller = new ItemRepository();
                controller.inserir(item);

                voltarItens();
            }
        });

        binding.btnCancelarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarItens();
            }
        });

        return v;
    }

    public void voltarItens(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ItensFragment fragment = new ItensFragment();
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
        fragmentTransaction.commit();
    }


    public boolean validaCampos() {
        Boolean retorno;

        retorno = binding.txtProduto.getText().toString().matches("[a-zA-Z0-9]{3,}.*");

        if (!retorno) {
            Toast.makeText(getContext(), "Nome do Produto inválido, por favor digite um nome com pelo menos 3 caracteres", Toast.LENGTH_SHORT).show();
            return retorno;
        }

        retorno = binding.txtQuantidade.getText().toString().matches("[0-9]+\\.{0,}[0-9]{0,}");
        if (!retorno) {
            Toast.makeText(getContext(), "Quantidade inválida", Toast.LENGTH_SHORT).show();
            return retorno;
        }

        retorno = binding.txtPreco.getText().toString().matches("[0-9]+\\.{0,}[0-9]{0,}");
        if (!retorno) {
            Toast.makeText(getContext(), "Preço inválido", Toast.LENGTH_SHORT).show();
            return retorno;
        }

        return retorno;
    }


}