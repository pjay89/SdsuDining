package sdsu.apps.sdsudining.gcm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sdsu.apps.sdsudining.R;

import android.content.Context;
import android.util.Log;

public class ExternalServerConnector {
	private static final String TAG = "SDSU_GCM";

	public void sendRegIdToExternalServer(final Context context, final String regId){

		try {
			URL serverUrl = new URL(Configurations.APP_SERVER_URL);


			String postMessage = context.getString(R.string.GCM_REG_ID) + "=" + regId;
			byte[] bytes = postMessage.getBytes();

			HttpURLConnection httpConnection = (HttpURLConnection) serverUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setUseCaches(false);
			httpConnection.setFixedLengthStreamingMode(bytes.length);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStream out = httpConnection.getOutputStream();
			out.write(bytes);
			out.close();
			
			int status = httpConnection.getResponseCode();
			if(status == 200){
				Log.i(TAG, "Shared regId with ExternalServer");
			}
			else{
				Log.e(TAG, "Response code: " + String.valueOf(status));
				//Error Collector
				//e.printStackTrace();
			}
			

		} 
		catch (MalformedURLException e) {
			Log.e(TAG, "malformed: " + e.getMessage());
			//Error Collector
			//e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, "esc io excpetion: " + e.getMessage());
			//Error Collector
			//e.printStackTrace();
		};
	}

}
