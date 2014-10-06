package sdsu.apps.sdsudining;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class SdsuDining extends Application{
	private static boolean isAppStart = true;
	private static String TAG = "STATE TEST";	
	
	public static void  appStatus(Activity activity){
		ActivityManager activityManager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> services = activityManager.getRunningTasks(1);
		
		if(services.get(0).topActivity.getPackageName().toString().equalsIgnoreCase(activity.getApplicationContext().getPackageName())){
			SdsuDining.setFalse();
		}
		else{
			SdsuDining.setTrue();
		}
		
	}
	
	public static boolean isNetworkConnected(Activity activity){
		ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		try{
			if( connectivityManager.getActiveNetworkInfo().isConnected()){
				Log.i(TAG, "internet yup");
				return true;
			}}
		catch(RuntimeException e){
			Log.i(TAG, "internet nope");
			return false;
		}
		Log.i(TAG, "internet nope");
		return false;
	}
	
	public static void setTrue(){
		Log.i(TAG, "state set to true");
		isAppStart = true;
	}
	
	public static void setFalse(){
		Log.i(TAG, "state set to false");
		isAppStart = false;
	}
	
	public static boolean isAppStart(){
		return isAppStart;
	}
}
