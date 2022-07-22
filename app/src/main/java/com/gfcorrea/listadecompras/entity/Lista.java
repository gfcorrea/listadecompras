package com.gfcorrea.listadecompras.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lista")
public class Lista {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "data")
    public String data;

    @ColumnInfo(name = "valor")
    public double valor_total;

}
