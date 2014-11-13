package sdsu.apps.sdsudining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * BrowseByLocationListRow:
 * 	- Display each list row item
 * @author Priya Jayaprakash
 *
 */

public class BrowseByLocationListRow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_by_location_list_row);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
