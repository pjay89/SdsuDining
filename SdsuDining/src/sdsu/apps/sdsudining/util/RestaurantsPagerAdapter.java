package sdsu.apps.sdsudining.util;

import java.util.ArrayList;

import sdsu.apps.sdsudiningfragements.DisplayRestaurantInfoFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class RestaurantsPagerAdapter extends FragmentPagerAdapter{

	private ArrayList<String> tabTitles;
	private String lableString;
	private Context context;
	
	private String TAG = "SECTIONS";
	
	public RestaurantsPagerAdapter(FragmentManager fragmentManager, ArrayList<String> tabTitles, String lableString, Context context) {
		super(fragmentManager);
		this.tabTitles = tabTitles;
		this.lableString = lableString;
		this.context = context;
	}
	
	
	@Override
	public Fragment getItem(int position) {
		Log.i(TAG, "pos: "+ position + " lableString: "+ lableString);
		return new DisplayRestaurantInfoFragment();
	}

	@Override
	public int getCount() {
		return tabTitles.size();
	}

}
