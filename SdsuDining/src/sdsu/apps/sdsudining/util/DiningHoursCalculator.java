package sdsu.apps.sdsudining.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Calculates corresponding time difference b/w current time, open time, and close time. 
 * Uses TimeZone: America/Los_Angeles to handle PST and PDT. 
 * 
 * @author Priya Jayaprakash
 *
 */

public class DiningHoursCalculator {

	public DiningHoursCalculator(){}

	public String getYesterdayPacificTimeDay(){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		c.add(Calendar.DAY_OF_WEEK, -1);
		int day = c.get(Calendar.DAY_OF_WEEK);
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

	public long getTodayTimeDifference(String openingTime, String closingTime){

		String currentTime = getPacificTime();
		DateFormat df = new SimpleDateFormat("hh:mm a", Locale.US);

		long diff = 0;
		if(!openingTime.equals("") || !closingTime.equals("")){
			try {					
				Date currTime = df.parse(currentTime);
				Date openTime = df.parse(openingTime);
				Date closeTime = df.parse(closingTime);

				diff = getTodayDifference(currTime, openTime, closeTime);
				diff = TimeUnit.MILLISECONDS.toMinutes(diff);
			} catch (ParseException e) {
				//Error Collector
				//e.printStackTrace();
			}
		}

		return diff;
	}

	public long getYesterdayTimeDifference(String yesterdayOpeningTime, String yesterdayClosingTime){
		String currentTime = getPacificTime();
		DateFormat df = new SimpleDateFormat("hh:mm a", Locale.US);

		long diff = 0;
		if(!yesterdayOpeningTime.equals("") || !yesterdayClosingTime.equals("")){
			try {
				Date currTime = df.parse(currentTime);
				Date yesterdayOpenTime = df.parse(yesterdayOpeningTime);
				Date yesterdayCloseTime = df.parse(yesterdayClosingTime);

				diff = getYesterdayDifference(currTime, yesterdayOpenTime, yesterdayCloseTime);
				diff = TimeUnit.MILLISECONDS.toMinutes(diff);

			} catch (ParseException e) {
				//Error Collector
				//e.printStackTrace();
			}
		}		
		return diff;
	}

	private long getTodayDifference(Date currTime, Date openTime, Date closeTime){

		// if closeTime == openTime OR closeTime < openTime,  increment closeTime by a day
		if(closeTime.compareTo(openTime) == 0 || closeTime.compareTo(openTime) < 0 ){
			Calendar cal = new GregorianCalendar();
			cal.setTime(closeTime);
			cal.add(Calendar.DATE, 1);
			closeTime.setTime(cal.getTimeInMillis());
		}

		//if currentTime > openTime and currentTime < closeTime.
		if (currTime.compareTo(openTime) >= 0 && currTime.compareTo(closeTime) < 0){
			return closeTime.getTime()-currTime.getTime();
		}
		else{
			return 0;
		}

	}


	private long getYesterdayDifference(Date currTime, Date yesterdayOpenTime, Date yesterdayCloseTime){
		Calendar cal = new GregorianCalendar();
		cal.setTime(currTime);
		cal.add(Calendar.DATE, 1);
		currTime.setTime(cal.getTimeInMillis());

		// if closeTime == openTime OR closeTime < openTime,  increment closeTime by a day
		if(yesterdayCloseTime.compareTo(yesterdayOpenTime) == 0 || yesterdayCloseTime.compareTo(yesterdayOpenTime) < 0 ){
			cal.setTime(yesterdayCloseTime);
			cal.add(Calendar.DATE, 1);
			yesterdayCloseTime.setTime(cal.getTimeInMillis());
		}

		//if currentTime+1 > openTime and currentTime+1 < closeTime.
		if (currTime.compareTo(yesterdayOpenTime) >= 0 && currTime.compareTo(yesterdayCloseTime) < 0){
			return yesterdayCloseTime.getTime()-currTime.getTime();
		}
		else{
			return 0;
		}

	}
}
