package sdsu.apps.sdsudining.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class GcmIntentService extends IntentService{

	public GcmIntentService() {
		super("GcmIntentService");
	}

	private NotificationManager mNotificationManager;
	private NotificationCompat.Builder builder;
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
        //GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

		
	}

}
