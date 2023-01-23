package com.elcentaurx.bimbonet.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.elcentaurx.bimbonet.data.database.entity.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("select * from Item")
    List<Item> getAll();

    @Query("select * from Item where id = :itemId")
    Item findById(int itemId);

    @Insert
    void insert(Item item);
    @Update
    void update(Item item);
    @Delete
    void delete(Item item);

}
