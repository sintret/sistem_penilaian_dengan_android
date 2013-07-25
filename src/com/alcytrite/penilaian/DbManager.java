package com.alcytrite.penilaian;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbManager extends SQLiteOpenHelper {

	public static final String DATABASE = "penilaian";
	public static final int DB_VERSION = 1;
	
	public static final String TABLE_MURID = "murid";
	public static final String MURID_ID = "id";
	public static final String MURID_NAME = "name";
	public static final String MURID_ADDRESS = "address";
	
	public static final String TABLE_MATAPELAJARAN = "matapelajaran";
	public static final String MATAPELAJARAN_ID = "id";
	public static final String MATAPELAJARAN_SUBJECT = "subject";
	
	public static final String TABLE_NILAI = "nilai";
	public static final String NILAI_ID = "id";
	public static final String NILAI_NILAI = "nilai";
	public static final String NILAI_MURID = "muridId";
	public static final String NILAI_MATAPELAJARAN = "matapelajaranId";


	public static final String[] COLUMNS_MURID = { MURID_ID, MURID_NAME, MURID_ADDRESS };
	public static final String[] COLUMNS_MATAPELAJARAN = { MATAPELAJARAN_ID, MATAPELAJARAN_SUBJECT};
	public static final String[] COLUMNS_NILAI = { NILAI_ID, NILAI_MURID,NILAI_MATAPELAJARAN,NILAI_NILAI,  };


	private static final String TAG = "DbManager";
	
	public static final String CREATE_TABLE_MURID = " CREATE TABLE " + TABLE_MURID
			+ " ( " 
			+ MURID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ MURID_NAME + " TEXT , " 
			+ MURID_ADDRESS	+ " TEXT  " 
			+ " ); "
	;
	
	public static final String CREATE_TABLE_MATAPELAJARAN = " CREATE TABLE " + TABLE_MATAPELAJARAN
			+ " ( " 
			+ MATAPELAJARAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ MATAPELAJARAN_SUBJECT + " TEXT  " 
			+ " );  " 
			+ "INSERT INTO " + TABLE_MATAPELAJARAN + "( "+ MATAPELAJARAN_SUBJECT + ") values " 
			+ "('PHP') , ('C') , ('JAVA') , ('jQuery');"
	;
	
	public static final String CREATE_TABLE_NILAI = " CREATE TABLE " + TABLE_NILAI
			+ " ( " 
			+ NILAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NILAI_MURID + " INTEGER , " 
			+ NILAI_MATAPELAJARAN + " INTEGER , " 
			+ NILAI_NILAI + " INTEGER " 
			+ " ); "
	;

	public static final String CREATE_DATABASE = CREATE_TABLE_MURID + CREATE_TABLE_MATAPELAJARAN + CREATE_TABLE_NILAI ;


	public DbManager(Context context) {
		super(context, DATABASE, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_DATABASE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(TAG,
				"PLEASE BE CONCERN , THE DATABASE VERSION WILL CHANGE FROM VERSION "
						+ oldVersion + " TO VERSION " + newVersion);
		onCreate(db);
	}
	

	// Mata pelajaran begin 
	//Get Single Murid
	public Murid getMurid(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selection = MURID_ID + "=?";
		String[] selectionArgs = new String[] { String.valueOf(id) };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		
		Cursor cursor = db.query(TABLE_MURID, COLUMNS_MURID, selection, selectionArgs,
				groupBy, having, orderBy);
		if (cursor != null)
			cursor.moveToFirst();

		Murid murid = new Murid(
				cursor.getInt(0),
				cursor.getString(1),
				cursor.getString(2)
				);
		
		return murid;
	}

	
	//get all Murid
	public List<Murid> getAllMurid(){
		List<Murid> murid = new ArrayList<Murid>();
		String sql = "SELECT * FROM " + TABLE_MURID;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToFirst()){
			do{
				Murid list = new Murid();
				list.setId(cursor.getInt(0));
				list.setName(cursor.getString(1));
				list.setAddress(cursor.getString(2));
				murid.add(list);
			} while (cursor.moveToNext());
		};
		
		return murid;
	}
	
	//add murid
	public void addMurid(Murid murid){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MURID_NAME, murid.getName());
		values.put(MURID_ADDRESS, murid.getAddress());
		db.insert(TABLE_MURID, null, values);
		db.close();
	}
	
	//update Murid
	public void updateMurid(Murid murid){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(MURID_NAME, murid.getName());
		values.put(MURID_ADDRESS, murid.getAddress());
		String whereClause = MURID_ID +"=?";
		String [] whereArgs = new String[] {String.valueOf(murid.getId())};
		db.update(TABLE_MURID, values, whereClause, whereArgs);		
		db.close();
	}
	
	// delete Murid 
	public void deleteMurid(Murid murid){
		SQLiteDatabase db = this.getWritableDatabase();
		String whereClause = MURID_ID +"=?";
		String [] whereArgs = new String[] {String.valueOf(murid.getId())};
		db.delete(TABLE_MURID, whereClause, whereArgs);
	}
	
	// count Murid
	public int countMurid(){
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM "+TABLE_MURID;
		Cursor cursor = db.rawQuery(sql,null);
		return cursor.getCount();
	}
	
	// END MURID 
	// START NILAI
	
	//Get Single Nilai
		public Nilai getNilai(int id) {
			SQLiteDatabase db = this.getReadableDatabase();
			String selection = NILAI_ID + "=?";
			String[] selectionArgs = new String[] { String.valueOf(id) };
			String groupBy = null;
			String having = null;
			String orderBy = null;
			
			Cursor cursor = db.query(TABLE_NILAI, COLUMNS_NILAI, selection, selectionArgs,
					groupBy, having, orderBy);
			if (cursor != null)
				cursor.moveToFirst();

			Nilai nilai = new Nilai(
					cursor.getInt(0),
					cursor.getInt(1),
					cursor.getInt(2),
					cursor.getInt(3)
					);
			
			return nilai;
		}

		
		//get all Nilai murid
		public List<Nilai> getAllNilai(){
			List<Nilai> nilai = new ArrayList<Nilai>();
			String sql = "SELECT * FROM " + TABLE_NILAI;
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(sql, null);
			if(cursor.moveToFirst()){
				do{
					Nilai list = new Nilai();
					list.setId(cursor.getInt(0));
					list.setMuridId(cursor.getInt(1));
					list.setMatapelajaranId(cursor.getInt(2));
					list.setNilai(cursor.getInt(3));
					nilai.add(list);
				} while (cursor.moveToNext());
			};
			
			return nilai;
		}
		
		//add Nilai
		public void addNilai(Nilai nilai){
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(NILAI_MURID, nilai.getMuridId());
			values.put(NILAI_MATAPELAJARAN, nilai.getMatapelajaranId());
			values.put(NILAI_NILAI, nilai.getNilai());
			db.close();
		}
		
		//update Nilai
		public void updateNilai(Nilai nilai){
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(NILAI_MURID, nilai.getMuridId());
			values.put(NILAI_MATAPELAJARAN, nilai.getMatapelajaranId());
			values.put(NILAI_NILAI, nilai.getNilai());
			String whereClause = NILAI_ID +"=?";
			String [] whereArgs = new String[] {String.valueOf(nilai.getId())};
			db.update(TABLE_NILAI, values, whereClause, whereArgs);		
			db.close();
		}
		
		// delete Nilai 
		public void deleteNilai(Nilai nilai){
			SQLiteDatabase db = this.getWritableDatabase();
			String whereClause = NILAI_ID +"=?";
			String [] whereArgs = new String[] {String.valueOf(nilai.getId())};
			db.delete(TABLE_MURID, whereClause, whereArgs);
		}
		
		// count Nilai
		public int countNilai(){
			SQLiteDatabase db = this.getReadableDatabase();
			String sql = "SELECT * FROM "+TABLE_NILAI;
			Cursor cursor = db.rawQuery(sql,null);
			return cursor.getCount();
		}
	
	// END NILAI
	// START MATAPELAJARAN
	// count Matapelajaran
	public int countMatapelajaran(){
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM "+TABLE_MATAPELAJARAN;
		Cursor cursor = db.rawQuery(sql,null);
		return cursor.getCount();
	}

}
