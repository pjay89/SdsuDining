package sdsu.apps.sdsudiningfragements;

import sdsu.apps.sdsudining.R;
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

		TextView phone = (TextView) rootView.findViewById(R.id.phone);
		phone.setText(getArguments().getString(getActivity().getResources().getString(R.string.phone)));
		TextView fax = (TextView) rootView.findViewById(R.id.fax);
		fax.setText(getArguments().getString(getActivity().getResources().getString(R.string.fax)));
		
		TextView email = (TextView) rootView.findViewById(R.id.email);
		email.setText(getArguments().getString(getActivity().getResources().getString(R.string.email)));

		TextView website = (TextView) rootView.findViewById(R.id.website);
		website.setText(getArguments().getString(getActivity().getResources().getString(R.string.website)));
		
		return rootView;
	}

}
