package sdsu.apps.sdsudining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CouponsListRow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupons_list_row);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coupons_list_row, menu);
		return true;
	}

}
