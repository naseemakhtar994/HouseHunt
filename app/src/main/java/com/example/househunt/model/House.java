package com.example.househunt.model;

import java.io.Serializable;

public class House implements Serializable {
    public static final String TABLE_NAME = "houses";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_POPERTY_NAME = "name";
    public static final String COLUMN_POPERTY_CONTACT = "contact";
    public static final String COLUMN_POPERTY_TYPE = "type";
    public static final String COLUMN_POPERTY_DESCRIPTION = "description";
    public static final String COLUMN_POPERTY_PRICE = "price";
    public static final String COLUMN_POPERTY_IMAGES = "images";
    public static final String COLUMN_POPERTY_LATITUDE = "latitude";
    public static final String COLUMN_POPERTY_LOGITUDE= "longitude";
    public static final String COLUMN_POPERTY_LOCATION= "location";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String name;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private String contact;
    private String type;
    private String description;
    private String price;
    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private String longitude;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;
    private String timestamp;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagesArray() {
        return imagesArray;
    }

    public void setImagesArray(String imagesArray) {
        this.imagesArray = imagesArray;
    }

    private String imagesArray;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_POPERTY_NAME + " TEXT,"
                    + COLUMN_POPERTY_TYPE + " TEXT,"
                    + COLUMN_POPERTY_DESCRIPTION + " TEXT,"
                    + COLUMN_POPERTY_PRICE + " TEXT,"
                    + COLUMN_POPERTY_IMAGES+ " TEXT,"
                    + COLUMN_POPERTY_LATITUDE+ " TEXT,"
                    + COLUMN_POPERTY_LOGITUDE+ " TEXT,"
                    + COLUMN_POPERTY_LOCATION+ " TEXT,"
                    + COLUMN_POPERTY_CONTACT+ " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public House(int id, String name, String type, String description, String timestamp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.timestamp = timestamp;
    }

    public House(String name, String type, String description,String price,String imagesArray,String latitude,String longitude,String location,String contact) {

        this.name = name;
        this.type = type;
        this.description = description;
        this.imagesArray = imagesArray;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.contact = contact;
    }

    public House() {
    }



    public int getId() {
        return id;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
