package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;


import java.text.DecimalFormat;


public class ItemVM extends ViewModel {

    private MutableLiveData<String> valorTotal;
    private DecimalFormat precision = new DecimalFormat("0.00");
    private int id_lista;

    public MutableLiveData<String> getTotalGeral() {
        if (valorTotal == null) {
            valorTotal = new MutableLiveData<String>();
        }
        return valorTotal;
    }

    public void atualizaTotal(){
        ItemListaDao itemListaDao = AppDatabase.getInstance().itemListaDao();
        getTotalGeral().setValue( "R$ " + precision.format(itemListaDao.pegaTotalLista(this.getId_lista())) );
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }
}
