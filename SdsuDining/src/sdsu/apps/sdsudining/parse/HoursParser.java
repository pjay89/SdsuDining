package sdsu.apps.sdsudining.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.content.Context;
import android.util.Log;

public class HoursParser extends SdsuDiningParser{
	private Context context;
	
	private static String HOURS_OBJECT_TAG; 
	private static String HOURS_RESTAURANT_ID;
	private static String HOURS_DAY;
	private static String HOURS_OPEN;
	private static String HOURS_CLOSE;
	
	private JSONArray hours = null;
	
	private String TAG = "PARSER";
	
	public HoursParser(String url, Context context){
		this.context = context;
		HOURS_OBJECT_TAG = context.getString(R.string.HOURS_OBJECT_TAG);
		HOURS_RESTAURANT_ID = context.getString(R.string.HOURS_RESTAURANT_ID);
		HOURS_DAY = context.getString(R.string.HOURS_DAY);
		HOURS_OPEN = context.getString(R.string.HOURS_OPEN);
		HOURS_CLOSE = context.getString(R.string.HOURS_CLOSE);
		
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
						hours = jsonObj.getJSONArray(HOURS_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						db.deleteHoursTable();
						
						for(int i=0; i<hours.length(); i++){
							JSONObject entry = hours.getJSONObject(i);
							String restaurantId = entry.getString(HOURS_RESTAURANT_ID);
							String day = entry.getString(HOURS_DAY);
							String open = entry.getString(HOURS_OPEN);
							String close = entry.getString(HOURS_CLOSE);							
							db.addToHoursTable(restaurantId, day, open, close);
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
