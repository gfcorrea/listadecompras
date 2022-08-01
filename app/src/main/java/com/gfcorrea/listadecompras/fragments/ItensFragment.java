package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ItensAdapter;
import com.gfcorrea.listadecompras.controller.ItemController;
import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.entity.ItemLista;
import com.gfcorrea.listadecompras.viewmodel.ListaSelecionadaVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ItensFragment extends Fragment {
    private TextView textViewTituloItens;
    private Button buttonVoltar;
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
        buttonVoltar        = v.findViewById(R.id.buttonVoltarItens);

        AddFAB              = v.findViewById(R.id.floatingActionButtonAddItem);

        ListaSelecionadaVM listaSelecionadaVM = new ViewModelProvider(requireActivity()).get(ListaSelecionadaVM.class);

        ItemController controller = new ItemController();

        ItensAdapter itemAdapter = new ItensAdapter( controller.itensDaListaID(listaSelecionadaVM.getId()));

        recyclerViewItens.setAdapter(itemAdapter);
        textViewTituloItens.setText("Lista: " + listaSelecionadaVM.getNome());

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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