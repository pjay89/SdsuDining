package sdsu.apps.sdsudining.util;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.fragements.DisplayRestaurantInfoFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RestaurantsPagerAdapter extends FragmentPagerAdapter{
	ArrayList<HashMap<String,String>> dbList;
	private Context context;
	
	public RestaurantsPagerAdapter(FragmentManager fragmentManager, String lableString, Context context) {
		super(fragmentManager);
		this.context = context;
		
		SdsuDBHelper db = new SdsuDBHelper(context);
		dbList = db.getAllRestaurantDetailsOf(lableString);
		db.close();
	}
	
	
	@Override
	public Fragment getItem(int position) {
		HashMap<String, String> entry = dbList.get(position);
		String restaurantId = entry.get(context.getString(R.string.RESTAURANT_ID));
		Bundle args = new Bundle();
		
		args.putLong(context.getResources().getString(R.string.restaurantStatus), getRestaurantClosingTimeDifference(restaurantId));
		
		args.putString(context.getResources().getString(R.string.monday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.monday)));
		args.putString(context.getResources().getString(R.string.tuesday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.tuesday)));
		args.putString(context.getResources().getString(R.string.wednesday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.wednesday)));
		args.putString(context.getResources().getString(R.string.thursday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.thursday)));
		args.putString(context.getResources().getString(R.string.friday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.friday)));
		args.putString(context.getResources().getString(R.string.saturday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.saturday)));
		args.putString(context.getResources().getString(R.string.sunday), getRestaurantHoursOn(restaurantId, context.getResources().getString(R.string.sunday)));

		args.putString(context.getResources().getString(R.string.phone), entry.get(context.getString(R.string.RESTAURANT_PHONE)));
		args.putString(context.getResources().getString(R.string.website), entry.get(context.getString(R.string.RESTAURANT_WEBSITE)));
				
		Fragment fragment =  new DisplayRestaurantInfoFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return dbList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		HashMap<String, String> entry = dbList.get(position);
		return entry.get(context.getString(R.string.RESTAURANT_LOCATION_NAME));
	}
	
	public int getPageTitlePosition(String title){
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			if(entry.get(context.getString(R.string.RESTAURANT_LOCATION_NAME)).equals(title)){
				return i;
			}
		}
		return 0;
	}
	
	private long getRestaurantClosingTimeDifference(String restaurantId){
		DiningHoursCalculator diningHoursCalc = new DiningHoursCalculator();
		
		SdsuDBHelper db = new SdsuDBHelper(context);
		ArrayList<String[]> hours = db.getHoursForRestaurantStatus(restaurantId, diningHoursCalc.getPacificTimeDay());
		db.close();
		
		long difference = 0;
		for(String[] entry : hours){
			difference = diningHoursCalc.getTimeDifference(entry[0], entry[1]);
			if(difference > 0){
				break;
			}
		}
		
		//long difference = diningHoursCalc.getTimeDifference(closingTime);		
		return difference;
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
}
