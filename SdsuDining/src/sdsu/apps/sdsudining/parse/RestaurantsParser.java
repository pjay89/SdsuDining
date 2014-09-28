package sdsu.apps.sdsudining.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.objects.BusyWait;
import android.content.Context;
import android.util.Log;

public class RestaurantsParser extends SdsuDiningParser{
	private Context context;

	private static  String RESTAURANTS_OBJECT_TAG;
	private static String RESTAURANT_ID;
	private static String RESTAURANT_NAME;
	private static String RESTAURANT_IMAGE;
	private static String RESTAURANT_LOCATION_ID;
	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_PHONE;
	private static String RESTAURANT_WEBSITE;

	private JSONArray restaurants = null;
	private BusyWait busyWait;
	
	private String TAG = "PARSER";
	

	public RestaurantsParser(String url, Context context, BusyWait busyWait){
		this.context = context;
		this.busyWait = busyWait;
		this.busyWait.show();
		
		RESTAURANTS_OBJECT_TAG = context.getString(R.string.RESTAURANTS_OBJECT_TAG);
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
						restaurants = jsonObj.getJSONArray(RESTAURANTS_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						db.deleteRestaurantTable();
						
						for(int i=0; i<restaurants.length(); i++){
							JSONObject entry = restaurants.getJSONObject(i);
							String id = entry.getString(RESTAURANT_ID);
							String name = entry.getString(RESTAURANT_NAME);
							String image = entry.getString(RESTAURANT_IMAGE);
							String locationId = entry.getString(RESTAURANT_LOCATION_ID);
							String locationName = entry.getString(RESTAURANT_LOCATION_NAME);
							String phone = entry.getString(RESTAURANT_PHONE);
							String website = entry.getString(RESTAURANT_WEBSITE);
							db.addToRestaurantTable(id, name, image, locationId, locationName, phone, website);
						}
						
					}
					catch (JSONException e) {
						Log.i(TAG, "ERROR: "+e.getMessage());
					}
				}
				busyWait.dismiss();
				return null;
			}
		};
	}

}
