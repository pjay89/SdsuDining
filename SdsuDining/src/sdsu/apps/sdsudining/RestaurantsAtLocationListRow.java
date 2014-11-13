package sdsu.apps.sdsudining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * RestaurantsAtLocationListRow:
 * 	- Display each list row item
 * @author Priya Jayaprakash
 *
 */

public class RestaurantsAtLocationListRow extends Activity {
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
