package sdsu.apps.sdsudining;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * SdsuDining:
 * - Verifies if network connectivity is available
 * - Verifies if application has appeared to the foreground
 * @author Priya Jayaprakash
 *
 */

public class SdsuDining extends Application{
	private static boolean isAppStart = true;	
	
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
				return true;
			}}
		catch(RuntimeException e){
			return false;
		}
		return false;
	}
	
	public static void setTrue(){
		isAppStart = true;
	}
	
	public static void setFalse(){
		isAppStart = false;
	}
	
	public static boolean isAppStart(){
		return isAppStart;
	}
}
