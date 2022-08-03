package com.gfcorrea.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.fragments.HomeFragment;
import com.gfcorrea.listadecompras.viewmodel.ListaVM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase.setContexto(getApplicationContext());

        ListaVM vmodel = new ViewModelProvider(this).get(ListaVM.class);
        vmodel.atualizaTotal();
    }

}