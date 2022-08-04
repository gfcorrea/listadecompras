package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.model.ItemListaModel;


import java.text.DecimalFormat;
import java.util.List;


public class ItemViewModel extends ViewModel {

    private MutableLiveData<String> valorTotal;
    private MutableLiveData<String> numItens;

    private DecimalFormat precision = new DecimalFormat("0.00");
    private List<ItemListaModel> lista;
    private int id_lista;


    public MutableLiveData<String> getTotalGeral() {
        if (valorTotal == null) {
            valorTotal = new MutableLiveData<String>();
        }
        return valorTotal;
    }

    public MutableLiveData<String> getNumItens() {
        if (numItens == null) {
            numItens = new MutableLiveData<String>();
        }
        return numItens;
    }

    public void atualizaTotal(){
        ItemListaDao itemListaDao = AppDatabase.getInstance().itemListaDao();
        getTotalGeral().setValue( "R$ " + precision.format(itemListaDao.pegaTotalLista(this.getId_lista())) );

        this.numItens.setValue(String.valueOf(lista.size()));
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public List<ItemListaModel> getLista() {
        return lista;
    }

    public void setLista(List<ItemListaModel> lista) {
        this.lista = lista;
    }
}
