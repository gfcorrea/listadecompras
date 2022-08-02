package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.controller.ListaController;
import com.gfcorrea.listadecompras.entity.Lista;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroListaFragment extends Fragment {
    TextInputEditText textInputNome;
    Button buttonSalvarLista, buttonCancelarLista;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cadastro_lista, container, false);
        textInputNome = v.findViewById(R.id.TextInputNome);
        buttonSalvarLista = v.findViewById(R.id.buttonSalvarLista);
        buttonCancelarLista = v.findViewById(R.id.buttonCancelarLista);

        buttonSalvarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalvarLista();
            }
        });

        buttonCancelarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarHome();
            }
        });

        return v;
    }

    public void SalvarLista() {
        Lista lista = new Lista();
        lista.setNome(textInputNome.getText().toString());

        ListaController controller = new ListaController();
        controller.inserir(lista);

        voltarHome();
    }

    public void voltarHome(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
        fragmentTransaction.commit();
    }
}