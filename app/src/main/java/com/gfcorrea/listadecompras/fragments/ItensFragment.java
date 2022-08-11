package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ItensAdapter;
import com.gfcorrea.listadecompras.model.ItemListaModel;

import com.gfcorrea.listadecompras.databinding.FragmentItensBinding;
import com.gfcorrea.listadecompras.viewmodel.ItemViewModel;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ItensFragment extends Fragment {
    private FragmentItensBinding binding;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

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
        itemViewModel.setId_lista(listaViewModel.getId());

        final Observer<String> itemObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewTotalItens.setText(s);
            }
        };

        final Observer<String> numItemObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewNumItens.setText(s);
            }
        };

        final Observer<List<ItemListaModel>> listItemObserver = new Observer<List<ItemListaModel>>() {
            @Override
            public void onChanged(List<ItemListaModel> itemListaModels) {
                ItensAdapter itemAdapter = new ItensAdapter(itemViewModel);
                binding.RecyclerViewItens.setAdapter(itemAdapter);

                executor.execute(() -> {
                    itemViewModel.atualizaTotal();
                });
            }
        };

        itemViewModel.getTotalGeral().observe(getActivity(), itemObserver);
        itemViewModel.getNumItens().observe(getActivity(), numItemObserver);
        itemViewModel.getItemListaModel().observe(getActivity(), listItemObserver);

        executor.execute(() -> {
            itemViewModel.atualizaLista();
        });

        binding.buttonVoltarItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                executor.execute(() -> {
                    itemViewModel.atualizaTotal();
                });

                Navigation.findNavController(view).navigate(R.id.navigateToHomeFromItensFragment);
            }
        });

        binding.btnNovoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToCadastroItemFragment);
            }
        });

        return view;
    }

}