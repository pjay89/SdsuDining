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
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 * @author Priya Jayaprakash
 *
 */

public class RestaurantsPagerAdapter extends FragmentStatePagerAdapter{
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
	public int getItemPosition(Object object) {
	     return POSITION_NONE;
	}
	
	/**
	 * Create each fragment by providing the necessary data to be displayed
	 */
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
		/**
		 * Calculate time difference used to display restaurant status. If today's difference is 0 (no match found OR closed), 
		 * verify previous day's hours as well to monitor restaurant status. For example, these checks handle the following situations:
		 * A) Current time: 10am, Today Open: 8am, Today Close: 11pm
		 * B) Current time: 10am, Today Open: 12am, Today Close: 12am
		 * C) Current time: 12am, Today Open: 7am, Today Close: 5pm BUT Yesterday Open: 7am Yesterday Close: 1am (ie. next day)
		 */
		
		long difference = getRestaurantTodayTimeDifference(restaurantId);
		if(difference == 0){
			difference = getRestaurantYesterdayTimeDifference(restaurantId);
		}		
		return difference;	
	}
	
	private long getRestaurantTodayTimeDifference(String restaurantId){
		DiningHoursCalculator diningHoursCalc = new DiningHoursCalculator();
		
		SdsuDBHelper db = new SdsuDBHelper(context);
		ArrayList<String[]> hours = db.getHoursForRestaurantStatus(restaurantId, diningHoursCalc.getPacificTimeDay());
		db.close();
		
		long difference = 0;
		//iterate through all the hours for a given day
		for(String[] entry : hours){
			difference = diningHoursCalc.getTodayTimeDifference(entry[0], entry[1]);
			if(difference > 0){
				//stop iteration at first open encounter
				break;
			}
		}
		
		return difference;
	}
	
	private long getRestaurantYesterdayTimeDifference(String restaurantId){
		DiningHoursCalculator diningHoursCalc = new DiningHoursCalculator();
		
		SdsuDBHelper db = new SdsuDBHelper(context);
		ArrayList<String[]> hours = db.getHoursForRestaurantStatus(restaurantId, diningHoursCalc.getYesterdayPacificTimeDay());
		db.close();
		
		long difference = 0;
		//iterate through all the hours for a given day
		for(String[] entry : hours){
			difference = diningHoursCalc.getYesterdayTimeDifference(entry[0], entry[1]);
			if(difference > 0){
				//stop iteration at first open encounter
				break;
			}
		}
		
		return difference;
	}
	
	private String getRestaurantHoursOn(String restaurantId, String day){
		SdsuDBHelper db = new SdsuDBHelper(context);
		String hours = db.getHoursFor(restaurantId, day);
		db.close();
		
		if(hours.equals(" - ") || hours.equals("")){
			return "Closed";
		}
		return hours;
	}
}
