package sdsu.apps.sdsudining.database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CateringDBHelper extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Table Name
	private static String TABLE; 

	// Column names
	private static String CATERING_PHONE;
	private static String CATERING_FAX;
	private static String CATERING_EMAIL;
	private static String CATERING_ADDRESS;
	private static String CATERING_SNIPPET;
	private static String CATERING_GUIDELINES;
	private static String CATERING_SERVICE_LEVEL;

	public CateringDBHelper(Context context){
		super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
		TABLE = context.getString(R.string.CATERING_TABLE);
		CATERING_PHONE = context.getString(R.string.CATERING_PHONE);
		CATERING_FAX = context.getString(R.string.CATERING_FAX);
		CATERING_EMAIL = context.getString(R.string.CATERING_EMAIL);
		CATERING_ADDRESS = context.getString(R.string.CATERING_ADDRESS);
		CATERING_SNIPPET = context.getString(R.string.CATERING_SNIPPET);
		CATERING_GUIDELINES = context.getString(R.string.CATERING_GUIDELINES);
		CATERING_SERVICE_LEVEL = context.getString(R.string.CATERING_SERVICE_LEVEL);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// CATERING table create statement
		String CREATE_TABLE_CATERING = "CREATE TABLE "
				+ TABLE + "(id INTEGER PRIMARY KEY," + CATERING_PHONE
				+ " TEXT," + CATERING_FAX + " TEXT," + CATERING_EMAIL 
				+ " TEXT," + CATERING_ADDRESS + " TEXT," + CATERING_SNIPPET 
				+ " TEXT," + CATERING_GUIDELINES + " TEXT," + CATERING_SERVICE_LEVEL 
				+ " TEXT"+ ")"; 

		db.execSQL(CREATE_TABLE_CATERING);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	public void addToDB(String phone, String fax, String email, String address, String snippet, String guidelines, String level){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CATERING_PHONE, phone);
		values.put(CATERING_FAX, fax);
		values.put(CATERING_EMAIL, email);
		values.put(CATERING_ADDRESS, address);
		values.put(CATERING_SNIPPET, snippet);
		values.put(CATERING_GUIDELINES, guidelines);
		values.put(CATERING_SERVICE_LEVEL, level);

		db.insert(TABLE, null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> catering = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;

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

}