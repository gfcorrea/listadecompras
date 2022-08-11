package com.gfcorrea.listadecompras.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gfcorrea.listadecompras.dao.ItemListaDao;
import com.gfcorrea.listadecompras.dao.ListaDao;
import com.gfcorrea.listadecompras.model.ItemListaModel;
import com.gfcorrea.listadecompras.model.ListaModel;

@Database(
        entities = {ListaModel.class, ItemListaModel.class},
        version = 1
)

public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "listadecompras";
    private static AppDatabase instance;
    private static Context contexto;

    public abstract ListaDao listaDao();
    public abstract ItemListaDao itemListaDao();

    public static synchronized AppDatabase getInstance(){
        if(instance == null){
            instance = Room.databaseBuilder(contexto, AppDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }

    public static void setContexto(Context c){
        contexto = c;
    }

}
