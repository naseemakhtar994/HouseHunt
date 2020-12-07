package com.example.househunt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.househunt.model.House;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(House.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + House.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(House house) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(House.COLUMN_POPERTY_NAME, house.getName());
        values.put(House.COLUMN_POPERTY_TYPE, house.getType());
        values.put(House.COLUMN_POPERTY_DESCRIPTION, house.getDescription());
        values.put(House.COLUMN_POPERTY_PRICE, house.getPrice());
        values.put(House.COLUMN_POPERTY_IMAGES, house.getImagesArray());
        values.put(House.COLUMN_POPERTY_LATITUDE, house.getLatitude());
        values.put(House.COLUMN_POPERTY_LOGITUDE, house.getLongitude());
        values.put(House.COLUMN_POPERTY_LOCATION, house.getLocation());
        values.put(House.COLUMN_POPERTY_CONTACT, house.getContact());


        // insert row
        long id = db.insert(House.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public House getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(House.TABLE_NAME,
                new String[]{House.COLUMN_ID, House.COLUMN_POPERTY_NAME, House.COLUMN_TIMESTAMP},
                House.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        House note = new House();

        note.setName(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_NAME)));
        note.setType(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_TYPE)));
        note.setPrice(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_PRICE)));
        note.setDescription(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_DESCRIPTION)));
        note.setImagesArray(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_IMAGES)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<House> getAllNotes() {
        List<House> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + House.TABLE_NAME + " ORDER BY " +
                House.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                House note = new House();
                note.setId(cursor.getInt(cursor.getColumnIndex(House.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_NAME)));
                note.setType(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_TYPE)));
                note.setDescription(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_DESCRIPTION)));
                note.setPrice(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_PRICE)));
                note.setImagesArray(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_IMAGES)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(House.COLUMN_TIMESTAMP)));
                note.setLatitude(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_LATITUDE)));
                note.setLongitude(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_LOGITUDE)));
                note.setLocation(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_LOCATION)));
                note.setContact(cursor.getString(cursor.getColumnIndex(House.COLUMN_POPERTY_CONTACT)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + House.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(House note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(House.COLUMN_POPERTY_NAME, note.getName());

        // updating row
        return db.update(House.TABLE_NAME, values, House.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(House note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(House.TABLE_NAME, House.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}