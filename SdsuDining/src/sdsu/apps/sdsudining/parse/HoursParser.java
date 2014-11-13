package sdsu.apps.sdsudining.parse;

import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.content.Context;

/**
 * Concrete parser for Hours category. Fetches, parses, and stores data to local db.
 * Upon completion, notify observer
 * 
 * @author Priya Jayaprakash
 *
 */

public class HoursParser extends SdsuDiningParser {
	private Context context;

	private static String HOURS_OBJECT_TAG;
	private static String DB_ID;
	private static String LAST_MODIFIED;
	private static String ACTIVE;
	private static String HOURS_RESTAURANT_ID;
	private static String HOURS_DAY;
	private static String HOURS_OPEN;
	private static String HOURS_CLOSE;

	private JSONArray hours = null;


	public HoursParser(String url, Context context, Observer observer){
		this.context = context;

		this.addObserver(observer);

		HOURS_OBJECT_TAG = context.getString(R.string.HOURS_OBJECT_TAG);
		DB_ID = context.getString(R.string.DB_ID);
		LAST_MODIFIED = context.getString(R.string.LAST_MODIFIED);
		ACTIVE = context.getString(R.string.ACTIVE);
		HOURS_RESTAURANT_ID = context.getString(R.string.HOURS_RESTAURANT_ID);
		HOURS_DAY = context.getString(R.string.HOURS_DAY);
		HOURS_OPEN = context.getString(R.string.HOURS_OPEN);
		HOURS_CLOSE = context.getString(R.string.HOURS_CLOSE);

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
						hours = jsonObj.getJSONArray(HOURS_OBJECT_TAG);
						SdsuDBHelper db = new SdsuDBHelper(context);

						for(int i=0; i<hours.length(); i++){
							JSONObject entry = hours.getJSONObject(i);
							String id = entry.getString(DB_ID);
							Boolean active = entry.getBoolean(ACTIVE);

							if(!active){
								db.removeFromHoursTable(id);
							}
							else{
								String lastModified = entry.getString(LAST_MODIFIED);
								String restaurantId = entry.getString(HOURS_RESTAURANT_ID);
								String day = entry.getString(HOURS_DAY);
								String open = entry.getString(HOURS_OPEN);
								String close = entry.getString(HOURS_CLOSE);							
								db.addToHoursTable(id, lastModified, restaurantId, day, open, close);
							}
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
