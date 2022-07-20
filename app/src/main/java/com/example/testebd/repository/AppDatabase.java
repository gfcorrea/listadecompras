package com.example.testebd.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.testebd.dao.UserDao;
import com.example.testebd.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}