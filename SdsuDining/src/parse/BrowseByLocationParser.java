package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.BrowseByLocationDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class BrowseByLocationParser extends SdsuDiningParser{
	private Context context;
	
	private static  String TABLE_NAME; 
	private static  String LOCATION_ID; 
	private static  String LOCATION_NAME;
	
	private JSONArray locations = null;
	
	private String TAG = "BROWSEBYLOCATION PARSER";
	
	public BrowseByLocationParser(String url, Context context){
		this.context = context;
		TABLE_NAME = context.getString(R.string.LOCATION_TABLE);
		LOCATION_ID = context.getString(R.string.LOCATION_ID);
		LOCATION_NAME = context.getString(R.string.LOCATION_NAME);
		
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
						locations = jsonObj.getJSONArray(TABLE_NAME);
						BrowseByLocationDBHelper db = new BrowseByLocationDBHelper(context);
						
						for(int i=0; i<locations.length(); i++){
							JSONObject contact = locations.getJSONObject(i);
							String id = contact.getString(LOCATION_ID);
							String name = contact.getString(LOCATION_NAME);
							db.addToDB(id, name);
						}
						db.close();
						
					} catch (JSONException e) {
						Log.i(TAG, "ERROR: "+e.getMessage());
					}
				}
				
				return null;
			}
		};
	}
	
	

	
}
