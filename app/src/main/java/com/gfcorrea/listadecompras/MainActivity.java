package com.gfcorrea.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.database.BdConection;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private TextView TextViewNome;
    Button buttonAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        //TextViewNome = findViewById(R.id.TextViewNome);

        AppDatabase db = BdConection.getConexao(getApplicationContext());

        ListaDao listaDao = db.listaDao();

        popularBanco(listaDao);

        List<Lista> listas = listaDao.getAll();

        //TextViewNome.setText(listas.get(0).nome);
*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragmentContainerView2, fragment);
        fragmentTransaction.commit();

        buttonAdicionar = findViewById(R.id.buttonAdicionar);
        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                NovaListaFragment fragment = new NovaListaFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });


    }

    public void popularBanco(ListaDao listaDao) {
        Lista l = new Lista();

        // l.id = 1;
        l.nome = "Lista nº 1";
        l.valor_total = 2;

        listaDao.insertAll(l);

        // l.id = 2;
        l.nome = "Lista nº 2";
        l.valor_total = 3;

        listaDao.insertAll(l);

        // l.id = 3;
        l.nome = "Lista nº 3";
        l.valor_total = 4;

        listaDao.insertAll(l);

        // l.id = 4;
        l.nome = "Lista nº 4";
        l.valor_total = 5;

        listaDao.insertAll(l);
    }
}