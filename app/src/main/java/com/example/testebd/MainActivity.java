package com.example.testebd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.testebd.dao.UserDao;
import com.example.testebd.entity.User;
import com.example.testebd.repository.AppDatabase;
import com.example.testebd.repository.BdConection;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView TextViewNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextViewNome = findViewById(R.id.TextViewNome);


        //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "meubd.bd").allowMainThreadQueries().build();

        AppDatabase db = BdConection.getConexao(getApplicationContext());



        UserDao userDao = db.userDao();
//        List<User> users = userDao.getAll();

        //UserDao userDao = db.userDao();
        //userDao.inserir(1, "Gabriel", "Fernandes");

        AddUser(userDao);

        List<User> users = userDao.getAll();

        TextViewNome.setText(users.get(0).firstName);

    }


    public void AddUser(UserDao d){

        User eu = new User();
        eu.uid = 1;
        eu.firstName = "Gabriel";
        eu.lastName = "Fernandes";


        d.insertAll(eu);
    }

}