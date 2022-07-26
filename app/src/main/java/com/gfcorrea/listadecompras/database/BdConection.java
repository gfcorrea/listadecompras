package com.gfcorrea.listadecompras.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class BdConection extends Application {

    private static BdConection conexao = new BdConection();
    private static AppDatabase instance;
    private static Context contexto;

    private BdConection() {

    }

    public static AppDatabase getConexao() {
        if (instance == null) {
            instance = Room.databaseBuilder( contexto, AppDatabase.class, "meubd3.bd").allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void setContexto(Context c){
        contexto = c;
    }

}
