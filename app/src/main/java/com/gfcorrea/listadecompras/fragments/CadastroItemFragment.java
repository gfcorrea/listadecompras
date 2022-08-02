package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.controller.ItemController;
import com.gfcorrea.listadecompras.entity.ItemLista;
import com.gfcorrea.listadecompras.viewmodel.ListaVM;
import com.google.android.material.textfield.TextInputEditText;


public class CadastroItemFragment extends Fragment {
    TextInputEditText produto, quantidade, preco;

    Button buttonSalvarItem;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cadastro_item, container, false);

        buttonSalvarItem = v.findViewById(R.id.buttonSalvarItem);
        produto    = v.findViewById(R.id.EditProduto);
        quantidade = v.findViewById(R.id.EditQuantidade);
        preco  = v.findViewById(R.id.EditPreco);

        ListaVM listaVM = new ViewModelProvider(getActivity()).get(ListaVM.class);

        buttonSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemLista item = new ItemLista();
                item.setProduto( produto.getText().toString() );
                item.setQuantidade( Double.parseDouble(quantidade.getText().toString()) );
                item.setPreco( Double.parseDouble(preco.getText().toString()) );
                item.setValor_total( item.getPreco() * item.getQuantidade() );
                item.setId_lista( listaVM.getId() );

                ItemController controller = new ItemController();
                controller.inserir(item);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ItensFragment fragment = new ItensFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}