package com.elcentaurx.bimbonet.data.database.repository;

import com.elcentaurx.bimbonet.data.database.entity.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> getAllItems();
    Item findItemById(int itemId);
    void insertItem(Item item);
    void updateItem(Item item);
    void deleteItem(Item item);
}
