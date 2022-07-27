package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.viewmodel.ListaSelecionadaVM;


public class ItensFragment extends Fragment {
    private TextView textViewTituloItens;

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

        ListaSelecionadaVM listaSelecionadaVM = new ViewModelProvider(requireActivity()).get(ListaSelecionadaVM.class);

        textViewTituloItens = v.findViewById(R.id.TextViewTituloItens);
        textViewTituloItens.setText("Lista: " + listaSelecionadaVM.getNome());

        return v;
    }

}