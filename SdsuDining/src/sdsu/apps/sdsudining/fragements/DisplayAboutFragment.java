package sdsu.apps.sdsudining.fragements;

import sdsu.apps.sdsudining.R;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayAboutFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_about, container, false);
		
		int orientation = getResources().getConfiguration().orientation;
		if(orientation == Configuration.ORIENTATION_PORTRAIT){
			rootView.setBackgroundResource(R.drawable.aboutus_bgi);
		}
		else{
			rootView.setBackgroundResource(R.drawable.aboutus_bgi_land);
		}
		
		TextView aboutText = (TextView) rootView.findViewById(R.id.aboutText);
		aboutText.setText(getArguments().getString(getActivity().getResources().getString(R.string.about)));
		return rootView;
	}

}
