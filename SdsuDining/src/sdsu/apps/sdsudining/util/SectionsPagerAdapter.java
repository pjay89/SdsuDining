package sdsu.apps.sdsudining.util;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.DetailsActivity.DummySectionFragment;
import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.fragements.CateringContactFragmentActivity;
import sdsu.apps.sdsudining.fragements.DisplayContactFragment;
import sdsu.apps.sdsudining.fragements.DisplayInfoFragment;
import sdsu.apps.sdsudining.fragements.DisplayWebFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	ArrayList<String> tabTitles;
	String lableString;
	Context context;
	
	private String TAG = "SECTIONS";	


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
		else if(lableString.equals(context.getString(R.string.sweetString))){
			return getSweetFragment(position);
		}



		return new DummySectionFragment();

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
			return new CateringContactFragmentActivity();
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

	
	private Fragment getSweetFragment(int position){
		SdsuDBHelper db = new SdsuDBHelper(context.getApplicationContext());
		ArrayList<HashMap<String, String>> dbList = db.getSweetDetails();
		
		String phone="";
		String fax="";
		String email="";
		String website="";
		String menu="";
		String orderForm="";
		
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			phone = entry.get(context.getApplicationContext().getString(R.string.SWEET_PHONE));
			fax = entry.get(context.getApplicationContext().getString(R.string.SWEET_FAX));
			email = entry.get(context.getApplicationContext().getString(R.string.SWEET_EMAIL));
			website = entry.get(context.getApplicationContext().getString(R.string.SWEET_WEBSITE));
			menu = entry.get(context.getApplicationContext().getString(R.string.SWEET_MENU));
			orderForm = entry.get(context.getApplicationContext().getString(R.string.SWEET_ORDER_FORM));
		}
		db.close();
		
		
		Bundle args = new Bundle();
		Fragment fragment;
		
		if(position == 0){
			args.putString(context.getResources().getString(R.string.phone), phone);
			args.putString(context.getResources().getString(R.string.fax), fax);
			args.putString(context.getResources().getString(R.string.email), email);
			args.putString(context.getResources().getString(R.string.website), website);
			
			fragment = new DisplayContactFragment();
			fragment.setArguments(args);
			return fragment;
		}
		else if(position == 1){
			Log.i(TAG, menu);
			args.putString(context.getResources().getString(R.string.menu), menu);
			
			fragment = new DisplayWebFragment();
			fragment.setArguments(args);
			return fragment;
		}
		else{
			
		}
		return new DummySectionFragment();
	}
}
