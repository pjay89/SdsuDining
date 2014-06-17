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
		Bundle args = new Bundle();
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
}
