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

import com.gfcorrea.listadecompras.viewmodel.ListaVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;



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

        ListaVM listaVM = new ViewModelProvider(requireActivity()).get(ListaVM.class);

        ItemController controller = new ItemController();

        ItensAdapter itemAdapter = new ItensAdapter( controller.itensDaListaID(listaVM.getId()));

        recyclerViewItens.setAdapter(itemAdapter);
        textViewTituloItens.setText("Lista: " + listaVM.getNome());


        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DecimalFormat precision = new DecimalFormat("0.00");

                ListaVM listaVM = new ViewModelProvider(getActivity()).get(ListaVM.class);
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