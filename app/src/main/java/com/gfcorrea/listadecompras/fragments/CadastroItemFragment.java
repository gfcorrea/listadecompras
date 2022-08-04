package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}