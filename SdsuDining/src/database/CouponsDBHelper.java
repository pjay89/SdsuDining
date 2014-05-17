package database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CouponsDBHelper extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Table Name
	private static String TABLE; 

	// Column names
	private static String COUPON_ID;
	private static String COUPON_IMAGE;
	private static String COUPON_LOCATION_ID;
	private static String COUPON_RESTAURANT_ID;
	private static String COUPON_RESTAURANT_NAME;
	private static String COUPON_DEAL;
	private static String COUPON_EXPIRATION;

	public CouponsDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		TABLE = context.getString(R.string.COUPON_TABLE);
		COUPON_ID = context.getString(R.string.COUPON_ID);
		COUPON_IMAGE = context.getString(R.string.COUPON_IMAGE);
		COUPON_LOCATION_ID = context.getString(R.string.COUPON_LOCATION_ID);
		COUPON_RESTAURANT_ID = context.getString(R.string.COUPON_RESTAURANT_ID);
		COUPON_RESTAURANT_NAME = context.getString(R.string.COUPON_RESTAURANT_NAME);
		COUPON_DEAL = context.getString(R.string.COUPON_DEAL);
		COUPON_EXPIRATION = context.getString(R.string.COUPON_EXPIRATION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// COUPON table create statement
		String CREATE_TABLE_COUPON = "CREATE TABLE "
				+ TABLE + "(" + COUPON_ID + " VARCHAR PRIMARY KEY," + COUPON_IMAGE
				+ " TEXT," + COUPON_LOCATION_ID + " TEXT," + COUPON_RESTAURANT_ID 
				+ " TEXT," + COUPON_RESTAURANT_NAME + " TEXT," + COUPON_DEAL 
				+ " TEXT," + COUPON_EXPIRATION + " DATE"+ ")"; 

		db.execSQL(CREATE_TABLE_COUPON);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	public void addToDB(String id, String image, String locationId, String restaurantId, String restaurantName, String deal, String expiration){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COUPON_ID, id);
		values.put(COUPON_IMAGE, image);
		values.put(COUPON_LOCATION_ID, locationId);
		values.put(COUPON_RESTAURANT_ID, restaurantId);
		values.put(COUPON_RESTAURANT_NAME, restaurantName);
		values.put(COUPON_DEAL, deal);
		values.put(COUPON_EXPIRATION, expiration);

		db.insert(TABLE, null, values);
		db.close();
	}
	
	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> coupons = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(COUPON_ID, cursor.getString(0));
			entry.put(COUPON_IMAGE, cursor.getString(1));
			entry.put(COUPON_LOCATION_ID, cursor.getString(2));
			entry.put(COUPON_RESTAURANT_ID, cursor.getString(3));
			entry.put(COUPON_RESTAURANT_NAME, cursor.getString(4));
			entry.put(COUPON_DEAL, cursor.getString(5));
			entry.put(COUPON_EXPIRATION, cursor.getString(6));
			coupons.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();

		return coupons;
	}

}
