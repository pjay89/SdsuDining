package webService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;


public class DataFetcher {

	private final String NAMESPACE = "http://www.webserviceX.NET/";
	private final String URL = "http://www.webservicex.net/stockquote.asmx";
	private final String SOAP_ACTION;
	private final String METHOD_NAME; 

	private String TAG = "SDSU DINING TEST";
	private final SoapSerializationEnvelope envelope;

	public DataFetcher(String method, String quote){
		METHOD_NAME = method;
		SOAP_ACTION = "http://www.webserviceX.NET/"+method;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		PropertyInfo quotesProperty = new PropertyInfo();
		quotesProperty.setName("symbol");
		quotesProperty.setValue(quote);
		quotesProperty.setType(String.class);
		request.addProperty(quotesProperty);

		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
	}

	//public List<QueryResult> Fetch(){
	public String Fetch(){
		HttpTransportSE httpRequest = new HttpTransportSE(URL);

		//StockQuoteHandler quoteParser = new StockQuoteHandler();;
		try
		{
			httpRequest.call(SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
			Log.i(TAG, "response "+ response.toString());
			return response.toString();
			//Xml.parse(response.toString(), quoteParser);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
		//return quoteParser.getQuotes();

	}

}
