package sdsu.apps.sdsudining.fragements;

import sdsu.apps.sdsudining.R;
import android.content.res.Configuration;
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

		int orientation = getResources().getConfiguration().orientation;
		if(orientation == Configuration.ORIENTATION_PORTRAIT){
			rootView.setBackgroundResource(R.drawable.aboutus_bgi);
		}
		else{
			rootView.setBackgroundResource(R.drawable.aboutus_bgi_land);
		}
		
		TextView monday = (TextView) rootView.findViewById(R.id.mondayHoursText);
		monday.setText(getArguments().getString(getResources().getString(R.string.monday)));

		TextView tuesday = (TextView) rootView.findViewById(R.id.tuesdayHoursText);
		tuesday.setText(getArguments().getString(getResources().getString(R.string.tuesday)));

		TextView wednesday = (TextView) rootView.findViewById(R.id.wednesdayHoursText);
		wednesday.setText(getArguments().getString(getResources().getString(R.string.wednesday)));

		TextView thursday = (TextView) rootView.findViewById(R.id.thursdayHoursText);
		thursday.setText(getArguments().getString(getResources().getString(R.string.thursday)));

		TextView friday = (TextView) rootView.findViewById(R.id.fridayHoursText);
		friday.setText(getArguments().getString(getResources().getString(R.string.friday)));

		TextView saturday = (TextView) rootView.findViewById(R.id.saturdayHoursText);
		saturday.setText(getArguments().getString(getResources().getString(R.string.saturday)));

		TextView sunday = (TextView) rootView.findViewById(R.id.sundayHoursText);
		sunday.setText(getArguments().getString(getResources().getString(R.string.sunday)));

		TextView phone = (TextView) rootView.findViewById(R.id.restaurantPhoneText);
		phone.setText(getArguments().getString(getResources().getString(R.string.phone)));

		TextView website = (TextView) rootView.findViewById(R.id.restaurantWebsiteText);
		website.setText(getArguments().getString(getResources().getString(R.string.website)));

		TextView restaurantStatus = (TextView) rootView.findViewById(R.id.restaurantStatus);
		long difference = getArguments().getLong(getResources().getString(R.string.restaurantStatus));
		getClosingStatus(difference, restaurantStatus);

		return rootView;
	}

	private void getClosingStatus(long difference, TextView restaurantStatus){
		if(difference <= 60 && difference > 0){
			restaurantStatus.setText(difference + " mins to Close!");
			restaurantStatus.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(difference > 60){
			restaurantStatus.setText("OPEN!");
			restaurantStatus.setTextColor(getResources().getColor(R.color.green));
		}
		else{
			restaurantStatus.setText("Closed");
			restaurantStatus.setTextColor(getResources().getColor(R.color.red));
		}
	}


}
