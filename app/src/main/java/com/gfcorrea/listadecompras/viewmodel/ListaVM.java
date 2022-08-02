package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.controller.ListaController;
import com.gfcorrea.listadecompras.entity.Lista;

import java.text.DecimalFormat;
import java.util.List;

public class ListaVM extends ViewModel {

    DecimalFormat precision = new DecimalFormat("0.00");
    List<Lista> listas;
    private int id;
    private String nome;
    private ListaController listaController = new ListaController();
    private MutableLiveData<String> valorTotal;

    public ListaVM() {  }

    public MutableLiveData<String> getValorTotal() {
        if (valorTotal == null) {
            valorTotal = new MutableLiveData<String>();
        }
        return valorTotal;
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

    public List<Lista> getAll(){
        this.listas = listaController.getAll();
        return listas;
    }

    public void deleteByID(int id){
        listaController.excluirID(id);
    }

    public void atualizaTotal(){
        getValorTotal().setValue("R$ " + precision.format(listaController.pegaValorTotal()));
    }

}
