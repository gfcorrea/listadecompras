package com.gfcorrea.listadecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gfcorrea.listadecompras.adapter.ListasAdapter;
import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.database.BdConection;
import com.gfcorrea.listadecompras.entity.Lista;
import com.gfcorrea.listadecompras.vm.ListaVM;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

   RecyclerView RecyclerViewLista;
   ListasAdapter adaptador;

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

        ListaVM vmodel = new ViewModelProvider(requireActivity()).get(ListaVM.class);

        adaptador = new ListasAdapter( vmodel.Listas_getAll() );

        RecyclerViewLista.setAdapter(adaptador);

        RecyclerViewLista.addItemDecoration(
                new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));



        return v;
    }


}