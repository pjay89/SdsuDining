package sdsu.apps.sdsudining.database;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BrowseByLocationDBHelper extends SQLiteOpenHelper{
	
	// Database Version
    private static final int DATABASE_VERSION = 1;
        
    // Table Name
    private static String TABLE; 
    
    // Column names
    private static String LOCATION_ID;
    private static String LOCATION_NAME;
    
    public BrowseByLocationDBHelper(Context context){
    	super(context, context.getString(R.string.DATABASE_NAME), null, DATABASE_VERSION);
    	TABLE = context.getString(R.string.LOCATION_TABLE);
    	LOCATION_ID = context.getString(R.string.LOCATION_ID);
    	LOCATION_NAME = context.getString(R.string.LOCATION_NAME);
    	
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// BROWSEBYLOCATION table create statement
	    String CREATE_TABLE_BROWSEBYLOCATION = "CREATE TABLE "
	            + TABLE + "(" + LOCATION_ID + " VARCHAR PRIMARY KEY," 
	    		+ LOCATION_NAME + " TEXT)";
		
	    db.execSQL(CREATE_TABLE_BROWSEBYLOCATION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}
	
	public void addToDB(String id, String name){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(LOCATION_ID, id);
		values.put(LOCATION_NAME, name);
		
		db.insert(TABLE, null, values);
		db.close();
	}
    
	public ArrayList<HashMap<String, String>> getDetails(){
		ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM " + TABLE;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		//Move to first row
		cursor.moveToFirst();
		while(cursor.getCount() > 0 && !cursor.isAfterLast()){
			HashMap<String, String> location = new HashMap<String, String>();
			location.put(LOCATION_ID, cursor.getString(0));
			location.put(LOCATION_NAME, cursor.getString(1));
			locations.add(location);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		
		return locations;
	}
	
}
