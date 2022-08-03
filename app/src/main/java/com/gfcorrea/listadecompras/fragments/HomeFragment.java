package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ListasAdapter;
import com.gfcorrea.listadecompras.viewmodel.ListaVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment {

   RecyclerView RecyclerViewLista;
   ListasAdapter adaptador;
   FloatingActionButton buttonAdicionar;
   TextView valorTotal, textViewNumListas;

    public HomeFragment() {
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerViewLista =  v.findViewById(R.id.RecyclerViewLista);
        valorTotal        =  v.findViewById(R.id.textViewTotalGeral);
        textViewNumListas =  v.findViewById(R.id.textViewNumListas);

        ListaVM listaVM = new ViewModelProvider(requireActivity()).get(ListaVM.class);

        final Observer<String> valorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String valor) {
                valorTotal.setText(valor);
            }
        };

        final Observer<String> numListas = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String valor) {
                textViewNumListas.setText(valor);
            }
        };

        listaVM.getValorTotal().observe(getActivity(), valorObserver);
        listaVM.getNumListas().observe(getActivity(), numListas);

        adaptador = new ListasAdapter( listaVM );

        RecyclerViewLista.setAdapter(adaptador);

        RecyclerViewLista.addItemDecoration(
                new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));


        listaVM.atualizaTotal();

        buttonAdicionar = v.findViewById(R.id.ButtonMainAdd);

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                CadastroListaFragment fragment = new CadastroListaFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });

        return v;
    }


}