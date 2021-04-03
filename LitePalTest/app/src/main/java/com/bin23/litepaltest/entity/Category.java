package com.bin23.litepaltest.entity;

public class Category {
    private int id;
    private String categoryName;
    private String categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Category() {
    }

    public Category(int id, String categoryName, String categoryCode) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                '}';
    }
}
