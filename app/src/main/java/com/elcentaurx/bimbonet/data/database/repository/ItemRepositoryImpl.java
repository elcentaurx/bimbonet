package com.elcentaurx.bimbonet.data.database.repository;

import com.elcentaurx.bimbonet.data.database.dao.ItemDao;
import com.elcentaurx.bimbonet.data.database.entity.Item;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepository{

    ItemDao dao;

    public ItemRepositoryImpl(ItemDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAll();
    }

    @Override
    public Item findItemById(int itemId) {
        return dao.findById(itemId);
    }

    @Override
    public void insertItem(Item item) {
        dao.insert(item);
    }

    @Override
    public void updateItem(Item item) {
        dao.update(item);
    }

    @Override
    public void deleteItem(Item item) {
        dao.delete(item);
    }
}
