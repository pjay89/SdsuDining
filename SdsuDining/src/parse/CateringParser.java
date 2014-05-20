package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import database.SdsuDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class CateringParser extends SdsuDiningParser{
	private Context context;

	private static  String CATERING_OBJECT_TAG;
	private static String CATERING_PHONE;
	private static String CATERING_FAX;
	private static String CATERING_EMAIL;
	private static String CATERING_ADDRESS;
	private static String CATERING_SNIPPET;
	private static String CATERING_GUIDELINES;
	private static String CATERING_SERVICE_LEVEL;

	private JSONArray catering = null;

	private String TAG = "CATERING PARSER";

	public CateringParser(String url, Context context){
		this.context = context;
		CATERING_OBJECT_TAG = context.getString(R.string.CATERING_OBJECT_TAG);
		CATERING_PHONE = context.getString(R.string.CATERING_PHONE);
		CATERING_FAX = context.getString(R.string.CATERING_FAX);
		CATERING_EMAIL = context.getString(R.string.CATERING_EMAIL);
		CATERING_ADDRESS = context.getString(R.string.CATERING_ADDRESS);
		CATERING_SNIPPET = context.getString(R.string.CATERING_SNIPPET);
		CATERING_GUIDELINES = context.getString(R.string.CATERING_GUIDELINES);
		CATERING_SERVICE_LEVEL = context.getString(R.string.CATERING_SERVICE_LEVEL);

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
						catering = jsonObj.getJSONArray(CATERING_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						db.deleteCateringTable();
						
						for(int i=0; i<catering.length(); i++){
							JSONObject entry = catering.getJSONObject(i);
							String phone = entry.getString(CATERING_PHONE);
							String fax = entry.getString(CATERING_FAX);
							String email = entry.getString(CATERING_EMAIL);
							String address = entry.getString(CATERING_ADDRESS);
							String snippet = entry.getString(CATERING_SNIPPET);
							String guidelines = entry.getString(CATERING_GUIDELINES);
							String level = entry.getString(CATERING_SERVICE_LEVEL);
							db.addToCateringTable(phone, fax, email, address, snippet, guidelines, level);
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
