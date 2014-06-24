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

public class SdsuDBHelper extends SQLiteOpenHelper{

	private static final String TAG = "DATABASEHELPER";

	// Database Version
	private static final int DATABASE_VERSION = 1;
	
	// Restaurant Table
	private static String RESTAURANT_TABLE;
	private static String RESTAURANT_ID;
	private static String RESTAURANT_NAME;
	private static String RESTAURANT_IMAGE;
	private static String RESTAURANT_LOCATION_ID;
	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_PHONE;
	private static String RESTAURANT_WEBSITE;

	private static String DB_FARMERS_MARKET;
	
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

	// Hours Table
	private static String HOURS_TABLE; 
	private static String HOURS_RESTAURANT_ID;
	private static String HOURS_DAY;
	private static String HOURS_OPEN;
	private static String HOURS_CLOSE;
	



	public SdsuDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		//Restaurant Table
		RESTAURANT_TABLE = context.getString(R.string.RESTAURANT_TABLE);
		RESTAURANT_ID = context.getString(R.string.RESTAURANT_ID);
		RESTAURANT_NAME = context.getString(R.string.RESTAURANT_NAME);
		RESTAURANT_IMAGE = context.getString(R.string.RESTAURANT_IMAGE);
		RESTAURANT_LOCATION_ID = context.getString(R.string.RESTAURANT_LOCATION_ID);
		RESTAURANT_LOCATION_NAME = context.getString(R.string.RESTAURANT_LOCATION_NAME);
		RESTAURANT_PHONE = context.getString(R.string.RESTAURANT_PHONE);
		RESTAURANT_WEBSITE = context.getString(R.string.RESTAURANT_WEBSITE);
		
		DB_FARMERS_MARKET = context.getString(R.string.DB_FARMERS_MARKET);
		
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
		
