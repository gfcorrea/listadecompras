package com.example.listadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItensTeste> itens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CriarItens();



    }


    public void CriarItens(){
        int a = 1;
        ItensTeste meuItem = new ItensTeste("Item "+ a, "Este é o item " + a);
        itens.add(meuItem);
        a++;

        meuItem = new ItensTeste("Item "+ a, "Este é o item " + a);
        itens.add(meuItem);
        a++;

        meuItem = new ItensTeste("Item "+ a, "Este é o item " + a);
        itens.add(meuItem);
        a++;

        meuItem = new ItensTeste("Item "+ a, "Este é o item " + a);
        itens.add(meuItem);
        a++;

        meuItem = new ItensTeste("Item "+ a, "Este é o item " + a);
        itens.add(meuItem);
        a++;

        meuItem = new ItensTeste("Item "+ a, "Este é o item " + a);
        itens.add(meuItem);
        a++;
    }
}