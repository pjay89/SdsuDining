package sdsu.apps.sdsudining.parse;

import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.content.Context;

/**
 * Concrete parser for Catering category. Fetches, parses, and stores data to local db.
 * Upon completion, notify observer
 * 
 * @author Priya Jayaprakash
 *
 */

public class CateringParser extends SdsuDiningParser{
	private Context context;

	private static  String CATERING_OBJECT_TAG;
	private static String DB_ID;
	private static String LAST_MODIFIED;
	private static String DB_PHONE;
	private static String DB_FAX;
	private static String DB_EMAIL;
	private static String DB_WEBSITE;
	private static String DB_ADDRESS;
	private static String DB_ABOUT;
	
	private JSONArray catering = null;


	public CateringParser(String url, Context context, Observer observer){
		this.context = context;

		addObserver(observer);
		
		CATERING_OBJECT_TAG = context.getString(R.string.CATERING_OBJECT_TAG);
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
						catering = jsonObj.getJSONArray(CATERING_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);
						
						for(int i=0; i<catering.length(); i++){
							JSONObject entry = catering.getJSONObject(i);
							String id = entry.getString(DB_ID);
							String lastModified = entry.getString(LAST_MODIFIED);
							String phone = entry.getString(DB_PHONE);
							String fax = entry.getString(DB_FAX);
							String email = entry.getString(DB_EMAIL);
							String website = entry.getString(DB_WEBSITE);
							String address = entry.getString(DB_ADDRESS);
							String about = entry.getString(DB_ABOUT);
						
							db.addToCateringTable(id, phone, fax, email, website, address, about, lastModified);
						}
						
						db.close();
						
					}
					catch (JSONException e) {
						//Error Collector
						//e.printStackTrace();
					}
				}
				handleObservers();
				return null;
			}
		};
	}

	@Override
	protected void handleObservers() {
		setChanged();
		notifyObservers(context.getString(R.string.parserObserverComplete));
	}

}
