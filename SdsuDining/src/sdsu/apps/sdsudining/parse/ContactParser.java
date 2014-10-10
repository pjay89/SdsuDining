package sdsu.apps.sdsudining.parse;

import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.content.Context;
import android.util.Log;

public class ContactParser extends SdsuDiningParser{

	private Context context;

	private static String CONTACT_OBJECT_TAG;
	private static String DB_ID;
	private static String LAST_MODIFIED;
	private static String DB_PHONE;
	private static String DB_FAX;
	private static String DB_EMAIL;
	private static String DB_WEBSITE;
	private static String DB_ADDRESS;
	private static String DB_ABOUT;

	private JSONArray contact = null;
	
	private String TAG = "PARSER";

	public ContactParser(String url, Context context, Observer observer){
		this.context = context;
		
		addObserver(observer);
		
		CONTACT_OBJECT_TAG = context.getString(R.string.CONTACT_OBJECT_TAG);
		DB_ID = context.getString(R.string.DB_ID);
		LAST_MODIFIED = context.getString(R.string.LAST_MODIFIED);
		DB_PHONE = context.getString(R.string.DB_PHONE);
		DB_FAX = context.getString(R.string.DB_FAX);
		DB_EMAIL = context.getString(R.string.DB_EMAIL);
		DB_WEBSITE = context.getString(R.string.DB_WEBSITE);
		DB_ADDRESS = context.getString(R.string.DB_ADDRESS);
		DB_ABOUT = context.getString(R.string.DB_ABOUT);

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
						
						for(int i=0; i<contact.length(); i++){
							JSONObject entry = contact.getJSONObject(i);
							String id = entry.getString(DB_ID);
							String lastModified = entry.getString(LAST_MODIFIED);
							String phone = entry.getString(DB_PHONE);
							String fax = entry.getString(DB_FAX);
							String email = entry.getString(DB_EMAIL);
							String website = entry.getString(DB_WEBSITE);
							String address = entry.getString(DB_ADDRESS);
							String about = entry.getString(DB_ABOUT);
							db.addToContactTable(id, phone, fax, email, website, address, about, lastModified);
						}
						
						db.close();
						
					}
					catch (JSONException e) {
						Log.i(TAG, "ERRORs: "+e.getMessage());
					}
				}
				handleObservers();
				return null;
			}
		};
	}



	@Override
	protected void handleObservers() {
		Log.i(TAG, "handle called in CONTACT");
		setChanged();
		notifyObservers(context.getString(R.string.parserObserverComplete));
	}

}
