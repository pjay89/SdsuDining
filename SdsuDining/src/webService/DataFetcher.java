package webService;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


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
			// TODO Auto-generated catch block
			Log.i(TAG, "ERROR: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
/*	private final HttpClient Client = new DefaultHttpClient();
	private String url; 
	private String TAG = "DATAFETCHER";
	private TextView view;
	
	
	public DataFetcher(String url, TextView view){
		this.url = url;
		this.view = view;
	}
	
	public AsyncCallWS query(){
		return new AsyncCallWS();
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
			// TODO Auto-generated catch block
			Log.i(TAG, "ERROR: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public class AsyncCallWS extends AsyncTask<String, Void, Void> {
		private String res;

		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExec");
		}


		@Override
		protected Void doInBackground(String... params) {
			Log.i(TAG, "doInBackground");
			
			res = fetch();
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

		@Override
		protected void onPostExecute(Void result) {
			//view.setText(res);
			
			Log.i(TAG,"onPostExec");
			Log.i(TAG, "postExec Res: "+ res);
		}   
	}*/
	
	
/*	private final HttpClient Client = new DefaultHttpClient();
	private String url; 
	private TextView textView;
	private String TAG = "DATAFETCHER";
	
	public DataFetcher(String url, TextView textView){
		this.url = url;
		this.textView = textView;
	}
	
	@Override
	public void run() {
		try {

			String response = "";
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = Client.execute(httpget, responseHandler);
			Log.i(TAG, response);
			Log.i(TAG, textView.toString());
			textView.invalidate();
			textView.setText(response);
			
		} catch (Throwable t) {
			// just end the background thread
			Log.i(TAG, "Thread  exception " + t);
		}
	}*/
	

/*	private final String NAMESPACE = "http://www.webserviceX.NET/";
	private final String URL = "http://www.webservicex.net/stockquote.asmx";
	private final String SOAP_ACTION; //= "http://www.webserviceX.NET/GetQuote";
	private final String METHOD_NAME;// = "GetQuote"; 

	private String TAG = "SDSU DINING TEST";
	private final SoapSerializationEnvelope envelope;

	public DataFetcher(String method, String searchKey, String searchValue){
		SOAP_ACTION = "http://www.webserviceX.NET/"+method;
		METHOD_NAME = method;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		PropertyInfo quotesProperty = new PropertyInfo();
		quotesProperty.setName(searchKey);
		quotesProperty.setValue(searchValue);
		quotesProperty.setType(String.class);
		request.addProperty(quotesProperty);

		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
	}

	//public List<QueryResult> Fetch(){
	public String fetch(){		
		HttpTransportSE httpRequest = new HttpTransportSE(URL);

		//StockQuoteHandler quoteParser = new StockQuoteHandler();;
		try
		{
			httpRequest.call(SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
			return response.toString();
			//Xml.parse(response.toString(), quoteParser);
		}
		catch(Exception e)
		{
			Log.i(TAG, "ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
		//return quoteParser.getQuotes();

	}*/
	
	

}
