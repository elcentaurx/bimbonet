package com.elcentaurx.bimbonet.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.elcentaurx.bimbonet.data.database.dao.ItemDao;
import com.elcentaurx.bimbonet.data.database.entity.Item;

@Database(entities = {
        Item.class
}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static AppDataBase INSTANCE;
    public abstract ItemDao itemDao();
    public static AppDataBase getInstance(Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDataBase.class, "beer.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
