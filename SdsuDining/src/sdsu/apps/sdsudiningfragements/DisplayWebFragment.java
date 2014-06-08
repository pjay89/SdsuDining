package sdsu.apps.sdsudiningfragements;

import sdsu.apps.sdsudining.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class DisplayWebFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_web, container, false);

		WebView fragmentWebView = (WebView) rootView.findViewById(R.id.fragementWebView);
		fragmentWebView.getSettings().setLoadWithOverviewMode(true);
		//fragmentWebView.getSettings().setUseWideViewPort(true);
		//fragmentWebView.getSettings().setJavaScriptEnabled(true);
		fragmentWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		//fragmentWebView.loadUrl(getArguments().getString(getActivity().getResources().getString(R.string.menu)));
		fragmentWebView.loadUrl("http://eatatsdsu.com");
		return rootView;
	}
}
