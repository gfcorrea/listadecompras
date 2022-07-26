package com.gfcorrea.listadecompras.vm;

import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaVM extends ViewModel {

    public String teste;
    List<Lista> listas;

    public ListaVM() {
    }

    public List<Lista> Listas_getAll(){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();

        this.listas = listaDao.getAll();

        return listas;
    }

    public void DeleteByID(int id){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        listaDao.deleteById( id );
    }

}
