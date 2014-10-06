package sdsu.apps.sdsudining.parse;

import sdsu.apps.sdsudining.webService.DataFetcher;
import android.os.AsyncTask;

public abstract class SdsuDiningParser extends java.util.Observable{
	
	protected class AsyncWebServiceCall extends AsyncTask<String, Void, Void> {
		private String jsonString = "";
		
		@Override
		protected void onPreExecute() {}

		protected Void doInBackground(String... params) {
			DataFetcher df = new DataFetcher(params[0]);
			jsonString = df.fetch();
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {}

		@Override
		protected void onPostExecute(Void result) {
			AsyncJsonStringParser asyncParse = getAsyncJsonStringParser();
			asyncParse.execute(jsonString);
		}
	}
	
	abstract class AsyncJsonStringParser extends AsyncTask<String, Void, Void>{};

	protected abstract AsyncJsonStringParser getAsyncJsonStringParser();
	protected abstract void handleObservers();
}
