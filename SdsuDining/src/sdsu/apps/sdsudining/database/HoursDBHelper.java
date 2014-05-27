package sdsu.apps.sdsudining.database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HoursDBHelper extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Table Name
	private static String TABLE; 

	// Column names
	private static String HOURS_RESTAURANT_ID;
	private static String HOURS_DAY;
	private static String HOURS_OPEN;
	private static String HOURS_CLOSE;

	public HoursDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		TABLE = context.getString(R.string.SWEET_TABLE);
		HOURS_RESTAURANT_ID = context.getString(R.string.HOURS_RESTAURANT_ID);
		HOURS_DAY = context.getString(R.string.HOURS_DAY);
		HOURS_OPEN = context.getString(R.string.HOURS_OPEN);
		HOURS_CLOSE = context.getString(R.string.HOURS_CLOSE);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// HOURS table create statement
		String CREATE_TABLE_HOURS = "CREATE TABLE "
				+ TABLE + "( id INTEGER PRIMARY KEY AUTO INCREMENT," + HOURS_RESTAURANT_ID
				+ " TEXT," + HOURS_DAY + " TEXT," + HOURS_OPEN 
				+ " TEXT," + HOURS_CLOSE + " TEXT"+ ")";

		db.execSQL(CREATE_TABLE_HOURS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}
	
	public void addToDB(String restaurantId, String day, String open, String close){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(HOURS_RESTAURANT_ID, restaurantId);
		values.put(HOURS_DAY, day);
		values.put(HOURS_OPEN, open);
		values.put(HOURS_CLOSE, close);

		db.insert(TABLE, null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> hours = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(HOURS_RESTAURANT_ID, cursor.getString(1));
			entry.put(HOURS_DAY, cursor.getString(2));
			entry.put(HOURS_OPEN, cursor.getString(3));
			entry.put(HOURS_CLOSE, cursor.getString(4));
			hours.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return hours;
	}
	
	public ArrayList<HashMap<String, String>> getHoursFor(String restaurandId){
		ArrayList<HashMap<String, String>> hours = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE + " WHERE " + HOURS_RESTAURANT_ID + "='" + restaurandId + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(HOURS_RESTAURANT_ID, cursor.getString(1));
			entry.put(HOURS_DAY, cursor.getString(2));
			entry.put(HOURS_OPEN, cursor.getString(3));
			entry.put(HOURS_CLOSE, cursor.getString(4));
			hours.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return hours;
	}
	
	public ArrayList<HashMap<String, String>> getClosingTimeFor(String restaurandId, String day){
		ArrayList<HashMap<String, String>> hours = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE + " WHERE " + HOURS_RESTAURANT_ID + "='" + restaurandId + "' AND " + HOURS_DAY + "='" + day + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(HOURS_RESTAURANT_ID, cursor.getString(1));
			entry.put(HOURS_DAY, cursor.getString(2));
			entry.put(HOURS_OPEN, cursor.getString(3));
			entry.put(HOURS_CLOSE, cursor.getString(4));
			hours.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return hours;
	}
	
}
