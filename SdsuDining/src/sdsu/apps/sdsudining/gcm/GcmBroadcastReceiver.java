package sdsu.apps.sdsudining.gcm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Broadcast receiver which listens for pushed messages from GCM servers. 
 * For additional information, refer to Android's GCM Push Notification Client Implementation webpage.
 * 
 * @author Priya Jayaprakash
 *
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// Explicitly specify that GcmIntentService will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(), GcmIntentService.class.getName());
        
        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);		
	}

}
