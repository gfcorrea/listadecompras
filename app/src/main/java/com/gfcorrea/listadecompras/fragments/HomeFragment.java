package com.gfcorrea.listadecompras.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gfcorrea.listadecompras.R;
import com.gfcorrea.listadecompras.adapter.ListasAdapter;
import com.gfcorrea.listadecompras.databinding.FragmentHomeBinding;
import com.gfcorrea.listadecompras.viewmodel.ListaViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

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
            public void onChanged(@Nullable final String valor)  {
                binding.textViewTotalGeral.setText(valor);
            }
        };

        final Observer<String> numListas = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String valor) {
                binding.textViewNumListas.setText(valor);
            }
        };

        listaViewModel.getValorTotal().observe(getActivity(), valorObserver);
        listaViewModel.getNumListas().observe(getActivity(), numListas);

        adaptador = new ListasAdapter(listaViewModel);

        binding.RecyclerViewLista.setAdapter(adaptador);

        listaViewModel.atualizaTotal();

        binding.btnNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToCadastroListaFragment);
            }
        });

        return view;
    }


}