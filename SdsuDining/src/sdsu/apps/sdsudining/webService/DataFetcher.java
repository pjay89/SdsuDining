package sdsu.apps.sdsudining.webService;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;


public class DataFetcher {
	private final HttpClient Client = new DefaultHttpClient();
	private String url; 
	private String TAG = "DATAFETCHER";	
	
	public DataFetcher(String url){
		this.url = url;
	}

	public String fetch(){
		String response = "";
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		
		try {
			response = Client.execute(httpget, responseHandler);
			Log.i(TAG, response);
			return response;
		} catch (ClientProtocolException e) {
			Log.e(TAG, "ERROR1: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, "ERROR2: "+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
		
		
	}
}
