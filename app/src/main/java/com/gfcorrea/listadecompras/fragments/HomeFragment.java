package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ListasAdapter;
import com.gfcorrea.listadecompras.databinding.FragmentHomeBinding;
import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    ListasAdapter adaptador;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ListaViewModel listaViewModel = new ViewModelProvider(requireActivity()).get(ListaViewModel.class);

        final Observer<String> valorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String valor) {
                binding.textViewTotalGeral.setText(valor);
            }
        };

        final Observer<String> numListas = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String valor) {
                binding.textViewNumListas.setText(valor);
            }
        };

        final Observer<List<ListaModel>> listaObserver = new Observer<List<ListaModel>>() {
            @Override
            public void onChanged(List<ListaModel> listaModels) {
                adaptador = new ListasAdapter(listaViewModel);
                binding.RecyclerViewLista.setAdapter(adaptador);

                executor.execute(() -> {
                    listaViewModel.atualizaTotal();
                });
            }
        };

        listaViewModel.getValorTotal().observe(getActivity(), valorObserver);
        listaViewModel.getNumListas().observe(getActivity(), numListas);
        listaViewModel.getListaModel().observe(getActivity(), listaObserver);

        executor.execute(() -> {
            listaViewModel.atualizaLista();
        });

        binding.btnNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToCadastroListaFragment);
            }
        });

        return view;
    }


}