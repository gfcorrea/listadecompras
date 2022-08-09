package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.repository.ListaRepository;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaViewModel extends ViewModel {

    private DecimalFormat precision = new DecimalFormat("0.00");

    private List<ListaModel> listaModel;
    private ListaModel lista = new ListaModel();

    private MutableLiveData<String> numListas;
    private ListaRepository listaRepository = new ListaRepository();
    private MutableLiveData<String> valorTotal;



    public ListaViewModel() {
    }

    public MutableLiveData<String> getValorTotal() {
        if (valorTotal == null) {
            valorTotal = new MutableLiveData<String>();
        }
        return valorTotal;
    }

    public MutableLiveData<String> getNumListas() {
        if (numListas == null) {
            numListas = new MutableLiveData<String>();
        }
        return numListas;
    }

    public int getId() {
        return lista.getId();
    }

    public void setId(int id) {
        this.lista.setId(id);
    }

    public String getNome() {
        return lista.getNome();
    }

    public void setNome(String nome) {
        this.lista.setNome(nome);
    }

    public List<ListaModel> getAll() {
        this.listaModel = listaRepository.getAll();
        return listaModel;
    }

    public void deleteByID(int id) {
        listaRepository.excluirID(id);
    }

    public void atualizaTotal() {
        getValorTotal().setValue("R$ " + precision.format(listaRepository.pegaValorTotal()));
        this.numListas.setValue(String.valueOf(listaModel.size()));
    }

}
