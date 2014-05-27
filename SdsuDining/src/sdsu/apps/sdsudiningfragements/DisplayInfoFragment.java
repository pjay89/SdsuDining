package sdsu.apps.sdsudiningfragements;

import sdsu.apps.sdsudining.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_info, container, false);
		
		TextView infoText = (TextView) rootView.findViewById(R.id.infoText);
		infoText.setText(getArguments().getString("info"));
		return rootView;
	}

}
