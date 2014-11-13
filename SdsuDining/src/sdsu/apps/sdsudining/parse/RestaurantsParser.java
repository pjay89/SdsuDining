package sdsu.apps.sdsudining.parse;

import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.content.Context;

/**
 * Concrete parser for Restaurants category. Fetches, parses, and stores data to local db.
 * Upon completion, notify observer
 * 
 * @author Priya Jayaprakash
 *
 */

public class RestaurantsParser extends SdsuDiningParser{
	private Context context;

	private static  String RESTAURANTS_OBJECT_TAG;
	private static String RESTAURANT_ID;
	private static String LAST_MODIFIED;
	private static String ACTIVE;
	private static String RESTAURANT_NAME;
	private static String RESTAURANT_IMAGE;
	private static String RESTAURANT_LOCATION_ID;
	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_PHONE;
	private static String RESTAURANT_WEBSITE;

	private JSONArray restaurants = null;



	public RestaurantsParser(String url, Context context, Observer observer){
		this.context = context;

		this.addObserver(observer);

		RESTAURANTS_OBJECT_TAG = context.getString(R.string.RESTAURANTS_OBJECT_TAG);
		RESTAURANT_ID = context.getString(R.string.RESTAURANT_ID);
		LAST_MODIFIED = context.getString(R.string.LAST_MODIFIED);
		ACTIVE = context.getString(R.string.ACTIVE);
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

						for(int i=0; i<restaurants.length(); i++){
							JSONObject entry = restaurants.getJSONObject(i);

							String id = entry.getString(RESTAURANT_ID);
							Boolean active = entry.getBoolean(ACTIVE);

							if(!active){
								db.removeFromRestaurantsTable(id);
							}
							else{

								String lastModified = entry.getString(LAST_MODIFIED);
								String name = entry.getString(RESTAURANT_NAME);
								String image = entry.getString(RESTAURANT_IMAGE);
								String locationId = entry.getString(RESTAURANT_LOCATION_ID);
								String locationName = entry.getString(RESTAURANT_LOCATION_NAME);
								String phone = entry.getString(RESTAURANT_PHONE);
								String website = entry.getString(RESTAURANT_WEBSITE);
								db.addToRestaurantTable(id, name, image, locationId, locationName, phone, website, lastModified);
							}
						}

						db.close();

					}
					catch (JSONException e) {
						//Error Collector
						//e.printStackTrace();
					}
				}
				handleObservers();
				return null;
			}
		};
	}


	@Override
	protected void handleObservers() {
		setChanged();
		notifyObservers(context.getString(R.string.parserObserverComplete));
	}


}
