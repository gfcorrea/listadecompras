package com.gfcorrea.listadecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gfcorrea.listadecompras.adapter.ListasAdapter;
import com.gfcorrea.listadecompras.vm.ListaVM;


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