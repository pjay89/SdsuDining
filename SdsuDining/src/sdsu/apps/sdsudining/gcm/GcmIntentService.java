package sdsu.apps.sdsudining.gcm;

import java.util.Random;

import sdsu.apps.sdsudining.LoadingActivity;
import sdsu.apps.sdsudining.R;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmIntentService extends IntentService{

	public GcmIntentService() {
		super("GcmIntentService");
	}

	private NotificationManager mNotificationManager;
	private static final String TAG = "SDSU_GCM";
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                // Ignoring message type
            	// sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                // Ignoring message type
            	// sendNotification("Deleted messages on server: " + extras.toString());
            
            // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                for (int i=0; i<5; i++) {
                    try {
                    	Log.i(TAG, "Working... " + (i+1)
                                + "/5 @ " + SystemClock.elapsedRealtime());
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    	Log.e(TAG, "gcm intent serv: " + e.getMessage());
                    	//Error Collector
            			//e.printStackTrace();
                    }
                }
                // Post notification of received message.
                sendNotification(String.valueOf(extras.get(Configurations.MESSAGE_KEY)));
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
		
	

//Put the message into a notification and post it.
private void sendNotification(String msg) {
    mNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

    // Generate a random number, get the current timestamp, add the 2 ints, and set as the notification id
    Random randomGenerator = new Random();
    int id = randomGenerator.nextInt(100) + (int)(System.currentTimeMillis()/1000);
    
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
    	.setSmallIcon(R.drawable.ic_launcher)
    	.setContentTitle(getApplicationContext().getString(R.string.gcmNotificationTitle))
    	.setStyle(new NotificationCompat.BigTextStyle()
    	.bigText(msg))
    	.setContentText(msg);
    
    Intent notificationIntent = new Intent(this, LoadingActivity.class);
    notificationIntent.putExtra(Configurations.MESSAGE_KEY, msg);
    notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    
    PendingIntent contentIntent = PendingIntent.getActivity(this, id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    
    mBuilder.setContentIntent(contentIntent)
    	.setDefaults(Notification.DEFAULT_ALL);
    
    mNotificationManager.notify(id, mBuilder.build());
}

}
