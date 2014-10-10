package sdsu.apps.sdsudining.fragements;

import sdsu.apps.sdsudining.R;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayContactFragment extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_display_contact, container, false);

		int orientation = getResources().getConfiguration().orientation;
		if(orientation == Configuration.ORIENTATION_PORTRAIT){
			rootView.setBackgroundResource(R.drawable.background_portrait);
		}
		else{
			rootView.setBackgroundResource(R.drawable.background_landscape);
		}
		
		TextView phone = (TextView) rootView.findViewById(R.id.phone);
		phone.setText(getArguments().getString(getActivity().getResources().getString(R.string.phone)));
		TextView fax = (TextView) rootView.findViewById(R.id.fax);
		fax.setText(getArguments().getString(getActivity().getResources().getString(R.string.fax)));
		
		TextView email = (TextView) rootView.findViewById(R.id.email);
		email.setText(getArguments().getString(getActivity().getResources().getString(R.string.email)));

		TextView website = (TextView) rootView.findViewById(R.id.website);
		website.setText(getArguments().getString(getActivity().getResources().getString(R.string.website)));
		
		TextView address = (TextView) rootView.findViewById(R.id.address);
		address.setText(getArguments().getString(getActivity().getResources().getString(R.string.address)));
		
		return rootView;
	}

}
