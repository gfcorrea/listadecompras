package com.gfcorrea.listadecompras.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gfcorrea.listadecompras.entity.ItemLista;

import java.util.List;

@Dao
public interface ItemListaDao {

    @Query("SELECT * FROM item_lista where id_lista = :id")
    List<ItemLista> getAll(int id);

    @Query("SELECT * FROM item_lista WHERE id IN (:listaIds)")
    List<ItemLista> loadAllByIds(int[] listaIds);

    @Query("SELECT * FROM item_lista WHERE id = :id")
    ItemLista findById(long id);

    @Query("DELETE FROM item_lista WHERE id = :id")
    void deleteById(long id);

    @Query("SELECT * FROM item_lista WHERE produto LIKE :nome  LIMIT 1")
    ItemLista findByName(String nome);

    @Query("DELETE FROM item_lista where id_lista = :id")
    void deleteAllByListID(int id);

    @Query("SELECT sum(valor_total) from item_lista where id_lista = :id")
    double pegaTotalLista(int id);

    @Query("UPDATE item_lista SET marcado = :marcado where id = :id")
    void atualizaMarcacao(long id, boolean marcado);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ItemLista... listas);

    @Delete
    void delete(ItemLista lista);

}
