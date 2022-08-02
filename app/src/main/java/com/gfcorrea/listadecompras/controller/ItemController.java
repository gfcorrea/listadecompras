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

        ListaController listaController = new ListaController();
        listaController.atualizarValor(item.getId_lista(), item.getValor_total());
    }

    public void apagarID(long id){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();

        ItemLista item = itemListaDao.findById(id);

        ListaController listaController = new ListaController();
        listaController.atualizarValor(item.getId_lista(), item.getValor_total() *-1 );

        itemListaDao.deleteById(id);
    }

    public void apagarTodosItensDaLista(int id){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();
        itemListaDao.deleteAllByListID(id);
    }

    public void atualizarMarcacao(long id, boolean marcado){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();
        itemListaDao.atualizaMarcacao(id, marcado);
    }

}
