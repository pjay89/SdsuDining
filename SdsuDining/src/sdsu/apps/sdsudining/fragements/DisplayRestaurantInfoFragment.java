package sdsu.apps.sdsudining.fragements;

import sdsu.apps.sdsudining.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayRestaurantInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_restaurant_info, container, false);
		
		TextView phone = (TextView) rootView.findViewById(R.id.restaurantPhoneText);
		phone.setText(getArguments().getString(getActivity().getResources().getString(R.string.phone)));
		
		TextView website = (TextView) rootView.findViewById(R.id.restaurantWebsiteText);
		website.setText(getArguments().getString(getActivity().getResources().getString(R.string.website)));
		
		return rootView;
	}

}
