package com.gfcorrea.listadecompras.repository;

import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.model.ItemListaModel;

import java.util.List;

public class ItemRepository {

    public List<ItemListaModel> itensDaListaID(int id){
        ItemListaDao itemListaDao = AppDatabase.getInstance().itemListaDao();
        return itemListaDao.getAll(id);
    }

    public void inserir(ItemListaModel item){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();
        itemListaDao.insertAll(item);

        ListaRepository listaRepository = new ListaRepository();
        listaRepository.atualizarValor(item.getId_lista(), item.getValor_total());
    }

    public void apagarID(long id){
        AppDatabase db = AppDatabase.getInstance();
        ItemListaDao itemListaDao = db.itemListaDao();

        ItemListaModel item = itemListaDao.findById(id);

        ListaRepository listaRepository = new ListaRepository();
        listaRepository.atualizarValor(item.getId_lista(), item.getValor_total() *-1 );

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
