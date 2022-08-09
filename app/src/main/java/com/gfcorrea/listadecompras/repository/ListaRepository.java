package com.gfcorrea.listadecompras.repository;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.model.ListaModel;

import java.util.List;


public class ListaRepository {

    public List<ListaModel> getAll(){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();

        return listaDao.getAll();
    }

    public void inserir(ListaModel listaModel){

            AppDatabase db = AppDatabase.getInstance();
            ListaDao listaDao = db.listaDao();

            listaModel.setValor_total(0.0);
            listaModel.setData(System.currentTimeMillis());

            listaDao.insertAll(listaModel);
    }

    public void excluirID(int id){
        ItemRepository itemRepository = new ItemRepository();
        itemRepository.apagarTodosItensDaLista(id);

        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        listaDao.deleteById( id);
    }

    public void atualizarValor(int id, double val){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        listaDao.atualizarValor(id, val);
    }

    public double pegaValorTotal(){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        return listaDao.pegaValorTotal();
    }



}
