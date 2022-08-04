package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.repository.ListaRepository;
import com.gfcorrea.listadecompras.databinding.FragmentCadastroListaBinding;
import com.gfcorrea.listadecompras.model.ListaModel;


public class CadastroListaFragment extends Fragment {
    private FragmentCadastroListaBinding binding;

    public CadastroListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCadastroListaBinding.inflate(getLayoutInflater());
        View view  = binding.getRoot();

        binding.btnSalvarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaModel listaModel = new ListaModel();
                listaModel.setNome(binding.txtNomeLista.getText().toString());

                ListaRepository controller = new ListaRepository();
                controller.inserir(listaModel);

                voltarHome();
            }
        });

        binding.btnCancelarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarHome();
            }
        });

        return view;
    }

    public void voltarHome(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
        fragmentTransaction.commit();
    }
}