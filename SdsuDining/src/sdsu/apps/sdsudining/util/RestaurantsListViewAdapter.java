package sdsu.apps.sdsudining.util;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.R;
import sdsu.apps.sdsudining.database.SdsuDBHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RestaurantsListViewAdapter extends BaseAdapter{
	private ArrayList<String> entries;
	private Context context;
	private static final String TAG = "RESTAURANT";
	
	public RestaurantsListViewAdapter(Context context){
		this.context = context;
		this.getRestaurants();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entries.size();
	}

	@Override
	public Object getItem(int index) {
		return entries.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.activity_restaurant_list_row, viewGroup, false);
		}
		
		ImageView restaurantListViewImage = (ImageView) view.findViewById(R.id.restaurantListViewImage);
		//restaurantListViewImage.setImageURI(entries.get(index));
	
		return view;
	}

	
	private void getRestaurants(){
		SdsuDBHelper db = new SdsuDBHelper(context);
		ArrayList<HashMap<String,String>> dbList = db.getUniqueRestaurantsExceptFarmersMarket();

		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			entries.add(entry.get(context.getString(R.string.RESTAURANT_IMAGE)));
		}
		db.close();
		
		
	}
}
