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
import com.gfcorrea.listadecompras.repository.ItemRepository;

import com.gfcorrea.listadecompras.databinding.FragmentItensBinding;
import com.gfcorrea.listadecompras.viewmodel.ItemViewModel;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;


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

        ListaViewModel listaViewModel = new ViewModelProvider(requireActivity()).get(ListaViewModel.class);
        binding.TextViewTituloItens.setText(listaViewModel.getNome());

        ItemViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

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

        itemViewModel.getTotalGeral().observe(getActivity(), itemObserver);
        itemViewModel.getNumItens().observe(getActivity(), numItemObserver);

        itemViewModel.setId_lista(listaViewModel.getId());

        ItemRepository itemRepository = new ItemRepository();
        itemViewModel.setLista(itemRepository.itensDaListaID(listaViewModel.getId()));

        ItensAdapter itemAdapter = new ItensAdapter(itemViewModel);

        binding.RecyclerViewItens.setAdapter(itemAdapter);

        binding.RecyclerViewItens.addItemDecoration(
                new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));

        itemViewModel.atualizaTotal();

        binding.buttonVoltarItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaViewModel.atualizaTotal();

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