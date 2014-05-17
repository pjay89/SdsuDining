package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import database.ContactDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class ContactParser extends SdsuDiningParser{

	private Context context;

	private static  String TABLE_NAME;
	private static String CONTACT_PHONE;
	private static String CONTACT_FAX;
	private static String CONTACT_EMAIL;
	private static String CONTACT_ADDRS;

	private JSONArray contact = null;

	private String TAG = "CONTACT PARSER";

	public ContactParser(String url, Context context){
		this.context = context;
		TABLE_NAME = context.getString(R.string.CATERING_TABLE);
		CONTACT_PHONE = context.getString(R.string.CONTACT_PHONE);
		CONTACT_FAX = context.getString(R.string.CONTACT_FAX);
		CONTACT_EMAIL = context.getString(R.string.CONTACT_EMAIL);
		CONTACT_ADDRS = context.getString(R.string.CONTACT_ADDRS);

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
						contact = jsonObj.getJSONArray(TABLE_NAME);
						ContactDBHelper db = new ContactDBHelper(context);
						
						for(int i=0; i<contact.length(); i++){
							JSONObject entry = contact.getJSONObject(i);
							String phone = entry.getString(CONTACT_PHONE);
							String fax = entry.getString(CONTACT_FAX);
							String email = entry.getString(CONTACT_EMAIL);
							String addrs = entry.getString(CONTACT_ADDRS);
							db.addToDB(phone, fax, email, addrs);
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
