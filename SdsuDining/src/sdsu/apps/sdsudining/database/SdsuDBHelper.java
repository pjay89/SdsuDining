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


	private static String DB_ID;
	private static String LAST_MODIFIED;
	private static String DB_PHONE;
	private static String DB_FAX;
	private static String DB_EMAIL;
	private static String DB_WEBSITE;
	private static String DB_ADDRESS;
	private static String DB_ABOUT;

	// Restaurant Table
	private static String RESTAURANT_TABLE;
	private static String RESTAURANT_ID;
	private static String RESTAURANT_NAME;
	private static String RESTAURANT_IMAGE;
	private static String RESTAURANT_LOCATION_ID;
	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_PHONE;
	private static String RESTAURANT_WEBSITE;

	// Farmers Market Table
	private static String FARMERS_TABLE;

	// Coupons Table
	private static String COUPON_TABLE;
	private static String COUPON_ID;
	private static String COUPON_IMAGE;
	private static String COUPON_EXPIRATION;

	// Catering Table
	private static String CATERING_TABLE;

	// Sweet Table
	private static String SWEET_TABLE; 

	// Contact Table
	private static String CONTACT_TABLE; 

	// Hours Table
	private static String HOURS_TABLE; 
	private static String HOURS_RESTAURANT_ID;
	private static String HOURS_DAY;
	private static String HOURS_OPEN;
	private static String HOURS_CLOSE;
	
	// GCM Table
	private static String GCM_TABLE;
	private static String GCM_REG_ID;


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

		DB_ID = context.getString(R.string.DB_ID);
		LAST_MODIFIED = context.getString(R.string.LAST_MODIFIED);
		DB_PHONE = context.getString(R.string.DB_PHONE);
		DB_FAX = context.getString(R.string.DB_FAX);
		DB_EMAIL = context.getString(R.string.DB_EMAIL);
		DB_WEBSITE = context.getString(R.string.DB_WEBSITE);
		DB_ADDRESS = context.getString(R.string.DB_ADDRESS);
		DB_ABOUT = context.getString(R.string.DB_ABOUT);

		//Farmer's Market Table
		FARMERS_TABLE = context.getString(R.string.FARMERS_TABLE);

		//Coupon Table
		COUPON_TABLE = context.getString(R.string.COUPON_TABLE);
		COUPON_ID = context.getString(R.string.COUPON_ID);
		COUPON_IMAGE = context.getString(R.string.COUPON_IMAGE);
		COUPON_EXPIRATION = context.getString(R.string.COUPON_EXPIRATION);

		//Catering Table
		CATERING_TABLE = context.getString(R.string.CATERING_TABLE);

		//Sweet Table
		SWEET_TABLE = context.getString(R.string.SWEET_TABLE);

		//Contact Table
		CONTACT_TABLE = context.getString(R.string.CONTACT_TABLE);

		//Hours Table
		HOURS_TABLE = context.getString(R.string.HOURS_TABLE);
		HOURS_RESTAURANT_ID = context.getString(R.string.HOURS_RESTAURANT_ID);
		HOURS_DAY = context.getString(R.string.HOURS_DAY);
		HOURS_OPEN = context.getString(R.string.HOURS_OPEN);
		HOURS_CLOSE = context.getString(R.string.HOURS_CLOSE);
		
		//GCM Table
		GCM_TABLE = context.getString(R.string.GCM_TABLE);
		GCM_REG_ID = context.getString(R.string.GCM_REG_ID);

	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// RESTAURANT table create statement
		String CREATE_TABLE_RESTAURANT = "CREATE TABLE "
				+ RESTAURANT_TABLE + "(" + RESTAURANT_ID + " VARCHAR PRIMARY KEY," + RESTAURANT_NAME
				+ " TEXT," + RESTAURANT_IMAGE + " TEXT," + RESTAURANT_LOCATION_ID 
				+ " TEXT," + RESTAURANT_LOCATION_NAME + " TEXT," + RESTAURANT_PHONE 
				+ " TEXT," + RESTAURANT_WEBSITE + " TEXT," + LAST_MODIFIED + " TIME)"; 
		Log.i(TAG, CREATE_TABLE_RESTAURANT);
		db.execSQL(CREATE_TABLE_RESTAURANT);

		//FARMERS table create statement
		String CREATE_TABLE_FARMERS = "CREATE TABLE "
				+ FARMERS_TABLE + "(" + DB_ID + " VARCHAR PRIMARY KEY," + DB_PHONE
				+ " TEXT," + DB_FAX + " TEXT," + DB_EMAIL 
				+ " TEXT," + DB_WEBSITE + " TEXT," + DB_ADDRESS 
				+ " TEXT," + DB_ABOUT + " TEXT," + LAST_MODIFIED + " TIME)"; 
		Log.i(TAG, CREATE_TABLE_FARMERS);
		db.execSQL(CREATE_TABLE_FARMERS);
		
		//COUPON table create statement
		String CREATE_TABLE_COUPON = "CREATE TABLE "
				+ COUPON_TABLE + "(" + COUPON_ID + " VARCHAR PRIMARY KEY," + COUPON_IMAGE
				+ " TEXT," + COUPON_EXPIRATION + " DATE," + LAST_MODIFIED + " TIME)"; 
		Log.i(TAG, CREATE_TABLE_COUPON);
		db.execSQL(CREATE_TABLE_COUPON);

		//CATERING table create statement
		String CREATE_TABLE_CATERING = "CREATE TABLE "
				+ CATERING_TABLE + "(" + DB_ID + " VARCHAR PRIMARY KEY," + DB_PHONE
				+ " TEXT," + DB_FAX + " TEXT," + DB_EMAIL 
				+ " TEXT," + DB_WEBSITE + " TEXT," + DB_ADDRESS 
				+ " TEXT," + DB_ABOUT + " TEXT," + LAST_MODIFIED + " TIME)"; 
		Log.i(TAG, CREATE_TABLE_CATERING);
		db.execSQL(CREATE_TABLE_CATERING);

		// SWEET table create statement
		String CREATE_TABLE_SWEET = "CREATE TABLE "
				+ SWEET_TABLE + "(" + DB_ID + " VARCHAR PRIMARY KEY," + DB_PHONE
				+ " TEXT," + DB_FAX + " TEXT," + DB_EMAIL 
				+ " TEXT," + DB_WEBSITE + " TEXT," + DB_ADDRESS 
				+ " TEXT," + DB_ABOUT + " TEXT," + LAST_MODIFIED + " TIME)"; 
		Log.i(TAG, CREATE_TABLE_SWEET);
		db.execSQL(CREATE_TABLE_SWEET);


		// CONTACT table create statement
		String CREATE_TABLE_CONTACT = "CREATE TABLE "
				+ CONTACT_TABLE + "(" + DB_ID + " VARCHAR PRIMARY KEY," + DB_PHONE
				+ " TEXT," + DB_FAX + " TEXT," + DB_EMAIL 
				+ " TEXT," + DB_WEBSITE + " TEXT," + DB_ADDRESS 
				+ " TEXT," + DB_ABOUT + " TEXT," + LAST_MODIFIED + " TIME)"; 
		Log.i(TAG, CREATE_TABLE_CONTACT);
		db.execSQL(CREATE_TABLE_CONTACT);

		// HOURS table create statement
		String CREATE_TABLE_HOURS = "CREATE TABLE "
				+ HOURS_TABLE + "(" + DB_ID + " VARCHAR PRIMARY KEY," + HOURS_RESTAURANT_ID
				+ " TEXT," + HOURS_DAY + " TEXT," + HOURS_OPEN 
				+ " TEXT," + HOURS_CLOSE + " TEXT," + LAST_MODIFIED + " TIME)";
		Log.i(TAG, CREATE_TABLE_HOURS);
		db.execSQL(CREATE_TABLE_HOURS);
		
		// GCM table create statement
				String CREATE_TABLE_GCM = "CREATE TABLE "
						+ GCM_TABLE + "(" + DB_ID + " VARCHAR PRIMARY KEY," 
						+ GCM_REG_ID + " TEXT)";
				Log.i(TAG, CREATE_TABLE_GCM);
				db.execSQL(CREATE_TABLE_GCM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + FARMERS_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + COUPON_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CATERING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + SWEET_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + HOURS_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + GCM_TABLE);
		onCreate(db);
	}

	/* ******* RESTAURANTS ******* */
	public void addToRestaurantTable(String id, String name, String image, String locationId, String locationName, String phone, String website, String lastModified){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(RESTAURANT_ID, id);
		values.put(RESTAURANT_NAME, name);
		values.put(RESTAURANT_IMAGE, image);
		values.put(RESTAURANT_LOCATION_ID, locationId);
		values.put(RESTAURANT_LOCATION_NAME, locationName);
		values.put(RESTAURANT_PHONE, phone);
		values.put(RESTAURANT_WEBSITE, website);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(RESTAURANT_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(RESTAURANT_TABLE, values, (RESTAURANT_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}

		db.close();
	}


	public ArrayList<HashMap<String, String>> getUniqueRestaurants(){
		ArrayList<HashMap<String, String>> restaurants = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_NAME + ", "+ RESTAURANT_IMAGE + " FROM " + RESTAURANT_TABLE + " GROUP BY " + RESTAURANT_NAME;

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

	public ArrayList<HashMap<String, String>> getUniqueLocations(){
		ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String,String>>();
		String query = "SELECT " + RESTAURANT_LOCATION_NAME + ", "+ RESTAURANT_LOCATION_ID + " FROM " + RESTAURANT_TABLE + " GROUP BY " + RESTAURANT_LOCATION_NAME;

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
		String query = "SELECT " + RESTAURANT_ID + ", "+ RESTAURANT_NAME + ", "+ RESTAURANT_IMAGE + " FROM " + RESTAURANT_TABLE + " WHERE " + RESTAURANT_LOCATION_NAME + "=\"" + locationName + "\" ORDER BY " + RESTAURANT_NAME;
		
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


	/* ******* FARMER'S MARKET ******* */
	public void addToFarmersTable(String id, String phone, String fax, String email, String website, String address, String about, String lastModified){
		SQLiteDatabase db = this.getWritableDatabase();
				
		ContentValues values = new ContentValues();
		values.put(DB_ID, id);
		values.put(DB_PHONE, phone);
		values.put(DB_FAX, fax);
		values.put(DB_EMAIL, email);
		values.put(DB_WEBSITE, website);
		values.put(DB_ADDRESS, address);
		values.put(DB_ABOUT, about);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(FARMERS_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(FARMERS_TABLE, values, (DB_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}

		db.close();
	}

	
	public ArrayList<HashMap<String, String>> getFarmersDetails(){
		ArrayList<HashMap<String, String>> farmers = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + FARMERS_TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(DB_PHONE, cursor.getString(1));
			entry.put(DB_FAX, cursor.getString(2));
			entry.put(DB_EMAIL, cursor.getString(3));
			entry.put(DB_WEBSITE, cursor.getString(4));
			entry.put(DB_ADDRESS, cursor.getString(5));
			entry.put(DB_ABOUT, cursor.getString(6));
			farmers.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return farmers;
	}	

	
	/* ******* COUPON ******* */
	public void addToCouponTable(String id, String image, String expiration, String lastModified){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COUPON_ID, id);
		values.put(COUPON_IMAGE, image);
		values.put(COUPON_EXPIRATION, expiration);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(COUPON_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(COUPON_TABLE, values, (COUPON_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}

		db.close();
	}

	public ArrayList<HashMap<String, String>> getCouponDetails(){
		ArrayList<HashMap<String, String>> coupons = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + COUPON_TABLE + " WHERE " + COUPON_EXPIRATION + " >= DATE('now', 'localtime') OR " + COUPON_EXPIRATION + "= '' ORDER BY " + COUPON_EXPIRATION;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(COUPON_ID, cursor.getString(0));
			entry.put(COUPON_IMAGE, cursor.getString(1));
			entry.put(COUPON_EXPIRATION, cursor.getString(2));

			coupons.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return coupons;
	}
	
	

	/* ******* CATERING ******* */
	public void addToCateringTable(String id, String phone, String fax, String email, String website, String address, String about, String lastModified){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DB_ID, id);
		values.put(DB_PHONE, phone);
		values.put(DB_FAX, fax);
		values.put(DB_EMAIL, email);
		values.put(DB_WEBSITE, website);
		values.put(DB_ADDRESS, address);
		values.put(DB_ABOUT, about);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(CATERING_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(CATERING_TABLE, values, (DB_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}

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
			entry.put(DB_PHONE, cursor.getString(1));
			entry.put(DB_FAX, cursor.getString(2));
			entry.put(DB_EMAIL, cursor.getString(3));
			entry.put(DB_WEBSITE, cursor.getString(4));
			entry.put(DB_ADDRESS, cursor.getString(5));
			entry.put(DB_ABOUT, cursor.getString(6));

			catering.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return catering;
	}



	/* ******* SWEET ******* */
	public void addToSweetTable(String id, String phone, String fax, String email, String website, String address, String about, String lastModified){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DB_ID, id);
		values.put(DB_PHONE, phone);
		values.put(DB_FAX, fax);
		values.put(DB_EMAIL, email);
		values.put(DB_WEBSITE, website);
		values.put(DB_ADDRESS, address);
		values.put(DB_ABOUT, about);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(SWEET_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(SWEET_TABLE, values, (DB_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}

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
			entry.put(DB_PHONE, cursor.getString(1));
			entry.put(DB_FAX, cursor.getString(2));
			entry.put(DB_EMAIL, cursor.getString(3));
			entry.put(DB_WEBSITE, cursor.getString(4));
			entry.put(DB_ADDRESS, cursor.getString(5));
			entry.put(DB_ABOUT, cursor.getString(6));
			sweet.add(entry);
			cursor.moveToNext();
		}

		cursor.close();
		db.close();

		return sweet;
	}



	/* ******* CONTACT ******* */
	public void addToContactTable(String id, String phone, String fax, String email, String website, String address, String about, String lastModified){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DB_ID, id);
		values.put(DB_PHONE, phone);
		values.put(DB_FAX, fax);
		values.put(DB_EMAIL, email);
		values.put(DB_WEBSITE, website);
		values.put(DB_ADDRESS, address);
		values.put(DB_ABOUT, about);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(CONTACT_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(CONTACT_TABLE, values, (DB_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}

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
			entry.put(DB_PHONE, cursor.getString(1));
			entry.put(DB_FAX, cursor.getString(2));
			entry.put(DB_EMAIL, cursor.getString(3));
			entry.put(DB_WEBSITE, cursor.getString(4));
			entry.put(DB_ADDRESS, cursor.getString(5));
			entry.put(DB_ABOUT, cursor.getString(6));
			contact.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return contact;
	}



	/* ******* HOURS ******* */
	public void addToHoursTable(String id, String lastModified, String restaurantId, String day, String open, String close){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(DB_ID, id);
		values.put(HOURS_RESTAURANT_ID, restaurantId);
		values.put(HOURS_DAY, day);
		values.put(HOURS_OPEN, open);
		values.put(HOURS_CLOSE, close);
		values.put(LAST_MODIFIED, lastModified);
		
		int result = (int) db.insertWithOnConflict(HOURS_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(HOURS_TABLE, values, (DB_ID + "=? AND " + LAST_MODIFIED+"<?"), new String[]{id, lastModified});
		}
		
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

		if(hours.length() > 0){
			//remove first newline character
			hours = hours.substring(1);
		}
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
	
	
	/* ******* GCM ******* */
	public void storeGCMRegId(String regId){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(DB_ID, GCM_REG_ID);
		values.put(GCM_REG_ID, regId);
		
		int result = (int) db.insertWithOnConflict(GCM_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		if(result == -1){
			db.update(GCM_TABLE, values, (DB_ID + "=?"), new String[]{GCM_REG_ID});
		}		
		db.close();
	}
	public String getGCMRegId(){
		String regId = "";
		String query = "SELECT " + GCM_REG_ID + " FROM " + GCM_TABLE; 
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			regId = cursor.getString(0);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return regId;
	}
}
