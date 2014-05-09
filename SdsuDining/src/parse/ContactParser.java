package parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.DatabaseHelper;


import webService.DataFetcher;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ContactParser extends SdsuDiningParser{

	//private String jsonString = "";
	private Context context;
	private String TAG = "PARSER";
	
	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";

	// contacts JSONArray
	JSONArray contacts = null;
	
	public ContactParser(String url, Context context){
		this.context = context;
		AsyncWebServiceCall ws = new AsyncWebServiceCall();
		ws.execute(url);
		//jsonString = ws.handle();
		
	}

	/*public void parse(){
		Log.i(TAG, "PARSE called");
		AsyncCallParse asyncParse = new AsyncCallParse();
		asyncParse.execute();
	}*/

	@Override
	protected AsyncJsonStringParser getAsyncJsonStringParser() {
		return new AsyncJsonStringParser() {
			
			@Override
			protected Void doInBackground(String... params) {
				String str = params[0];
				Log.i(TAG, "PARSE doInBack "+str);
				if(str != null){
					try{
						JSONObject jsonObj = new JSONObject(str);
						contacts = jsonObj.getJSONArray(TAG_CONTACTS);
						DatabaseHelper db = new DatabaseHelper(context);
						db.deleteTable();
						
						for(int i=0; i<contacts.length(); i++){
							JSONObject contact = contacts.getJSONObject(i);
							String id = contact.getString(TAG_ID);
							String name = contact.getString(TAG_NAME);

							// Phone node is JSON Object
							JSONObject phone = contact.getJSONObject(TAG_PHONE);
							String mobile = phone.getString(TAG_PHONE_MOBILE);
							String home = phone.getString(TAG_PHONE_HOME);

	                        
	                        db.addToDB(id, name, mobile, home);
						}
						db.close();
					} 
					catch(JSONException e){
						Log.i(TAG, "ERROR: "+e.getMessage());
					}
				}
				else{
					Log.i(TAG, "nil");
				}
				return null;
			}
		};
	}

	

/*	public class AsyncCallParse extends AsyncTask<String, Void, Void>{
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "PARSE onPreExec");
		}

		@Override
		protected Void doInBackground(String... params) {
			String str = params[0];
			Log.i(TAG, "PARSE doInBack "+str);
			if(str != null){
				try{
					JSONObject jsonObj = new JSONObject(str);
					contacts = jsonObj.getJSONArray(TAG_CONTACTS);
					DatabaseHelper db = new DatabaseHelper(context);
					db.deleteTable();
					
					for(int i=0; i<contacts.length(); i++){
						JSONObject contact = contacts.getJSONObject(i);
						String id = contact.getString(TAG_ID);
						String name = contact.getString(TAG_NAME);

						// Phone node is JSON Object
						JSONObject phone = contact.getJSONObject(TAG_PHONE);
						String mobile = phone.getString(TAG_PHONE_MOBILE);
						String home = phone.getString(TAG_PHONE_HOME);

                        
                        db.addToDB(id, name, mobile, home);
					}
					db.close();
				} 
				catch(JSONException e){
					Log.i(TAG, "ERROR: "+e.getMessage());
				}
			}
			else{
				Log.i(TAG, "nil");
			}
			return null;
		}


		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "PARSE onProgressUpdate");
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Log.i(TAG,"PARSE onPostExec");
		}
	}*/

	/*private class AsyncCallWS extends AsyncTask<String, Void, Void> {
		private String myResult;
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExec");
		}

		protected Void doInBackground(String... params) {
			Log.i(TAG, "doInBackground");
			DataFetcher df = new DataFetcher(params[0]);
			//jsonString = df.fetch();
			myResult = df.fetch();
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

		
		protected void onPostExecute(Void result) {
			Log.i(TAG,"onPostExec");
			//handle();
			
			Log.i(TAG, "PARSE called");
			AsyncCallParse asyncParse = new AsyncCallParse();
			asyncParse.execute(myResult);
		}
		
		protected String handle(){
			Log.i(TAG, "handle Res: "+myResult);
			return myResult;
		}
	}*/
}
