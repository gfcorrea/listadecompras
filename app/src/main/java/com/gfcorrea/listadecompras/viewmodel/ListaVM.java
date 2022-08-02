package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.controller.ListaController;
import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.entity.Lista;

import java.text.DecimalFormat;
import java.util.List;

public class ListaVM extends ViewModel {

    DecimalFormat precision = new DecimalFormat("0.00");
    List<Lista> listas;
    private int id;
    private String nome;
    private double valorTotal;

    private MutableLiveData<String> valorTotalf;

    public MutableLiveData<String> getvalorTotalf() {
        if (valorTotalf == null) {
            valorTotalf = new MutableLiveData<String>();
        }
        return valorTotalf;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ListaVM() {

    }

    public List<Lista> Listas_getAll(){
        ListaController listaController = new ListaController();
        this.listas = listaController.getAll();

        return listas;
    }

    public void DeleteByID(int id){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        listaDao.deleteById( id );
    }

    public void atualizaTotal(){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        setValorTotal(listaDao.pegaValorTotal());

        getvalorTotalf().setValue("R$ " + precision.format(getValorTotal()));
    }

}
