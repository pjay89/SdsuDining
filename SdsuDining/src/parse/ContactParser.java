package parse;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import webService.DataFetcher;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class ContactParser {

	private String jsonString = "";
	private String TAG = "PARSER";
	
	// Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";

	// contacts JSONArray
	JSONArray contacts = null;
	
	public ContactParser(String url){
		AsyncCallWS ws = new AsyncCallWS();
		ws.execute(url);
	}

	public void parse(){
		Log.i(TAG, "PARSE called");
		AsyncCallParse asyncParse = new AsyncCallParse();
		asyncParse.execute();
	}


	private class AsyncCallParse extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "PARSE onPreExec");
		}

		@Override
		protected Void doInBackground(Void... params) {
			if(jsonString != null){
				try{
					JSONObject jsonObj = new JSONObject(jsonString);
					contacts = jsonObj.getJSONArray(TAG_CONTACTS);

					for(int i=0; i<contacts.length(); i++){
						JSONObject contact = contacts.getJSONObject(i);
						String id = contact.getString(TAG_ID);
						String name = contact.getString(TAG_NAME);

						// Phone node is JSON Object
						JSONObject phone = contact.getJSONObject(TAG_PHONE);
						String mobile = phone.getString(TAG_PHONE_MOBILE);
						String home = phone.getString(TAG_PHONE_HOME);

						HashMap<String, String> c = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						c.put(TAG_ID, id);
						c.put(TAG_NAME, name);
						c.put(TAG_PHONE_MOBILE, mobile);
						c.put(TAG_PHONE_HOME, home);

						// adding contact to contact list
                        contactList.add(c);
                        
					}
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
			for(int j=0; j<contactList.size(); j++){
				HashMap<String, String> c = contactList.get(j);
				Log.i(TAG, c.get(TAG_NAME));
			}
			
		}



	}

	private class AsyncCallWS extends AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExec");
		}

		protected Void doInBackground(String... params) {
			Log.i(TAG, "doInBackground");
			DataFetcher df = new DataFetcher(params[0]);
			jsonString = df.fetch();
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i(TAG,"onPostExec");
			Log.i(TAG, "postExec Res: ");
		}
	}

	/*private class AsyncCallWS extends AsyncTask<String, Void, Void> {

		 @Override
	        protected void onPreExecute() {
	            Log.i(TAG, "onPreExecute");
	        }

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }

		@Override
		protected void onPostExecute(Void result) {
			Log.i(TAG, "onPostExecute");
		}
	}*/
}
