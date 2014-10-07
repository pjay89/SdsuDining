package sdsu.apps.sdsudining.gcm;

import java.io.IOException;

import sdsu.apps.sdsudining.database.SdsuDBHelper;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class RegIdRegistrar {
	private Context context;
	
	private static final String TAG = "SDSU_GCM";
	
	public RegIdRegistrar(Context context){
		this.context = context;
	}
	
	public void register(){
		GCMServerRegistrar gcmServerRegistrar = new GCMServerRegistrar();
		gcmServerRegistrar.execute();
	}

	
	protected class GCMServerRegistrar extends AsyncTask<Void, Void, String>{
		
		@Override
		protected String doInBackground(Void... params) {
			String regId = "";
			GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
			try {
				regId = gcm.register(Configurations.GOOGLE_PROJECT_ID);
			} catch (IOException e) {
				Log.e(TAG, "gcm serv registrar: " + e.getMessage());
            	//Error Collector
    			//e.printStackTrace();
			}
			Log.i(TAG, "GCM SRVR registered");
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
			
			ExternalServerConnector connector = new ExternalServerConnector();
			connector.sendRegIdToExternalServer(context, regId);
			Log.i(TAG, "APPSRVR registered");
			
			// Save regId to local device DB
			SdsuDBHelper db = new SdsuDBHelper(context);
			db.storeGCMRegId(regId);
			db.close();
			Log.i(TAG, "LOCAL DB registered");
			
			
			return null;
		}
	}

}
