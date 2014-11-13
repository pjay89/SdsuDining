package sdsu.apps.sdsudining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * CouponsListRow:
 * 	- Display each list row item
 * @author Priya Jayaprakash
 *
 */

public class CouponsListRow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupons_list_row);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
