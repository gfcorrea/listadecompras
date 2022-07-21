package com.gfcorrea.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.database.BdConection;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private TextView TextViewNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextViewNome = findViewById(R.id.TextViewNome);

        AppDatabase db = BdConection.getConexao(getApplicationContext());

        ListaDao listaDao = db.listaDao();

        Lista l = new Lista();
        l.id = 1;
        l.nome = "Lista 1";
        l.valor_total = 2;

        listaDao.insertAll(l);

        List<Lista> listas = listaDao.getAll();

        //TextViewNome.setText(listas.get(0).nome);
    }
}