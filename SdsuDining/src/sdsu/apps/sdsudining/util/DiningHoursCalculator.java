package sdsu.apps.sdsudining.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.util.Log;

public class DiningHoursCalculator {

	public DiningHoursCalculator(){}
	
	public String getPreviousPacificTimeDay(){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		c.add(Calendar.DAY_OF_WEEK, -1);
		int day = c.get(Calendar.DAY_OF_WEEK);
		Log.i("PLAIN TEST", getDay(day));
		return getDay(day);
	}
	
	public String getPacificTimeDay(){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		int day = c.get(Calendar.DAY_OF_WEEK);
		return getDay(day);
	}
	
	private String getDay(int day){
		if(day == Calendar.MONDAY){
			return "Monday";
		}
		else if(day == Calendar.TUESDAY){
			return "Tuesday";
		}
		else if(day == Calendar.WEDNESDAY){
			return "Wednesday";
		}
		else if(day == Calendar.THURSDAY){
			return "Thursday";
		}
		else if(day == Calendar.FRIDAY){
			return "Friday";
		}
		else if(day == Calendar.SATURDAY){
			return "Saturday";
		}
		else{
			return "Sunday";
		}
	}


	public String getPacificTime(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		return sdf.format(d);
	}

	public long getTimeDifference(String openingTime, String closingTime){
		String currentTime = getPacificTime();
		DateFormat df = new SimpleDateFormat("hh:mm a", Locale.US);

		long diff = 0;
		if(!openingTime.equals("") || !closingTime.equals("")){
			try {
				Date currTime = df.parse(currentTime);
				Date openTime = df.parse(openingTime);
				Date closeTime = df.parse(closingTime);

				/*if(isCurrentTimeBetweenOpenAndClose(currTime, openTime, closeTime)){
				diff = closeTime.getTime()-currTime.getTime();
				diff = TimeUnit.MILLISECONDS.toMinutes(diff);
			}*/
				diff = getDifference(currTime, openTime, closeTime);
				diff = TimeUnit.MILLISECONDS.toMinutes(diff);
				Log.i("PLAIN TEST", "diff to minutes: "+String.valueOf(diff));

			} catch (ParseException e) {
				Log.e(this.toString(), e.getMessage());
			}
		}
		return diff;

	}
	

	private long getDifference(Date currTime, Date openTime, Date closeTime){
		Log.i("PLAIN TEST", "currTime " + currTime);
		Log.i("PLAIN TEST", "open "+openTime);
		Log.i("PLAIN TEST", "close " +closeTime);

		//if currentTime > openTime and currentTime < closeTime. Handles, for example 7:00am - 4:00pm currTime=>10am
		if (currTime.compareTo(openTime) >= 0 && currTime.compareTo(closeTime) < 0){
			Log.i("PLAIN TEST", "a");
			return closeTime.getTime()-currTime.getTime();
		}
		//if closeTime < openTime and currentTime > openTime and currentTime > closeTime. Handle for example, 6:00am - 12:00am currTime=>10am
		else if(closeTime.compareTo(openTime) < 0 && currTime.compareTo(openTime) >= 0 && currTime.compareTo(closeTime) >= 0){
			Log.i("PLAIN TEST", "b");

			Calendar cal = new GregorianCalendar();
			cal.setTime(currTime);
			int curr = cal.get(Calendar.HOUR_OF_DAY);
			cal.setTime(closeTime);
			int close = cal.get(Calendar.HOUR_OF_DAY);

			Log.i("PLAIN TEST", "IN B curr: "+String.valueOf(curr));
			Log.i("PLAIN TEST", "IN B close: "+String.valueOf(close));
			if(curr==23 && close==0){
				cal.add(Calendar.DATE, 1);
				closeTime.setTime(cal.getTimeInMillis());
				Log.i("PLAIN TEST", "NEW CLOSE " + closeTime);
				return closeTime.getTime() - currTime.getTime();
			}
			return currTime.getTime()-closeTime.getTime();
		}
		//if closeTime < openTime and currentTime < openTime and currentTime < closeTime. Handle for example, 6:00am - 1:00am currTime=>12am
		else if(closeTime.compareTo(openTime) < 0 && currTime.compareTo(openTime) < 0 && currTime.compareTo(closeTime) < 0){
			Log.i("PLAIN TEST", "c");
			return closeTime.getTime()-currTime.getTime();
		}
		// if closeTime = openTime (24 hours open), return a millisecond value larger than 3660000 indicating always open
		else if(closeTime.compareTo(openTime) == 0){
			Log.i("PLAIN TEST", "d");
			return 4200000;
		}
		else{
			Log.i("PLAIN TEST", "e");
			return 0;
		}
	}

	private boolean isCurrentTimeBetweenOpenAndClose(Date currTime, Date openTime, Date closeTime){
		Log.i("PLAIN TEST", "currTime " + currTime);
		Log.i("PLAIN TEST", "open "+openTime);
		Log.i("PLAIN TEST", "close" +closeTime);

		Log.i("PLAIN TEST", String.valueOf(closeTime.compareTo(openTime) < 0));
		Log.i("PLAIN TEST", String.valueOf(currTime.compareTo(openTime) >= 0));
		Log.i("PLAIN TEST", String.valueOf(currTime.compareTo(closeTime) >= 0));

		//if currentTime > openTime and currentTime < closeTime. Handles, for example 7:00am - 4:00pm
		if (currTime.compareTo(openTime) >= 0 && currTime.compareTo(closeTime) < 0){
			Log.i("PLAIN TEST", "a");
			return true;
		}
		//if closeTime < openTime and currentTime > openTime and currentTime > closeTime. Handle for example, 6:00am - 1:00am
		else if(closeTime.compareTo(openTime) < 0 && currTime.compareTo(openTime) >= 0 && currTime.compareTo(closeTime) >= 0){
			Log.i("PLAIN TEST", "b");
			return true;
		}
		else{
			Log.i("PLAIN TEST", "c");
			return false;
		}

	}
}
