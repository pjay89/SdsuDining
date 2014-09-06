package sdsu.apps.sdsudining.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class ContactParser extends SdsuDiningParser{

	private Context context;

	private static String CONTACT_OBJECT_TAG;
	private static String DB_PHONE;
	private static String DB_FAX;
	private static String DB_EMAIL;
	private static String DB_WEBSITE;
	private static String DB_ADDRESS;
	private static String DB_ABOUT;

	private JSONArray contact = null;
	private ProgressDialog progressDialog;
	
	private String TAG = "PARSER";

	public ContactParser(String url, Context context, ProgressDialog progressDialog){
		this.context = context;
		this.progressDialog = progressDialog;
		//this.progressDialog.show();
		
		CONTACT_OBJECT_TAG = context.getString(R.string.CONTACT_OBJECT_TAG);
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
						db.deleteContactTable();
						
						for(int i=0; i<contact.length(); i++){
							JSONObject entry = contact.getJSONObject(i);
							String phone = entry.getString(DB_PHONE);
							String fax = entry.getString(DB_FAX);
							String email = entry.getString(DB_EMAIL);
							String website = entry.getString(DB_WEBSITE);
							String address = entry.getString(DB_ADDRESS);
							String about = entry.getString(DB_ABOUT);
							db.addToContactTable(phone, fax, email, website, address, about);
						}
						
					}
					catch (JSONException e) {
						Log.i(TAG, "ERRORs: "+e.getMessage());
					}
				}
				return null;
			}

			@Override
			void onPostExecute() {
				progressDialog.dismiss();
			}
		};
	}

}
