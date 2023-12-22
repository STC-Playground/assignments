package com.ttpkk.assignments.assignment2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.ttpkk.assignments.assignment2.entity.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insertItem(Item...items);

    @Query("SELECT * FROM Items ORDER BY timestamp DESC")
    List<Item> getAllItems();

}
