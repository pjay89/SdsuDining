package sdsu.apps.sdsudining.database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDBHelper extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Table Name
	private static String TABLE; 

	// Column names
	private static String RESTAURANT_ID;
	private static String RESTAURANT_NAME;
	private static String RESTAURANT_IMAGE;
	private static String RESTAURANT_LOCATION_ID;
	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_PHONE;
	private static String RESTAURANT_WEBSITE;

	public RestaurantDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		TABLE = context.getString(R.string.RESTAURANT_TABLE);
		RESTAURANT_ID = context.getString(R.string.RESTAURANT_ID);
		RESTAURANT_NAME = context.getString(R.string.RESTAURANT_NAME);
		RESTAURANT_IMAGE = context.getString(R.string.RESTAURANT_IMAGE);
		RESTAURANT_LOCATION_ID = context.getString(R.string.RESTAURANT_LOCATION_ID);
		RESTAURANT_LOCATION_NAME = context.getString(R.string.RESTAURANT_LOCATION_NAME);
		RESTAURANT_PHONE = context.getString(R.string.RESTAURANT_PHONE);
		RESTAURANT_WEBSITE = context.getString(R.string.RESTAURANT_WEBSITE);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// RESTAURANT table create statement
		String CREATE_TABLE_RESTAURANT = "CREATE TABLE "
				+ TABLE + "(" + RESTAURANT_ID + " VARCHAR PRIMARY KEY," + RESTAURANT_NAME
				+ " TEXT," + RESTAURANT_IMAGE + " TEXT," + RESTAURANT_LOCATION_ID 
				+ " TEXT," + RESTAURANT_LOCATION_NAME + " TEXT," + RESTAURANT_PHONE 
				+ " TEXT," + RESTAURANT_WEBSITE + " TEXT"+ ")"; 

		db.execSQL(CREATE_TABLE_RESTAURANT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}
	
	public void addToDB(String id, String name, String image, String locationId, String locationName, String phone, String website){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(RESTAURANT_ID, id);
		values.put(RESTAURANT_NAME, name);
		values.put(RESTAURANT_IMAGE, image);
		values.put(RESTAURANT_LOCATION_ID, locationId);
		values.put(RESTAURANT_LOCATION_NAME, locationName);
		values.put(RESTAURANT_PHONE, phone);
		values.put(RESTAURANT_WEBSITE, website);

		db.insert(TABLE, null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(RESTAURANT_ID, cursor.getString(0));
			entry.put(RESTAURANT_NAME, cursor.getString(1));
			entry.put(RESTAURANT_IMAGE, cursor.getString(2));
			entry.put(RESTAURANT_LOCATION_ID, cursor.getString(3));
			entry.put(RESTAURANT_LOCATION_NAME, cursor.getString(4));
			entry.put(RESTAURANT_PHONE, cursor.getString(5));
			entry.put(RESTAURANT_WEBSITE, cursor.getString(6));
			restaurants.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return restaurants;
	}

	public ArrayList<HashMap<String, String>> getAllRestaurants(){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_NAME + ", "+ RESTAURANT_IMAGE + " FROM " + TABLE + "GROUP BY " + RESTAURANT_NAME;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(RESTAURANT_NAME, cursor.getString(0));
			entry.put(RESTAURANT_IMAGE, cursor.getString(1));
			restaurants.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return restaurants;
	}
	
	public ArrayList<HashMap<String, String>> getAllLocationsOf(String restaurantName){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_ID + ", "+ RESTAURANT_NAME + ", "+ RESTAURANT_IMAGE + " FROM " + TABLE + "WHERE " + RESTAURANT_NAME + "='" + restaurantName + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(RESTAURANT_ID, cursor.getString(0));
			entry.put(RESTAURANT_NAME, cursor.getString(1));
			entry.put(RESTAURANT_IMAGE, cursor.getString(2));
			restaurants.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return restaurants;
	}
	
	public ArrayList<HashMap<String, String>> getAllRestaurantsAt(String locationId){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE + "WHERE " + RESTAURANT_LOCATION_ID + "='" + locationId + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(RESTAURANT_ID, cursor.getString(0));
			entry.put(RESTAURANT_NAME, cursor.getString(1));
			entry.put(RESTAURANT_IMAGE, cursor.getString(2));
			entry.put(RESTAURANT_LOCATION_ID, cursor.getString(3));
			entry.put(RESTAURANT_LOCATION_NAME, cursor.getString(4));
			entry.put(RESTAURANT_PHONE, cursor.getString(5));
			entry.put(RESTAURANT_WEBSITE, cursor.getString(6));
			restaurants.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return restaurants;
	}
}
