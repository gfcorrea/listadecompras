package com.gfcorrea.listadecompras.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
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

    private static final String DATABASE_NAME = "listadecompras.bd";
    private static AppDatabase instance;
    private static Context contexto;

    public abstract ListaDao listaDao();
    public abstract ItemListaDao itemListaDao();

    public static synchronized AppDatabase getInstance(){
        if(instance == null){
            instance = Room.databaseBuilder(contexto, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public static void setContexto(Context c){
        contexto = c;
    }

}
