package com.gfcorrea.listadecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gfcorrea.listadecompras.adapter.ListasAdapter;
import com.gfcorrea.listadecompras.vm.ListaSelecionadaVM;
import com.gfcorrea.listadecompras.vm.ListaVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment {

   RecyclerView RecyclerViewLista;
   ListasAdapter adaptador;
   FloatingActionButton buttonAdicionar;

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
        ListaSelecionadaVM listaSelecionadaVM = new ViewModelProvider(requireActivity()).get(ListaSelecionadaVM.class);

        adaptador = new ListasAdapter( vmodel.Listas_getAll(), listaSelecionadaVM );

        RecyclerViewLista.setAdapter(adaptador);

        RecyclerViewLista.addItemDecoration(
                new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));

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