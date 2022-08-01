package com.gfcorrea.listadecompras.controller;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;

public class ListaController {

    public List<Lista> getAll(){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();

        return listaDao.getAll();
    }

    public void inserir(Lista lista){
        AppDatabase db = AppDatabase.getInstance();
        ListaDao listaDao = db.listaDao();

        listaDao.insertAll(lista);
    }

    public void excluirID(int id){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        listaDao.deleteById( id);
    }
}
