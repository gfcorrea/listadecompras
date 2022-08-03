package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ItensAdapter;
import com.gfcorrea.listadecompras.controller.ItemController;

import com.gfcorrea.listadecompras.viewmodel.ItemVM;
import com.gfcorrea.listadecompras.viewmodel.ListaVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ItensFragment extends Fragment {
    private TextView textViewTituloItens, textViewTotalItens, textViewNumItens;
    private ImageView buttonVoltar;
    private RecyclerView recyclerViewItens;
    private ItensAdapter adapter;
    private FloatingActionButton AddFAB;

    public ItensFragment() {
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

        View v = inflater.inflate(R.layout.fragment_itens, container, false);

        recyclerViewItens   = v.findViewById(R.id.RecyclerViewItens);
        textViewTituloItens = v.findViewById(R.id.TextViewTituloItens);
        textViewTotalItens  = v.findViewById(R.id.textViewTotalItens);
        textViewNumItens  = v.findViewById(R.id.textViewNumItens);
        buttonVoltar        = v.findViewById(R.id.buttonVoltarItens);

        AddFAB              = v.findViewById(R.id.floatingActionButtonAddItem);

        ListaVM listaVM = new ViewModelProvider(requireActivity()).get(ListaVM.class);
        textViewTituloItens.setText(listaVM.getNome());

        ItemVM itemVM = new ViewModelProvider(requireActivity()).get(ItemVM.class);

        final Observer<String> itemObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewTotalItens.setText(s);
            }
        } ;

        final Observer<String> numItemObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewNumItens.setText(s);
            }
        } ;


        ItemController itemController = new ItemController();

        itemVM.getTotalGeral().observe(getActivity(), itemObserver);
        itemVM.getNumItens().observe(getActivity(), numItemObserver);
        itemVM.setId_lista(listaVM.getId());
        itemVM.setLista(itemController.itensDaListaID(listaVM.getId()));

        ItensAdapter itemAdapter = new ItensAdapter(itemVM);

        recyclerViewItens.setAdapter(itemAdapter);

        recyclerViewItens.addItemDecoration(
                new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));


        itemVM.atualizaTotal();

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaVM.atualizaTotal();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HomeFragment fragment = new HomeFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });

        AddFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                CadastroItemFragment fragment = new CadastroItemFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });


        return v;
    }

}