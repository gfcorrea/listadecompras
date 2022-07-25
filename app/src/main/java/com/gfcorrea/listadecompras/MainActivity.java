package com.gfcorrea.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button buttonAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                CadastroListaFragment fragment = new CadastroListaFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });
    }

}