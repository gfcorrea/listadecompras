package com.gfcorrea.listadecompras.database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.entity.ItemLista;
import com.gfcorrea.listadecompras.entity.Lista;

@Database(
        entities = {Lista.class, ItemLista.class},
        version = 1
)

public abstract class AppDatabase extends RoomDatabase {

    public abstract ListaDao listaDao();
    public abstract ItemListaDao itemListaDao();

}
