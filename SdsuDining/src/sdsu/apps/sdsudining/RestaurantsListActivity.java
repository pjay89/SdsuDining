package sdsu.apps.sdsudining;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RestaurantsListActivity extends Activity {

	private Button browseByMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_list);
		
		// Enable Home button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		browseByMap = (Button) findViewById(R.id.browseByMap);
		browseByMap.setOnClickListener(getBrowseByMapButtonListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurants_list, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
                return true;
        }
        return false;
    }
	
	public OnClickListener getBrowseByMapButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(RestaurantsListActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.browseByMapString));
			startActivity(intent);
		}

	};

}
