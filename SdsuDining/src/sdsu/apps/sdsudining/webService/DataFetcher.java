package sdsu.apps.sdsudining.webService;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * DataFetcher class: Used to fetch data from the respective URLs using HttpGet
 * @author Priya Jayaprakash
 *
 */

public class DataFetcher {
	private final HttpClient Client = new DefaultHttpClient();
	private String url; 
	
	public DataFetcher(String url){
		this.url = url;
	}

	public String fetch(){
		String response = "";
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		
		try {
			response = Client.execute(httpget, responseHandler);
			return response;
		} catch (ClientProtocolException e) {
			//Error Collector
			//e.printStackTrace();
		} catch (IOException e) {
			//Error Collector
			//e.printStackTrace();
		}
		
		return null;
		
		
	}
}
