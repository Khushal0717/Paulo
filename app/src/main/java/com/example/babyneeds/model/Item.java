package com.example.babyneeds.model;

public class Item {
    private int id;
    private String itemName;
    private String itemColor;
    private int itemSize;
    private int itemQty;
    private String dataItemAdded;

    public Item(int id, String itemName, String itemColor, int itemSize, int itemQty, String dataItemAdded) {
        this.id = id;
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.itemSize = itemSize;
        this.itemQty = itemQty;
        this.dataItemAdded = dataItemAdded;
    }

    public Item(){

    }

    public Item(String itemName, String itemColor, int itemSize, int itemQty, String dataItemAdded) {
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.itemSize = itemSize;
        this.itemQty = itemQty;
        this.dataItemAdded = dataItemAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getDataItemAdded() {
        return dataItemAdded;
    }

    public void setDataItemAdded(String dataItemAdded) {
        this.dataItemAdded = dataItemAdded;
    }
}
