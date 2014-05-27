package sdsu.apps.sdsudining.util;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudiningfragements.CateringContactFragementActivity;
import sdsu.apps.sdsudiningfragements.DisplayInfoFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	ArrayList<String> tabTitles;
	String lableString;
	Context context;
	


	public SectionsPagerAdapter(FragmentManager fragmentManager, ArrayList<String> tabTitles, String lableString, Context context) {
		super(fragmentManager);
		this.tabTitles = tabTitles;
		this.lableString = lableString;
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {


		if(lableString.equals(context.getString(R.string.cateringString))){
			return getCateringFragement(position);
		}
		return null;
 







	}

	@Override
	public int getCount() {
		return tabTitles.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return (tabTitles.get(position));			
	}

	private Fragment getCateringFragement(int position){
		/*Fragment fragment;
		Bundle args = new Bundle();
		if(position == 0){
			fragment = new CateringContactFragementActivity();
			args.putInt(CateringContactFragementActivity.ARG_SECTION_NUMBER, position + 1);
		}
		else if(position == 1){
			fragment = new DummySectionFragment();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		}
		else{
			fragment = new DummySectionFragment();
			fragment.setArguments(args);
			return fragment;
		}*/
		

		SdsuDBHelper db = new SdsuDBHelper(context.getApplicationContext());
		ArrayList<HashMap<String, String>> dbList = db.getCateringDetails();
		
		String info="";
		String gettingStarted="";
		
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			info = entry.get(context.getApplicationContext().getString(R.string.CATERING_SNIPPET));
			gettingStarted = entry.get(context.getApplicationContext().getString(R.string.CATERING_GUIDELINES)) + "\n\n" + 
						entry.get(context.getApplicationContext().getString(R.string.CATERING_SERVICE_LEVEL));
			
		}
		db.close();
		
		if(position == 0){
			return new CateringContactFragementActivity();
		}
		if(position == 1){
			Bundle args = new Bundle();
			args.putString("info", info);
			Fragment f = new DisplayInfoFragment();
			f.setArguments(args);
			return f;
		}
		
		Bundle args = new Bundle();
		args.putString("info", gettingStarted);
		Fragment f = new DisplayInfoFragment();
		f.setArguments(args);
		return f;

	}

}
