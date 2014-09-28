package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CouponsListActivity extends Activity {

	private AQuery rootAQuery;
	private ArrayList<String> entries = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupons_list);

		// Enable Home button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		SdsuDBHelper db = new SdsuDBHelper(this);
		ArrayList<HashMap<String,String>> dbList = db.getCouponDetails();

		rootAQuery = new AQuery(this);
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			entries.add(entry.get(getString(R.string.COUPON_IMAGE)));
		}
		db.close();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_coupons_list_row, entries){
			@Override
			public View getView(int position, View convertView, ViewGroup parent){
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.activity_coupons_list_row, null);
				}
				
				AQuery listRowAQuery = new AQuery(convertView);
				String url = entries.get(position);
				ImageOptions options = new ImageOptions();
				options.ratio = AQuery.RATIO_PRESERVE;
				listRowAQuery.id(R.id.couponsListViewImage).image(url, options);
				return convertView;
			}
		};

		ListView listView = (ListView) findViewById(R.id.couponsListView);
		listView.setAdapter(adapter);

		rootAQuery.id(R.id.couponsListView).adapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, HomeActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
			return true;
		}
		return false;
	}

	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
	}
}
