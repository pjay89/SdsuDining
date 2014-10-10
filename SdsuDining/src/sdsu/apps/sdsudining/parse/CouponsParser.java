package sdsu.apps.sdsudining.parse;

import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.content.Context;
import android.util.Log;

public class CouponsParser extends SdsuDiningParser{

	private Context context;

	private static String COUPON_OBJECT_TAG;
	private static String COUPON_ID;
	private static String LAST_MODIFIED;
	private static String ACTIVE;
	private static String COUPON_IMAGE;
	private static String COUPON_EXPIRATION;
	
	private JSONArray coupon = null;
	
	private String TAG = "PARSER";

	public CouponsParser(String url, Context context, Observer observer){
		this.context = context;

		addObserver(observer);
		
		COUPON_OBJECT_TAG = context.getString(R.string.COUPON_OBJECT_TAG);
		COUPON_ID = context.getString(R.string.COUPON_ID);
		LAST_MODIFIED = context.getString(R.string.LAST_MODIFIED);
		ACTIVE = context.getString(R.string.ACTIVE);
		COUPON_IMAGE = context.getString(R.string.COUPON_IMAGE);
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
						coupon = jsonObj.getJSONArray(COUPON_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						
						for(int i=0; i<coupon.length(); i++){
							JSONObject entry = coupon.getJSONObject(i);
							
							String id = entry.getString(COUPON_ID);
							Boolean active = entry.getBoolean(ACTIVE);
							
							if(!active){
								db.removeFromCouponTable(id);
							}
							else{
								String lastModified = entry.getString(LAST_MODIFIED);
								String image = entry.getString(COUPON_IMAGE);
								String expiration = entry.getString(COUPON_EXPIRATION);
								db.addToCouponTable(id, image, expiration, lastModified);
							}
						}
						
						db.close();		
					}
					catch (JSONException e) {
						Log.i(TAG, "ERROR: "+e.getMessage());
					}
				}
				handleObservers();
				return null;
			}
		};
	}

	@Override
	protected void handleObservers() {
		Log.i(TAG, "handle called in COUPONS");
		setChanged();
		notifyObservers(context.getString(R.string.parserObserverComplete));
	}

}
