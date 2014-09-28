package sdsu.apps.sdsudining.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.objects.BusyWait;
import android.content.Context;
import android.util.Log;

public class CouponsParser extends SdsuDiningParser{

	private Context context;

	private static String COUPON_OBJECT_TAG;
	private static String COUPON_ID;
	private static String COUPON_IMAGE;
	private static String COUPON_EXPIRATION;
	
	private JSONArray coupon = null;
	private BusyWait busyWait;
	
	private String TAG = "PARSER";

	public CouponsParser(String url, Context context, BusyWait busyWait){
		this.context = context;
		this.busyWait = busyWait;
		this.busyWait.show();
		
		COUPON_OBJECT_TAG = context.getString(R.string.COUPON_OBJECT_TAG);
		COUPON_ID = context.getString(R.string.COUPON_ID);
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
						db.deleteCouponTable();
						
						for(int i=0; i<coupon.length(); i++){
							JSONObject entry = coupon.getJSONObject(i);
							String id = entry.getString(COUPON_ID);
							String image = entry.getString(COUPON_IMAGE);
							String expiration = entry.getString(COUPON_EXPIRATION);
							db.addToCouponTable(id, image, expiration);
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