		//Hours Table
		HOURS_TABLE = context.getString(R.string.HOURS_TABLE);
		HOURS_RESTAURANT_ID = context.getString(R.string.HOURS_RESTAURANT_ID);
		HOURS_DAY = context.getString(R.string.HOURS_DAY);
		HOURS_OPEN = context.getString(R.string.HOURS_OPEN);
		HOURS_CLOSE = context.getString(R.string.HOURS_CLOSE);
	}










	@Override
	public void onCreate(SQLiteDatabase db) {
		// RESTAURANT table create statement
		String CREATE_TABLE_RESTAURANT = "CREATE TABLE "
				+ RESTAURANT_TABLE + "(" + RESTAURANT_ID + " VARCHAR PRIMARY KEY," + RESTAURANT_NAME
				+ " TEXT," + RESTAURANT_IMAGE + " TEXT," + RESTAURANT_LOCATION_ID 
				+ " TEXT," + RESTAURANT_LOCATION_NAME + " TEXT," + RESTAURANT_PHONE 
				+ " TEXT," + RESTAURANT_WEBSITE + " TEXT"+ ")"; 
		Log.i(TAG, CREATE_TABLE_RESTAURANT);
		db.execSQL(CREATE_TABLE_RESTAURANT);

		//CATERING table create statement
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
		
		// HOURS table create statement
		String CREATE_TABLE_HOURS = "CREATE TABLE "
				+ HOURS_TABLE + "(id INTEGER PRIMARY KEY," + HOURS_RESTAURANT_ID
				+ " TEXT," + HOURS_DAY + " TEXT," + HOURS_OPEN 
				+ " TEXT," + HOURS_CLOSE + " TEXT"+ ")";
		Log.i(TAG, CREATE_TABLE_HOURS);
		db.execSQL(CREATE_TABLE_HOURS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CATERING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + SWEET_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + HOURS_TABLE);
		onCreate(db);
	}
	
	/* ******* RESTAURANTS ******* */
	public void addToRestaurantTable(String id, String name, String image, String locationId, String locationName, String phone, String website){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(RESTAURANT_ID, id);
		values.put(RESTAURANT_NAME, name);
		values.put(RESTAURANT_IMAGE, image);
		values.put(RESTAURANT_LOCATION_ID, locationId);
		values.put(RESTAURANT_LOCATION_NAME, locationName);
		values.put(RESTAURANT_PHONE, phone);
		values.put(RESTAURANT_WEBSITE, website);

		db.insert(RESTAURANT_TABLE, null, values);
		db.close();
	}
	
	
	public ArrayList<HashMap<String, String>> getUniqueRestaurantsExceptFarmersMarket(){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_NAME + ", "+ RESTAURANT_IMAGE + " FROM " + RESTAURANT_TABLE + " WHERE " + RESTAURANT_LOCATION_NAME + " <> '" + DB_FARMERS_MARKET + "' GROUP BY " + RESTAURANT_NAME;

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
	
	public ArrayList<HashMap<String, String>> getAllRestaurantDetailsOf(String restaurantName){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + RESTAURANT_TABLE + " WHERE " + RESTAURANT_NAME + "=\"" + restaurantName + "\" ORDER BY " + RESTAURANT_LOCATION_NAME;

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
	
	public ArrayList<HashMap<String, String>> getUniqueLocationsExceptFarmersMarket(){
		ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_LOCATION_NAME + ", "+ RESTAURANT_LOCATION_ID + " FROM " + RESTAURANT_TABLE + " WHERE " + RESTAURANT_LOCATION_NAME + " <> '" + DB_FARMERS_MARKET + "' GROUP BY " + RESTAURANT_LOCATION_NAME;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(RESTAURANT_LOCATION_NAME, cursor.getString(0));
			entry.put(RESTAURANT_LOCATION_ID, cursor.getString(1));
			locations.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return locations;
	}
	
		
	public ArrayList<HashMap<String, String>> getRestaurantsAt(String locationName){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_ID + ", "+ RESTAURANT_NAME + ", "+ RESTAURANT_IMAGE + " FROM " + RESTAURANT_TABLE + " WHERE " + RESTAURANT_LOCATION_NAME + "=\"" + locationName + "\"";
		
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
	
	
	public ArrayList<HashMap<String, String>> getRestaurantsAtFarmersMarket(){
		return getRestaurantsAt(DB_FARMERS_MARKET);
	}


	

	

	
	public ArrayList<HashMap<String, String>> getAllRestaurantsAt(String locationId){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + RESTAURANT_TABLE + " WHERE " + RESTAURANT_LOCATION_ID + "=\"" + locationId + "\"";

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
		db.close();
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
	
	
	
	/* ******* HOURS ******* */
	public void addToHoursTable(String restaurantId, String day, String open, String close){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(HOURS_RESTAURANT_ID, restaurantId);
		values.put(HOURS_DAY, day);
		values.put(HOURS_OPEN, open);
		values.put(HOURS_CLOSE, close);
		
		db.insert(HOURS_TABLE, null, values);
		db.close();
	}
	
	public String getHoursFor(String restaurandId, String day){
		String hours = "";
		String query = "SELECT " + HOURS_OPEN + ", " + HOURS_CLOSE + " FROM " + HOURS_TABLE + " WHERE " + HOURS_RESTAURANT_ID + "=\"" + restaurandId + "\" AND " + HOURS_DAY + "=\"" + day + "\"";
		

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			hours += "\n" + cursor.getString(0) + " - " + cursor.getString(1);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		hours = hours.substring(1);
		return hours;
	}
	
	public ArrayList<String[]> getHoursForRestaurantStatus(String restaurandId, String today){
		ArrayList<String[]> closingTimes = new ArrayList<String[]>();
		
		String query = "SELECT " + HOURS_OPEN + ", " + HOURS_CLOSE + " FROM " + HOURS_TABLE + " WHERE " + HOURS_RESTAURANT_ID + "=\"" + restaurandId + "\" AND " + HOURS_DAY + "=\"" + today + "\"";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			String[] hours = new String[2];
			hours[0] = cursor.getString(0);
			hours[1] = cursor.getString(1);
			
			closingTimes.add(hours);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		
		return closingTimes;
	}

	/* **************************************************************** */
	public void deleteRestaurantTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(RESTAURANT_TABLE, null, null);
		db.close();
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
	
	public void deleteHoursTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(HOURS_TABLE, null, null);
		db.close();
	}
}
