package sdsu.apps.sdsudining.gcm;

public class Configurations {
	// used to share GCM regId with application server - using php app server
	static final String APP_SERVER_URL = "http://130.191.3.5/~jayaprak/sdsuDining/gcm/gcm.php/?shareRegId=1";

	// GCM server using java
	// static final String APP_SERVER_URL =
	// "http://192.168.1.17:8080/GCM-App-Server/GCMNotification?shareRegId=1";

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "397619223503";
	static final String MESSAGE_KEY = "m";
}
