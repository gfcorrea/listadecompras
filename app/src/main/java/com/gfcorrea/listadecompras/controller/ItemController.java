package com.gfcorrea.listadecompras.controller;

import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.entity.ItemLista;

import java.util.List;

public class ItemController {

    public List<ItemLista> itensDaListaID(int id){
        ItemListaDao itemListaDao = AppDatabase.getInstance().itemListaDao();
        return itemListaDao.getAll(id);
    }

    public void inserir(ItemLista item){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();
        itemListaDao.insertAll(item);
    }

    public void apagarID(int id){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();
        itemListaDao.deleteById(id);
    }

}
