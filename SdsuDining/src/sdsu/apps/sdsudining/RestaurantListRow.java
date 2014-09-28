package sdsu.apps.sdsudining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RestaurantListRow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_list_row);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
}
