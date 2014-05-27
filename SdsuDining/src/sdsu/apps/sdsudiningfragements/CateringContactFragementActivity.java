package sdsu.apps.sdsudiningfragements;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CateringContactFragementActivity extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_catering_contact, container, false);
		
		SdsuDBHelper db = new SdsuDBHelper(getActivity().getApplicationContext());
		ArrayList<HashMap<String, String>> dbList = db.getCateringDetails();
		
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
		
			
			TextView phone = (TextView) rootView.findViewById(R.id.phone);
			phone.setText(entry.get(getActivity().getApplicationContext().getString(R.string.CATERING_PHONE)));
			
			TextView fax = (TextView) rootView.findViewById(R.id.fax);
			fax.setText(entry.get(getActivity().getApplicationContext().getString(R.string.CATERING_FAX)));
			
			TextView email = (TextView) rootView.findViewById(R.id.email);
			email.setText(entry.get(getActivity().getApplicationContext().getString(R.string.CATERING_EMAIL)));

			TextView website = (TextView) rootView.findViewById(R.id.websiteHeading);
			website.setText("");
			
			TextView address = (TextView) rootView.findViewById(R.id.address);
			address.setText(entry.get(getActivity().getApplicationContext().getString(R.string.CATERING_ADDRESS)));
		}
		
		db.close();
		
		
		
		return rootView;
	}

}
