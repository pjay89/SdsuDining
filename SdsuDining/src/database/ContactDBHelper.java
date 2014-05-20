package database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Table Name
	private static String TABLE; 

	// Column names
	private static String CONTACT_PHONE;
	private static String CONTACT_FAX;
	private static String CONTACT_EMAIL;
	private static String CONTACT_ADDRS;

	public ContactDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		TABLE = context.getString(R.string.CONTACT_TABLE);
		CONTACT_PHONE = context.getString(R.string.CONTACT_PHONE);
		CONTACT_FAX = context.getString(R.string.CONTACT_FAX);
		CONTACT_EMAIL = context.getString(R.string.CONTACT_EMAIL);
		CONTACT_ADDRS = context.getString(R.string.CONTACT_ADDRESS);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// CONTACT table create statement
		String CREATE_TABLE_CONTACT = "CREATE TABLE "
				+ TABLE + "(id INTEGER PRIMARY KEY," + CONTACT_PHONE
				+ " TEXT," + CONTACT_FAX + " TEXT," + CONTACT_EMAIL 
				+ " TEXT," + CONTACT_ADDRS	+ " TEXT"+ ")"; 
		
		db.execSQL(CREATE_TABLE_CONTACT);
		
		// SWEET table create statement
				String CREATE_TABLE_SWEET = "CREATE TABLE "
						+ "sweetTable" + "( id INTEGER PRIMARY KEY," + "phone"
						+ " TEXT," + "fax" + " TEXT," + "email" 
						+ " TEXT," + "website" + " TEXT," + "menu_pdf" 
						+ " TEXT," + "order_form_pdf" + " TEXT"+ ")";

				db.execSQL(CREATE_TABLE_SWEET);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	public void addToDB(String phone, String fax, String email, String addrs){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CONTACT_PHONE, phone);
		values.put(CONTACT_FAX, fax);
		values.put(CONTACT_EMAIL, email);
		values.put(CONTACT_ADDRS, addrs);
		
		db.insert(TABLE, null, values);
	}
	
	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> contact = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(CONTACT_PHONE, cursor.getString(1));
			entry.put(CONTACT_FAX, cursor.getString(2));
			entry.put(CONTACT_EMAIL, cursor.getString(3));
			entry.put(CONTACT_ADDRS, cursor.getString(4));
			contact.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return contact;
	}
	
	public void deleteTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE, null, null);
		db.close();
	}

}
