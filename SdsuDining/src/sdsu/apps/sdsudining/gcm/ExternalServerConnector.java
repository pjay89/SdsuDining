package sdsu.apps.sdsudining.gcm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sdsu.apps.sdsudining.R;

import android.content.Context;

/**
 * Connects to the external 3rd party application server and sends the regID. 
 * The regID will be stored on the 3rd party server and used to send future 
 * push notification messages 
 * 
 * @author Priya Jayaprakash
 *
 */

public class ExternalServerConnector {

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
			if(status != 200){
				//Error Collector
				//e.printStackTrace();
			}
		} 
		catch (MalformedURLException e) {
			//Error Collector
			//e.printStackTrace();
		} catch (IOException e) {
			//Error Collector
			//e.printStackTrace();
		};
	}

}
