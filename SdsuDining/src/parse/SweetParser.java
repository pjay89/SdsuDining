package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import database.SweetDBHelper;
import sdsu.apps.sdsudining.R;
import android.content.Context;
import android.util.Log;

public class SweetParser extends SdsuDiningParser{
	private Context context;
	
	private static String TABLE_NAME;
	private static String SWEET_PHONE;
	private static String SWEET_FAX;
	private static String SWEET_EMAIL;
	private static String SWEET_WEBSITE;
	private static String SWEET_MENU;
	private static String SWEET_ORDER_FORM;
	
	private JSONArray sweet = null;

	private String TAG = "SWEET	 PARSER";
	
	public SweetParser(String url, Context context){
		this.context = context;
		TABLE_NAME = context.getString(R.string.SWEET_TABLE);
		SWEET_PHONE = context.getString(R.string.SWEET_PHONE);
		SWEET_FAX = context.getString(R.string.SWEET_FAX);
		SWEET_EMAIL = context.getString(R.string.SWEET_EMAIL);
		SWEET_WEBSITE = context.getString(R.string.SWEET_WEBSITE);
		SWEET_MENU = context.getString(R.string.SWEET_MENU);
		SWEET_ORDER_FORM = context.getString(R.string.SWEET_ORDER_FORM);
		
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
						sweet = jsonObj.getJSONArray(TABLE_NAME);
						SweetDBHelper db = new SweetDBHelper(context);
						
						for(int i=0; i<sweet.length(); i++){
							JSONObject entry = sweet.getJSONObject(i);
							String phone = entry.getString(SWEET_PHONE);
							String fax = entry.getString(SWEET_FAX);
							String email = entry.getString(SWEET_EMAIL);
							String website = entry.getString(SWEET_WEBSITE);
							String menu = entry.getString(SWEET_MENU);
							String orderForm = entry.getString(SWEET_ORDER_FORM);
							
							db.addToDB(phone, fax, email, website, menu, orderForm);
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
