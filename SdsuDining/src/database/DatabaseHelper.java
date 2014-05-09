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

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final String TAG = "DATABASEHELPER";
	// Database Version
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    //private static final String DATABASE_NAME = "sdsuDiningDB";
    
    // Table Names
    private static final String TABLE_CONTACTS = "contacts";
    
    // Common column names
    private static final String KEY_ID = "id";
    
    // CONTACTS table column names
    private static String CONTACTS_NAME;
    private static String CONTACTS_MOBILE = "mobile";
    private static String CONTACTS_HOME = "home";
    

    
    
    public DatabaseHelper(Context context){   	
    	super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
    	CONTACTS_NAME = context.getString(R.string.CONTACT_NAME);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		 // CONTACTS table create statement
	    String CREATE_TABLE_CONTACTS = "CREATE TABLE "
	            + TABLE_CONTACTS + "(" + KEY_ID + " VARCHAR PRIMARY KEY," + CONTACTS_NAME
	            + " TEXT," + CONTACTS_MOBILE + " TEXT," + CONTACTS_HOME + " TEXT" + ")"; 
	    
		db.execSQL(CREATE_TABLE_CONTACTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // create new tables
        onCreate(db);
	}


	
	
	public void addToDB(String uid, String name, String mobile, String home){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_ID, uid);
		values.put(CONTACTS_NAME, name);
		values.put(CONTACTS_MOBILE, mobile);
		values.put(CONTACTS_HOME, home);
		
		db.insert(TABLE_CONTACTS, null, values);
		db.close();
	}
	
	public ArrayList<HashMap<String, String>> getUserDetails(){
		ArrayList<HashMap<String, String>> users = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE_CONTACTS;
		Log.i(TAG, query);
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> user = new HashMap<String, String>();
			user.put(KEY_ID, cursor.getString(0));
			user.put(CONTACTS_NAME, cursor.getString(1));
			user.put(CONTACTS_MOBILE, cursor.getString(2));
			user.put(CONTACTS_HOME, cursor.getString(3));
			
			users.add(user);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		
		return users;
	}
	
	public void deleteTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, null, null);
		db.close();
		Log.i(TAG, "delete table");
	}

	public void printColumns(){
		String cmd = "SELECT sql from sqlite_master WHERE tbl_name = 'contacts' AND type = 'table'";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(cmd, null);
		cursor.moveToFirst();
		Log.i(TAG, cursor.getString(0));
		cursor.close();
		db.close();
	}
}
