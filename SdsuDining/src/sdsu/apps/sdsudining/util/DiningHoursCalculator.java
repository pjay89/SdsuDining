package sdsu.apps.sdsudining.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.util.Log;

public class DiningHoursCalculator {
	
	public DiningHoursCalculator(){}
	
	public String getPacificTimeDay(){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		int day = c.get(Calendar.DAY_OF_WEEK);
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
		try {
			Date currTime = df.parse(currentTime);
			Date openTime = df.parse(openingTime);
			Date closeTime = df.parse(closingTime);
						
			if(isCurrentTimeBetweenOpenAndClose(currTime, openTime, closeTime)){
				diff = closeTime.getTime()-currTime.getTime();
				diff = TimeUnit.MILLISECONDS.toMinutes(diff);
			}
		} catch (ParseException e) {
			Log.e(this.toString(), e.getMessage());
		}
		return diff;
		
	}
	
	private boolean isCurrentTimeBetweenOpenAndClose(Date currTime, Date openTime, Date closeTime){
		if (currTime.compareTo(openTime) >= 0 && currTime.compareTo(closeTime) < 0){
			return true;
		}
		return false;
		//return currTime.after(openTime) && currTime.before(closeTime);
		
	}
}
