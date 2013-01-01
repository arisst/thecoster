package com.example.androidhive.library;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "theCoster";

	// Login table name
	private static final String TABLE_LOGIN = "login";
	private static final String TABLE_LOG = "log";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";
	private static final String KEY_MOBILE = "mobile";
	private static final String KEY_START = "start";
	private static final String KEY_CREDIT = "credit";
	private static final String KEY_GOODS = "goods";
	private static final String KEY_PRICE = "price";
	private static final String KEY_TIME = "time";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOG_TABLE = "CREATE TABLE " + TABLE_LOG + "("
				+ KEY_GOODS + " TEXT,"
				+ KEY_PRICE + " TEXT,"
				+ KEY_TIME + " TEXT" + ")";
		db.execSQL(CREATE_LOG_TABLE);
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," 
				+ KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE,"
				+ KEY_UID + " TEXT,"
				+ KEY_CREATED_AT + " TEXT,"
				+ KEY_MOBILE + " TEXT,"
				+ KEY_START + " TEXT,"
				+ KEY_CREDIT + " TEXT" + ")";
		
		db.execSQL(CREATE_LOGIN_TABLE);
		
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	
	public void addLog(String goods, String price, String time){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GOODS, goods); 
		values.put(KEY_PRICE, price);
		values.put(KEY_TIME, time); 
		db.insert(TABLE_LOG, null, values);
		db.close(); // Closing database connection
		
	}
	public void addUser(String name, String email, String uid, String created_at, String mobile, String start, String credit) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_UID, uid); // Email
		values.put(KEY_CREATED_AT, created_at); // Created At
		values.put(KEY_MOBILE, mobile); // Created At
		values.put(KEY_START, start); // Created At
		values.put(KEY_CREDIT, credit); // Created At

		// Inserting Row
		db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection
	}
	
	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails(){
		HashMap<String,String> user = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
		
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	user.put("name", cursor.getString(1));
        	user.put("email", cursor.getString(2));
        	user.put("uid", cursor.getString(3));
        	user.put("created_at", cursor.getString(4));
        	user.put("mobile", cursor.getString(5));
        	user.put("start", cursor.getString(6));    	
        	user.put("credit", cursor.getString(7));
        	
        	
        }
        
        cursor.close();
        db.close();
		// return user
		return user;
	}
	
	public HashMap<String, String> getLogDetails(){
		HashMap<String,String> log = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOG;
		
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	
        	for(int i=0; i<cursor.getCount(); i++){
        	log.put("goods"+i+"", cursor.getString(0));
        	log.put("price"+i+"", cursor.getString(1));
        	log.put("time"+i+"", cursor.getString(2));
        	
        	cursor.moveToNext();
        	}
        }
        
        cursor.close();
        db.close();
		// return user
		return log;
	}

	/**
	 * Getting user login status
	 * return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();
		
		// return row count
		return rowCount;
	}
	

	/**
	 * Re crate database
	 * Delete all tables and create them again
	 * */
	public void resetTables(){
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
		db.delete(TABLE_LOG, null, null);
		db.close();
	}

}
