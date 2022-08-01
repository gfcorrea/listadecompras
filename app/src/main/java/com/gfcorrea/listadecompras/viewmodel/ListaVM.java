package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.controller.ListaController;
import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.database.AppDatabase;
import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;

public class ListaVM extends ViewModel {

    public String teste;
    List<Lista> listas;

    public ListaVM() {
    }

    public List<Lista> Listas_getAll(){
        //ListaDao listaDao = AppDatabase.getInstance().listaDao();
        //this.listas = listaDao.getAll();

        ListaController controller = new ListaController();
        this.listas = controller.getAll();

        return listas;
    }

    public void DeleteByID(int id){
        ListaDao listaDao = AppDatabase.getInstance().listaDao();
        listaDao.deleteById( id );
    }

}
