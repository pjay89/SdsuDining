package sdsu.apps.sdsudining.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;

import android.content.Context;
import android.util.Log;

public class FarmersParser extends SdsuDiningParser{

	private Context context;

	private static  String FARMERS_OBJECT_TAG;
	private static String DB_PHONE;
	private static String DB_FAX;
	private static String DB_EMAIL;
	private static String DB_WEBSITE;
	private static String DB_ADDRESS;
	private static String DB_ABOUT;
	
	private JSONArray farmers = null;
	
	private String TAG = "PARSER";
	
	public FarmersParser(String url, Context context){
		this.context = context;
		FARMERS_OBJECT_TAG = context.getString(R.string.FARMERS_OBJECT_TAG);
		DB_PHONE = context.getString(R.string.DB_PHONE);
		DB_FAX = context.getString(R.string.DB_FAX);
		DB_EMAIL = context.getString(R.string.DB_EMAIL);
		DB_WEBSITE = context.getString(R.string.DB_WEBSITE);
		DB_ADDRESS = context.getString(R.string.DB_ADDRESS);
		DB_ABOUT = context.getString(R.string.DB_ABOUT);
		
		AsyncWebServiceCall ws = new AsyncWebServiceCall();
		ws.execute(url);
	}
	
	
	
	@Override
	protected AsyncJsonStringParser getAsyncJsonStringParser() {
		return new AsyncJsonStringParser() {

			@Override
			protected Void doInBackground(String... params) {
				String jsonString = params[0];

				if(jsonString != null){
					try {
						JSONObject jsonObj = new JSONObject(jsonString);
						farmers = jsonObj.getJSONArray(FARMERS_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						db.deleteFarmersTable();
						
						for(int i=0; i<farmers.length(); i++){
							JSONObject entry = farmers.getJSONObject(i);
							String phone = entry.getString(DB_PHONE);
							String fax = entry.getString(DB_FAX);
							String email = entry.getString(DB_EMAIL);
							String website = entry.getString(DB_WEBSITE);
							String address = entry.getString(DB_ADDRESS);
							String about = entry.getString(DB_ABOUT);
						
							db.addToFarmersTable(phone, fax, email, website, address, about);
						}
						
					}
					catch (JSONException e) {
						Log.i(TAG, "ERROR: "+e.getMessage());
					}
				}
				return null;
			}
		};
	}

}
