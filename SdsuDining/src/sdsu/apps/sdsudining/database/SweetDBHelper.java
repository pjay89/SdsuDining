package sdsu.apps.sdsudining.database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SweetDBHelper extends SQLiteOpenHelper{
	private static final String TAG = "DATABASEHELPER";
	
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Table Name
	private static String TABLE; 

	// Column names
	private static String SWEET_PHONE;
	private static String SWEET_FAX;
	private static String SWEET_EMAIL;
	private static String SWEET_WEBSITE;
	private static String SWEET_MENU;
	private static String SWEET_ORDER_FORM;

	public SweetDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		TABLE = context.getString(R.string.SWEET_TABLE);
		SWEET_PHONE = context.getString(R.string.SWEET_PHONE);
		SWEET_FAX = context.getString(R.string.SWEET_FAX);
		SWEET_EMAIL = context.getString(R.string.SWEET_EMAIL);
		SWEET_WEBSITE = context.getString(R.string.SWEET_WEBSITE);
		SWEET_MENU = context.getString(R.string.SWEET_MENU);
		SWEET_ORDER_FORM = context.getString(R.string.SWEET_ORDER_FORM);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SWEET table create statement
		String CREATE_TABLE_SWEET = "CREATE TABLE "
				+ TABLE + "( id INTEGER PRIMARY KEY," + SWEET_PHONE
				+ " TEXT," + SWEET_FAX + " TEXT," + SWEET_EMAIL 
				+ " TEXT," + SWEET_WEBSITE + " TEXT," + SWEET_MENU 
				+ " TEXT," + SWEET_ORDER_FORM + " TEXT"+ ")";

		Log.i(TAG, CREATE_TABLE_SWEET); 
		db.execSQL(CREATE_TABLE_SWEET);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	public void addToDB(String phone, String fax, String email, String website, String menu, String orderForm){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SWEET_PHONE, phone);
		values.put(SWEET_FAX, fax);
		values.put(SWEET_EMAIL, email);
		values.put(SWEET_WEBSITE, website);
		values.put(SWEET_MENU, menu);
		values.put(SWEET_ORDER_FORM, orderForm);

		db.insert(TABLE, null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> sweet = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;

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

	public void deleteTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE, null, null);
		db.close();
	}
}

