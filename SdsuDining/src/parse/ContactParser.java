package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.SdsuDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class ContactParser extends SdsuDiningParser{

	private Context context;

	private static String CONTACT_OBJECT_TAG;
	private static String CONTACT_PHONE;
	private static String CONTACT_FAX;
	private static String CONTACT_EMAIL;
	private static String CONTACT_ADDRESS;

	private JSONArray contact = null;

	private String TAG = "PARSER";

	public ContactParser(String url, Context context){
		this.context = context;
		CONTACT_OBJECT_TAG = context.getString(R.string.CONTACT_OBJECT_TAG);
		CONTACT_PHONE = context.getString(R.string.CONTACT_PHONE);
		CONTACT_FAX = context.getString(R.string.CONTACT_FAX);
		CONTACT_EMAIL = context.getString(R.string.CONTACT_EMAIL);
		CONTACT_ADDRESS = context.getString(R.string.CONTACT_ADDRESS);

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
						contact = jsonObj.getJSONArray(CONTACT_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						db.deleteContactTable();
						
						for(int i=0; i<contact.length(); i++){
							JSONObject entry = contact.getJSONObject(i);
							String phone = entry.getString(CONTACT_PHONE);
							String fax = entry.getString(CONTACT_FAX);
							String email = entry.getString(CONTACT_EMAIL);
							String address = entry.getString(CONTACT_ADDRESS);
							db.addToContactTable(phone, fax, email, address);
						}
						
					}
					catch (JSONException e) {
						Log.i(TAG, "ERRORs: "+e.getMessage());
					}
				}
				return null;
			}
		};
	}

}
