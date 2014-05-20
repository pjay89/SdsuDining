package database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SdsuDBHelper extends SQLiteOpenHelper{

	private static final String TAG = "DATABASEHELPER";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Catering Table
	private static String CATERING_TABLE;
	private static String CATERING_PHONE;
	private static String CATERING_FAX;
	private static String CATERING_EMAIL;
	private static String CATERING_ADDRESS;
	private static String CATERING_SNIPPET;
	private static String CATERING_GUIDELINES;
	private static String CATERING_SERVICE_LEVEL;

	// Sweet Table
	private static String SWEET_TABLE; 
	private static String SWEET_PHONE;
	private static String SWEET_FAX;
	private static String SWEET_EMAIL;
	private static String SWEET_WEBSITE;
	private static String SWEET_MENU;
	private static String SWEET_ORDER_FORM;

	// Contact Table
	private static String CONTACT_TABLE; 
	private static String CONTACT_PHONE;
	private static String CONTACT_FAX;
	private static String CONTACT_EMAIL;
	private static String CONTACT_ADDRESS;






	public SdsuDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);

		//Catering Table
		CATERING_TABLE = context.getString(R.string.CATERING_TABLE);
		CATERING_PHONE = context.getString(R.string.CATERING_PHONE);
		CATERING_FAX = context.getString(R.string.CATERING_FAX);
		CATERING_EMAIL = context.getString(R.string.CATERING_EMAIL);
		CATERING_ADDRESS = context.getString(R.string.CATERING_ADDRESS);
		CATERING_SNIPPET = context.getString(R.string.CATERING_SNIPPET);
		CATERING_GUIDELINES = context.getString(R.string.CATERING_GUIDELINES);
		CATERING_SERVICE_LEVEL = context.getString(R.string.CATERING_SERVICE_LEVEL);

		//Sweet Table
		SWEET_TABLE = context.getString(R.string.SWEET_TABLE);
		SWEET_PHONE = context.getString(R.string.SWEET_PHONE);
		SWEET_FAX = context.getString(R.string.SWEET_FAX);
		SWEET_EMAIL = context.getString(R.string.SWEET_EMAIL);
		SWEET_WEBSITE = context.getString(R.string.SWEET_WEBSITE);
		SWEET_MENU = context.getString(R.string.SWEET_MENU);
		SWEET_ORDER_FORM = context.getString(R.string.SWEET_ORDER_FORM);


		//Contact Table
		CONTACT_TABLE = context.getString(R.string.CONTACT_TABLE);
		CONTACT_PHONE = context.getString(R.string.CONTACT_PHONE);
		CONTACT_FAX = context.getString(R.string.CONTACT_FAX);
		CONTACT_EMAIL = context.getString(R.string.CONTACT_EMAIL);
		CONTACT_ADDRESS = context.getString(R.string.CONTACT_ADDRESS);
	}










	@Override
	public void onCreate(SQLiteDatabase db) {
		//CATERING tble create statement
		String CREATE_TABLE_CATERING = "CREATE TABLE "
				+ CATERING_TABLE + "(id INTEGER PRIMARY KEY," + CATERING_PHONE
				+ " TEXT," + CATERING_FAX + " TEXT," + CATERING_EMAIL 
				+ " TEXT," + CATERING_ADDRESS + " TEXT," + CATERING_SNIPPET 
				+ " TEXT," + CATERING_GUIDELINES + " TEXT," + CATERING_SERVICE_LEVEL 
				+ " TEXT"+ ")"; 
		Log.i(TAG, CREATE_TABLE_CATERING);
		db.execSQL(CREATE_TABLE_CATERING);

		// SWEET table create statement
		String CREATE_TABLE_SWEET = "CREATE TABLE "
				+ SWEET_TABLE + "( id INTEGER PRIMARY KEY," + SWEET_PHONE
				+ " TEXT," + SWEET_FAX + " TEXT," + SWEET_EMAIL 
				+ " TEXT," + SWEET_WEBSITE + " TEXT," + SWEET_MENU 
				+ " TEXT," + SWEET_ORDER_FORM + " TEXT"+ ")";
		Log.i(TAG, CREATE_TABLE_SWEET);
		db.execSQL(CREATE_TABLE_SWEET);


		// CONTACT table create statement
		String CREATE_TABLE_CONTACT = "CREATE TABLE "
				+ CONTACT_TABLE + "(id INTEGER PRIMARY KEY," + CONTACT_PHONE
				+ " TEXT," + CONTACT_FAX + " TEXT," + CONTACT_EMAIL 
				+ " TEXT," + CONTACT_ADDRESS	+ " TEXT"+ ")"; 
		Log.i(TAG, CREATE_TABLE_CONTACT);
		db.execSQL(CREATE_TABLE_CONTACT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CATERING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + SWEET_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
		onCreate(db);
	}

	/* ******* CATERING ******* */
	public void addToCateringTable(String phone, String fax, String email, String address, String snippet, String guidelines, String level){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CATERING_PHONE, phone);
		values.put(CATERING_FAX, fax);
		values.put(CATERING_EMAIL, email);
		values.put(CATERING_ADDRESS, address);
		values.put(CATERING_SNIPPET, snippet);
		values.put(CATERING_GUIDELINES, guidelines);
		values.put(CATERING_SERVICE_LEVEL, level);

		db.insert(CATERING_TABLE, null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getCateringDetails(){
		ArrayList<HashMap<String, String>> catering = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + CATERING_TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(CATERING_PHONE, cursor.getString(1));
			entry.put(CATERING_FAX, cursor.getString(2));
			entry.put(CATERING_EMAIL, cursor.getString(3));
			entry.put(CATERING_ADDRESS, cursor.getString(4));
			entry.put(CATERING_SNIPPET, cursor.getString(5));
			entry.put(CATERING_GUIDELINES, cursor.getString(6));
			entry.put(CATERING_SERVICE_LEVEL, cursor.getString(7));
			catering.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return catering;
	}



	/* ******* SWEET ******* */
	public void addToSweetTable(String phone, String fax, String email, String website, String menu, String orderForm){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SWEET_PHONE, phone);
		values.put(SWEET_FAX, fax);
		values.put(SWEET_EMAIL, email);
		values.put(SWEET_WEBSITE, website);
		values.put(SWEET_MENU, menu);
		values.put(SWEET_ORDER_FORM, orderForm);

		db.insert(SWEET_TABLE, null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getSweetDetails(){
		ArrayList<HashMap<String, String>> sweet = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + SWEET_TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(SWEET_PHONE, cursor.getString(1));
			entry.put(SWEET_FAX, cursor.getString(2));
			entry.put(SWEET_EMAIL, cursor.getString(3));
			entry.put(SWEET_WEBSITE, cursor.getString(4));
			entry.put(SWEET_MENU, cursor.getString(5));
			entry.put(SWEET_ORDER_FORM, cursor.getString(6));
			sweet.add(entry);
			cursor.moveToNext();
		}

		cursor.close();
		db.close();

		return sweet;
	}



	/* ******* CONTACT ******* */
	public void addToContactTable(String phone, String fax, String email, String address){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CONTACT_PHONE, phone);
		values.put(CONTACT_FAX, fax);
		values.put(CONTACT_EMAIL, email);
		values.put(CONTACT_ADDRESS, address);

		db.insert(CONTACT_TABLE, null, values);
	}

	public ArrayList<HashMap<String, String>> getContactDetails(){
		ArrayList<HashMap<String, String>> contact = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + CONTACT_TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(CONTACT_PHONE, cursor.getString(1));
			entry.put(CONTACT_FAX, cursor.getString(2));
			entry.put(CONTACT_EMAIL, cursor.getString(3));
			entry.put(CONTACT_ADDRESS, cursor.getString(4));
			contact.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return contact;
	}

	
	public void deleteCateringTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(CATERING_TABLE, null, null);
		db.close();
	}


	public void deleteSweetTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SWEET_TABLE, null, null);
		db.close();
	}



	public void deleteContactTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(CONTACT_TABLE, null, null);
		db.close();
	}
}
