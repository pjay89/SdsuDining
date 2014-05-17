package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.RestaurantDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class RestaurantsParser extends SdsuDiningParser{
	private Context context;

	private static  String TABLE_NAME;
	private static String RESTAURANT_ID;
	private static String RESTAURANT_NAME;
	private static String RESTAURANT_IMAGE;
	private static String RESTAURANT_LOCATION_ID;
	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_PHONE;
	private static String RESTAURANT_WEBSITE;

	private JSONArray restaurants = null;

	private String TAG = "RESTAURANTS PARSER";

	public RestaurantsParser(String url, Context context){
		this.context = context;
		TABLE_NAME = context.getString(R.string.CATERING_TABLE);
		RESTAURANT_ID = context.getString(R.string.RESTAURANT_ID);
		RESTAURANT_NAME = context.getString(R.string.RESTAURANT_NAME);
		RESTAURANT_IMAGE = context.getString(R.string.RESTAURANT_IMAGE);
		RESTAURANT_LOCATION_ID = context.getString(R.string.RESTAURANT_LOCATION_ID);
		RESTAURANT_LOCATION_NAME = context.getString(R.string.RESTAURANT_LOCATION_NAME);
		RESTAURANT_PHONE = context.getString(R.string.RESTAURANT_PHONE);
		RESTAURANT_WEBSITE = context.getString(R.string.RESTAURANT_WEBSITE);

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
						restaurants = jsonObj.getJSONArray(TABLE_NAME);
						RestaurantDBHelper db = new RestaurantDBHelper(context);
						
						for(int i=0; i<restaurants.length(); i++){
							JSONObject entry = restaurants.getJSONObject(i);
							String id = entry.getString(RESTAURANT_ID);
							String name = entry.getString(RESTAURANT_NAME);
							String image = entry.getString(RESTAURANT_IMAGE);
							String locationId = entry.getString(RESTAURANT_LOCATION_ID);
							String locationName = entry.getString(RESTAURANT_LOCATION_NAME);
							String phone = entry.getString(RESTAURANT_PHONE);
							String website = entry.getString(RESTAURANT_WEBSITE);
							db.addToDB(id, name, image, locationId, locationName, phone, website);
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
