package com.gfcorrea.listadecompras.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gfcorrea.listadecompras.model.ListaModel;

import java.util.List;

@Dao
public interface ListaDao {

    @Query("SELECT * FROM lista")
     List<ListaModel>  getAll();

    @Query("SELECT * FROM lista WHERE id IN (:listaIds)")
    List<ListaModel> loadAllByIds(int[] listaIds);

    @Query("SELECT * FROM lista WHERE id = :id")
    ListaModel findById(int id);

    @Query("DELETE FROM lista WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM lista WHERE nome LIKE :nome  LIMIT 1")
    ListaModel findByName(String nome);

    @Query("UPDATE lista SET valor = valor + :val WHERE id = :id")
    void atualizarValor(int id, double val);

    @Query("SELECT sum(valor) from lista")
    double pegaValorTotal();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ListaModel... listaModels);

    @Delete
    void delete(ListaModel listaModel);

}
