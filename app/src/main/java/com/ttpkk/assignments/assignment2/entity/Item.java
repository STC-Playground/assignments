package com.ttpkk.assignments.assignment2.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(tableName = "Items")
public class Item {

    @ColumnInfo(name = "item_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "barcode_number1")
    private String barcodeNumber1;
    @ColumnInfo(name = "barcode_number2")
    private String barcodeNumber2;
    @ColumnInfo(name = "timestamp")
    private long timestamp;
    @ColumnInfo(name = "status")
    private boolean isMatched;

    public Item() {
    }

    public Item( String barcodeNumber1, String barcodeNumber2, long timestamp, boolean isMatched) {
        this.barcodeNumber1 = barcodeNumber1;
        this.barcodeNumber2 = barcodeNumber2;
        this.timestamp = timestamp;
        this.isMatched = isMatched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcodeNumber1() {
        return barcodeNumber1;
    }

    public void setBarcodeNumber1(String barcodeNumber1) {
        this.barcodeNumber1 = barcodeNumber1;
    }

    public String getBarcodeNumber2() {
        return barcodeNumber2;
    }

    public void setBarcodeNumber2(String barcodeNumber2) {
        this.barcodeNumber2 = barcodeNumber2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

}
