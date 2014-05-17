package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.CouponsDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class CouponsParser extends SdsuDiningParser{

	private Context context;

	private static  String TABLE_NAME;
	private static String COUPON_ID;
	private static String COUPON_IMAGE;
	private static String COUPON_LOCATION_ID;
	private static String COUPON_RESTAURANT_ID;
	private static String COUPON_RESTAURANT_NAME;
	private static String COUPON_DEAL;
	private static String COUPON_EXPIRATION;
	
	private JSONArray coupon = null;

	private String TAG = "COUPON PARSER";

	public CouponsParser(String url, Context context){
		this.context = context;
		TABLE_NAME = context.getString(R.string.CATERING_TABLE);
		COUPON_ID = context.getString(R.string.COUPON_ID);
		COUPON_IMAGE = context.getString(R.string.COUPON_IMAGE);
		COUPON_LOCATION_ID = context.getString(R.string.COUPON_LOCATION_ID);
		COUPON_RESTAURANT_ID = context.getString(R.string.COUPON_RESTAURANT_ID);
		COUPON_RESTAURANT_NAME = context.getString(R.string.COUPON_RESTAURANT_NAME);
		COUPON_DEAL = context.getString(R.string.COUPON_DEAL);
		COUPON_EXPIRATION = context.getString(R.string.COUPON_EXPIRATION);

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
						coupon = jsonObj.getJSONArray(TABLE_NAME);
						CouponsDBHelper db = new CouponsDBHelper(context);
						
						for(int i=0; i<coupon.length(); i++){
							JSONObject entry = coupon.getJSONObject(i);
							String id = entry.getString(COUPON_ID);
							String image = entry.getString(COUPON_IMAGE);
							String locationId = entry.getString(COUPON_LOCATION_ID);
							String restaurantId = entry.getString(COUPON_RESTAURANT_ID);
							String restaurantName = entry.getString(COUPON_RESTAURANT_NAME);
							String deal = entry.getString(COUPON_DEAL);
							String expiration = entry.getString(COUPON_EXPIRATION);
							db.addToDB(id, image, locationId, restaurantId, restaurantName, deal, expiration);
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
