package sdsu.apps.sdsudining.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class SweetParser extends SdsuDiningParser{
	private Context context;
	
	private static String SWEET_OBJECT_TAG;
	private static String DB_PHONE;
	private static String DB_FAX;
	private static String DB_EMAIL;
	private static String DB_WEBSITE;
	private static String DB_ADDRESS;
	private static String DB_ABOUT;
	
	private JSONArray sweet = null;
	private ProgressDialog progressDialog;
	
	private String TAG = "PARSER";
	
	public SweetParser(String url, Context context, ProgressDialog progressDialog){
		this.context = context;
		this.progressDialog = progressDialog;
		//this.progressDialog.show();
		
		SWEET_OBJECT_TAG = context.getString(R.string.SWEET_OBJECT_TAG);
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
						sweet = jsonObj.getJSONArray(SWEET_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						db.deleteSweetTable();
						
						for(int i=0; i<sweet.length(); i++){
							JSONObject entry = sweet.getJSONObject(i);
							String phone = entry.getString(DB_PHONE);
							String fax = entry.getString(DB_FAX);
							String email = entry.getString(DB_EMAIL);
							String website = entry.getString(DB_WEBSITE);
							String address = entry.getString(DB_ADDRESS);
							String about = entry.getString(DB_ABOUT);
							db.addToSweetTable(phone, fax, email, website, address, about);
						}
					}
					catch (JSONException e) {
						Log.i(TAG, "ERROR: "+e.getMessage());
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
