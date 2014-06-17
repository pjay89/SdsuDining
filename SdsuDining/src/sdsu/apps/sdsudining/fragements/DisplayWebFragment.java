package sdsu.apps.sdsudining.fragements;

import sdsu.apps.sdsudining.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class DisplayWebFragment extends Fragment {
	private String TAG = "SECTIONS";	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_web, container, false);

		WebView fragmentWebView = (WebView) rootView.findViewById(R.id.fragementWebView);
		fragmentWebView.getSettings().setLoadWithOverviewMode(true);
		fragmentWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		fragmentWebView.loadUrl("http://sdsucatering.com/Gallery.aspx");
		
		/*fragmentWebView.getSettings().setJavaScriptEnabled(true);
		Log.i(TAG, getArguments().getString(getActivity().getResources().getString(R.string.menu)));
		fragmentWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + getArguments().getString(getActivity().getResources().getString(R.string.menu)));
		*/
		return rootView;
	}
}
