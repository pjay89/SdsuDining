package sdsu.apps.sdsudiningfragements;

import sdsu.apps.sdsudining.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DisplayRestaurantInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_restaurant_info, container, false);
		
		return rootView;
	}

}
