package sdsu.apps.sdsudining.util;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.fragements.DisplayAboutFragment;
import sdsu.apps.sdsudining.fragements.DisplayContactFragment;
import sdsu.apps.sdsudining.fragements.DisplayHousFragment;
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

	private ArrayList<HashMap<String, String>> dbList;
	private ArrayList<String> tabTitles;
	private String lableString;
	private Context context;
	
	public SectionsPagerAdapter(FragmentManager fragmentManager, ArrayList<String> tabTitles, String lableString, Context context) {
		super(fragmentManager);
		this.tabTitles = tabTitles;
		this.lableString = lableString;
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		
		if(lableString.equals(context.getString(R.string.farmersMarketString))){
			dbList = getFarmersDBDetails();
			return getFragement(position);
		}
		else if(lableString.equals(context.getString(R.string.cateringString))){
			dbList = getCateringDBDetails();
			return getFragement(position);
		}
		else if(lableString.equals(context.getString(R.string.sweetString))){
			dbList = getSweetDBDetails();
			return getFragement(position);
		}
		else{
			dbList = getContactDBDetails();
			return getFragement(position);
		}
	}

	@Override
	public int getCount() {
		return tabTitles.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return (tabTitles.get(position));			
	}
	
	private ArrayList<HashMap<String, String>> getFarmersDBDetails(){
		SdsuDBHelper db = new SdsuDBHelper(context.getApplicationContext());
		dbList = db.getFarmersDetails();
		db.close();
		return dbList;
	}

	private ArrayList<HashMap<String, String>> getCateringDBDetails(){
		SdsuDBHelper db = new SdsuDBHelper(context.getApplicationContext());
		dbList = db.getCateringDetails();
		db.close();
		return dbList;
	}
	
	private ArrayList<HashMap<String, String>> getSweetDBDetails(){
		SdsuDBHelper db = new SdsuDBHelper(context.getApplicationContext());
		dbList = db.getSweetDetails();
		db.close();
		return dbList;
	}
		
	private ArrayList<HashMap<String, String>> getContactDBDetails(){
		SdsuDBHelper db = new SdsuDBHelper(context.getApplicationContext());
		dbList = db.getContactDetails();
		db.close();
		return dbList;
	}
	
	private String getRestaurantHoursOn(String restaurantId, String day){
		SdsuDBHelper db = new SdsuDBHelper(context);
		String hours = db.getHoursFor(restaurantId, day);
		db.close();
		if(hours.equals(" - ")){
			return "Closed";
		}
		return hours;
	}
	
	private Fragment getHoursFragment(){
		Bundle args = new Bundle();
	
		args.putString(context.getResources().getString(R.string.monday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.monday)));
		args.putString(context.getResources().getString(R.string.tuesday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.tuesday)));
		args.putString(context.getResources().getString(R.string.wednesday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.wednesday)));
		args.putString(context.getResources().getString(R.string.thursday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.thursday)));
		args.putString(context.getResources().getString(R.string.friday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.friday)));
		args.putString(context.getResources().getString(R.string.saturday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.saturday)));
		args.putString(context.getResources().getString(R.string.sunday), getRestaurantHoursOn(lableString, context.getResources().getString(R.string.sunday)));
				
		Fragment fragment =  new DisplayHousFragment();
		fragment.setArguments(args);
		return fragment;

	}
	
	private Fragment getFragement(int position){
		if(position == 1){
			return getHoursFragment();
		}
		
		
		String phone="";
		String fax="";
		String email="";
		String website="";
		String address="";
		String about="";
		
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			phone = entry.get(context.getApplicationContext().getString(R.string.DB_PHONE));
			fax = entry.get(context.getApplicationContext().getString(R.string.DB_FAX));
			email = entry.get(context.getApplicationContext().getString(R.string.DB_EMAIL));
			website = entry.get(context.getApplicationContext().getString(R.string.DB_WEBSITE));
			address = entry.get(context.getApplicationContext().getString(R.string.DB_ADDRESS));
			about = entry.get(context.getApplicationContext().getString(R.string.DB_ABOUT));
		}
		
		Bundle args = new Bundle();
		Fragment fragment;
		
		if(position == 0){
			args.putString(context.getResources().getString(R.string.phone), phone);
			args.putString(context.getResources().getString(R.string.fax), fax);
			args.putString(context.getResources().getString(R.string.email), email);
			args.putString(context.getResources().getString(R.string.website), website);
			args.putString(context.getResources().getString(R.string.address), address);
						
			fragment = new DisplayContactFragment();
			fragment.setArguments(args);
			return fragment;
		}
		else{
			args.putString(context.getResources().getString(R.string.about), about);
			
			fragment = new DisplayAboutFragment();
			fragment.setArguments(args);
			return fragment;
		
			
		}
	}
}
