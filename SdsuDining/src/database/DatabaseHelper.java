package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final String TAG = "DATABASEHELPER";
	// Database Version
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    private static final String DATABASE_NAME = "sdsuDiningDB";
    
    // Table Names
    private static final String TABLE_CONTACTS = "contacts";
    private static final String TABLE_WEATHER = "weather";
    
    // Common column names
    private static final String KEY_ID = "id";
    
    // CONTACTS table column names
    private static final String CONTACTS_NAME = "name";
    private static final String CONTACTS_MOBILE = "mobile";
    private static final String CONTACTS_HOME = "home";
    private static final String CONTACTS_ADDRS = "addrs";
    
 // CONTACTS table create statement
    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE "
            + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + CONTACTS_NAME
            + CONTACTS_MOBILE + " TEXT," + CONTACTS_HOME + " TEXT,"  
            + CONTACTS_ADDRS + " TEXT," + ")";
    
    
    public DatabaseHelper(Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_CONTACTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // create new tables
        onCreate(db);
	}



}
