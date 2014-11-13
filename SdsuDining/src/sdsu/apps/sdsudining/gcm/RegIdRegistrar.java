package sdsu.apps.sdsudining.gcm;

import java.io.IOException;

import sdsu.apps.sdsudining.database.SdsuDBHelper;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Registers device with GCM Servers, gets the regId, sends regId to 3rd party application server and stores regId to local DB as well.
 * Storing regId to local DB prevents repetitive requests from app for regId from GCM Servers
 * 
 * @author Priya Jayaprakash
 *
 */

public class RegIdRegistrar {
	private Context context;
	
	
	public RegIdRegistrar(Context context){
		this.context = context;
	}
	
	public void register(){
		GCMServerRegistrar gcmServerRegistrar = new GCMServerRegistrar();
		gcmServerRegistrar.execute();
	}

	
	protected class GCMServerRegistrar extends AsyncTask<Void, Void, String>{
		// Register the device with GCM servers
		@Override
		protected String doInBackground(Void... params) {
			String regId = "";
			GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
			try {
				regId = gcm.register(Configurations.GOOGLE_PROJECT_ID);
			} catch (IOException e) {
            	//Error Collector
    			//e.printStackTrace();
			}
			return regId;
		}
		
		@Override
		protected void onPostExecute(String regId) {
			ExternalServerRegistrar externalServerRegistrar = new ExternalServerRegistrar();
			externalServerRegistrar.execute(regId);
		}
	}
	
	protected class ExternalServerRegistrar extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			String regId = params[0];
			
			// Send regId to 3rd party application server
			ExternalServerConnector connector = new ExternalServerConnector();
			connector.sendRegIdToExternalServer(context, regId);
			
			// Save regId to local device DB
			SdsuDBHelper db = new SdsuDBHelper(context);
			db.storeGCMRegId(regId);
			db.close();
			
			return null;
		}
	}

}
