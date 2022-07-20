package com.example.testebd.repository;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.testebd.dao.UserDao;

public class BdConection extends Application {

    private static BdConection conexao = new BdConection();
    private static AppDatabase instance;

    private BdConection() {
    }

    public static AppDatabase getConexao(Context contexto) {
        if (instance == null) {
            instance = Room.databaseBuilder(contexto, AppDatabase.class, "meubd.bd").allowMainThreadQueries().build();
        }

        return instance;
    }

}
