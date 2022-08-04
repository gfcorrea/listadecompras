package com.gfcorrea.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gfcorrea.listadecompras.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase.setContexto(getApplicationContext());

        //ListaVM vmodel = new ViewModelProvider(this).get(ListaVM.class);
        //vmodel.atualizaTotal();
    }

}