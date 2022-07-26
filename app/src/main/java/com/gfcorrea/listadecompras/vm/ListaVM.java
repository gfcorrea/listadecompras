package com.gfcorrea.listadecompras.vm;

import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.BdConection;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;

public class ListaVM extends ViewModel {

    public String teste;
    List<Lista> listas;

    public ListaVM() {
    }

    public List<Lista> Listas_getAll(){
        ListaDao listaDao = BdConection.getConexao().listaDao();
        this.listas = listaDao.getAll();
        return listas;
    }

    public void DeleteByID(int id){
        ListaDao listaDao = BdConection.getConexao().listaDao();
        listaDao.deleteById( id );
    }
}
