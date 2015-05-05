package com.team2.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Change GMT time to local time by airpot's longitude
 *
 */
public class TimeManagement {
	public static Date gmtLocal(String airportcode, Date date){
		Date local = new Date();
		HashMap<String, Airport> allAirports = AirlinesSystem.parseAirports();
		float airportLongitude = allAirports.get(airportcode).getLongitude();
		long hourDifference = Math.round((Math.abs(airportLongitude)/15)) -5;
		long diff=date.getTime() - 60*60*1000*hourDifference;
		local.setTime(diff);
		return local;
	}

}
