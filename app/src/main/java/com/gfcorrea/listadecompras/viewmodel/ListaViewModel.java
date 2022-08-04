package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.repository.ListaRepository;

import java.text.DecimalFormat;
import java.util.List;

public class ListaViewModel extends ViewModel {

    DecimalFormat precision = new DecimalFormat("0.00");
    List<ListaModel> listaModel;
    private int id;
    private String nome;
    private MutableLiveData<String> numListas;
    private ListaRepository listaRepository = new ListaRepository();
    private MutableLiveData<String> valorTotal;

    public ListaViewModel() {  }

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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ListaModel> getAll(){
        this.listaModel = listaRepository.getAll();
        return listaModel;
    }

    public void deleteByID(int id){
        listaRepository.excluirID(id);
    }

    public void atualizaTotal(){
        getValorTotal().setValue("R$ " + precision.format(listaRepository.pegaValorTotal()));
        this.numListas.setValue(String.valueOf(listaModel.size()));
    }

}
