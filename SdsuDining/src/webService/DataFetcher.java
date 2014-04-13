package webService;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class DataFetcher implements Runnable{
	
	private final HttpClient Client = new DefaultHttpClient();
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
			textView.setText(response);
		} catch (Throwable t) {
			// just end the background thread
			Log.i(TAG, "Thread  exception " + t);
		}
	}
	

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
