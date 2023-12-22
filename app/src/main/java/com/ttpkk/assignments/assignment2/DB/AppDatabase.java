package com.ttpkk.assignments.assignment2.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ttpkk.assignments.assignment2.dao.ItemDao;
import com.ttpkk.assignments.assignment2.entity.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "room_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return  INSTANCE;
    }
}
