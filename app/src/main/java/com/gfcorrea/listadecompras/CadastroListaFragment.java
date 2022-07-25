package com.gfcorrea.listadecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.database.BdConection;
import com.gfcorrea.listadecompras.entity.Lista;
import com.google.android.material.textfield.TextInputEditText;


public class CadastroListaFragment extends Fragment {
    TextInputEditText nome;
    Button buttonSalvarLista;

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
        nome = v.findViewById(R.id.TextInputNome);
        buttonSalvarLista = v.findViewById(R.id.buttonSalvarLista);
        buttonSalvarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalvarLista();
            }
        });

        return v;
    }

    public void SalvarLista() {
        AppDatabase db = BdConection.getConexao(getActivity().getApplicationContext());

        ListaDao listaDao = db.listaDao();

        Lista lista = new Lista();
        lista.nome = nome.getText().toString();

        listaDao.insertAll(lista);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
        fragmentTransaction.commit();
    }
}