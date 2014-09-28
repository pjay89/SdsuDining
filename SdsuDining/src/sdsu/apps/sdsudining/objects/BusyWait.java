package sdsu.apps.sdsudining.objects;

import android.app.ProgressDialog;
import android.content.Context;

public class BusyWait {
	ProgressDialog progressDialog;
	
	public BusyWait (Context context){
		progressDialog = new ProgressDialog(context);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
		progressDialog.setInverseBackgroundForced(true);
		progressDialog.setMessage("Loading...");
	}
	
	public void show(){
		progressDialog.show();
	}
	
	public void dismiss(){
		progressDialog.dismiss();
	}
}
