package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ItensAdapter;
import com.gfcorrea.listadecompras.controller.ItemController;

import com.gfcorrea.listadecompras.databinding.FragmentItensBinding;
import com.gfcorrea.listadecompras.viewmodel.ItemVM;
import com.gfcorrea.listadecompras.viewmodel.ListaVM;


public class ItensFragment extends Fragment {
    private FragmentItensBinding binding;

    public ItensFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItensBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ListaVM listaVM = new ViewModelProvider(requireActivity()).get(ListaVM.class);
        binding.TextViewTituloItens.setText(listaVM.getNome());

        ItemVM itemVM = new ViewModelProvider(requireActivity()).get(ItemVM.class);

        final Observer<String> itemObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {

                binding.textViewTotalItens.setText(s);
            }
        } ;

        final Observer<String> numItemObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewNumItens.setText(s);
            }
        } ;

        itemVM.getTotalGeral().observe(getActivity(), itemObserver);
        itemVM.getNumItens().observe(getActivity(), numItemObserver);

        itemVM.setId_lista(listaVM.getId());

        ItemController itemController = new ItemController();
        itemVM.setLista(itemController.itensDaListaID(listaVM.getId()));

        ItensAdapter itemAdapter = new ItensAdapter(itemVM);

        binding.RecyclerViewItens.setAdapter(itemAdapter);

        binding.RecyclerViewItens.addItemDecoration(
                new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));

        itemVM.atualizaTotal();

        binding.buttonVoltarItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaVM.atualizaTotal();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HomeFragment fragment = new HomeFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });

        binding.btnNovoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                CadastroItemFragment fragment = new CadastroItemFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

}