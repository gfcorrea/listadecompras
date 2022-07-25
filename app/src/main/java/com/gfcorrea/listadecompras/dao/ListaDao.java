package com.gfcorrea.listadecompras.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gfcorrea.listadecompras.entity.Lista;

import java.util.List;

@Dao
public interface ListaDao {

    @Query("SELECT * FROM lista")
    List<Lista> getAll();

    @Query("SELECT * FROM lista WHERE id IN (:listaIds)")
    List<Lista> loadAllByIds(int[] listaIds);

    @Query("SELECT * FROM lista WHERE id = :id")
    Lista findById(int id);

    @Query("DELETE FROM lista WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM lista WHERE nome LIKE :nome  LIMIT 1")
    Lista findByName(String nome);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Lista... listas);

    @Delete
    void delete(Lista lista);

}
