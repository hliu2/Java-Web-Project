package com.team2.model.customer;

import java.util.ArrayList;
import java.util.Collections;


import java.util.Date;

import com.team2.model.TimeManagement;
import com.team2.model.ticket.TwowayTicketPlan;

/**
 * @author Naihui Wang
 *
 */
/**
 * @author Administrator
 *
 */
public class TwowaySort {
	/**
	 * @param f an arraylist of TwowayTicketPlan
	 * @param stopOver valid value is 123
	 * @return an arraylist of TwowayTicketPlan which contains flights with certain stopover
	 */
	public static ArrayList<TwowayTicketPlan> selectStopovers(ArrayList<TwowayTicketPlan> f,int toStopOver,int backStopover)
	{   
		ArrayList<TwowayTicketPlan> allPlans = f;
		ArrayList<TwowayTicketPlan> result=new ArrayList<TwowayTicketPlan>();
	    int toStop=toStopOver;
	    int backStop=backStopover;
	    if(toStop<0&&toStop>2)
	    {
	    	throw new IllegalArgumentException();
	    }
	    if(backStop<0&&backStop>2)
	    {
	    	throw new IllegalArgumentException();
	    }
	    for(TwowayTicketPlan i:allPlans)
	    {
	    	if(i.getDepartureNumOfStopovers()==toStop&&i.getArrivalNumOfStopovers()==backStop)
	    	{
	    		result.add(i);
	    	}
	    
	    }
		
		return result;
		}

	/**
	 * @param f an arraylist of TwowayTicketPlan
	 * @return  an arraylist of TwowayTicketPlan sorted by total price of each ticket plan
	 */
	public static ArrayList<TwowayTicketPlan> sortByTotalPrice(ArrayList<TwowayTicketPlan> f)
	{
		ArrayList<TwowayTicketPlan> allPlans = f;

		Collections.sort(allPlans, TwowayTicketPlan.BY_PRICE );
		return allPlans;
		
		}

	/**
	 * @param f an arraylist of TwowayTicketPlan
	 * @return an arraylist of TwowayTicketPlan sorted by total time of each ticket plan
	 */
	public static ArrayList<TwowayTicketPlan> sortByTotalTime(ArrayList<TwowayTicketPlan> f)
	{
		ArrayList<TwowayTicketPlan> allPlans = f;

		Collections.sort(allPlans, TwowayTicketPlan.BY_TOTAL_TIME);
		return allPlans;
		}

	/**
	 * @param f arraylist of TwowayTicketPlan
	 * @return an arraylist of TwowayTicketPlan sorted by flight time of each ticket plan
	 */
	public static ArrayList<TwowayTicketPlan> sortByFlightTime(ArrayList<TwowayTicketPlan> f)
	{
		ArrayList<TwowayTicketPlan> allPlans = f;

		Collections.sort(allPlans, TwowayTicketPlan.BY_FLIGHT_TIME);
		return allPlans;
		}
	
	/**
	 * @param f an arraylist of TwowayTicketPlan
	 * @param dtoFrom Departure flight departure time window start hour
	 * @param dtoTo   Departure flight departure time window end hour
	 * @param atoFrom Departure flight arrival time window start hour
	 * @param atoTo   Departure flight arrival time window end hour
	 * @param dbackFrom Return flight departure time window start hour
	 * @param dbackTo   Return flight departure time window end hour
	 * @param abackFrom Return flight arrival time window start hour
	 * @param abackTo   Return flight arrival time window end hour
	 * @return an arraylist of TwowayTicketPlan in which the departure time and arrival time of departure flight and return flight will satisfy the time range set above respectively
	 */
	public static ArrayList<TwowayTicketPlan> sortByTimeWindow(ArrayList<TwowayTicketPlan> f,int dtoFrom,int dtoTo,int atoFrom,int atoTo,int dbackFrom,int dbackTo,int abackFrom,int abackTo)
	{
		ArrayList<TwowayTicketPlan> allPlans = f;
		ArrayList<TwowayTicketPlan> result = new ArrayList<TwowayTicketPlan>();
		for(int i=0;i<allPlans.size();i++)
	    {  
			int tosize=allPlans.get(i).getDeparturePlan().size()-1;
			int backsize=allPlans.get(i).getArrivalPlan().size()-1;
			
			Date dtoDeparturetime=allPlans.get(i).getDeparturePlan().get(0).getDepartureTime();
			String dtodepaturecode = allPlans.get(i).getDeparturePlan().get(0).getDepatureAirport().getCode();
	    	Date dtodeparturelocaltime=TimeManagement.gmtLocal(dtodepaturecode,dtoDeparturetime);
	    	
			Date atoDeparturetime=allPlans.get(i).getDeparturePlan().get(tosize).getArrivalTime();
			String atodepaturecode = allPlans.get(i).getDeparturePlan().get(tosize).getArrivalAirport().getCode();
			Date atodeparturelocaltime=TimeManagement.gmtLocal(atodepaturecode,atoDeparturetime);
			
	        Date dbackDeparturetime=allPlans.get(i).getArrivalPlan().get(0).getDepartureTime();
	        String dbackdepaturecode = allPlans.get(i).getArrivalPlan().get(0).getDepatureAirport().getCode();
	    	Date dbackdeparturelocaltime=TimeManagement.gmtLocal(dbackdepaturecode,dbackDeparturetime);
	        
	        Date abackDeparturetime=allPlans.get(i).getArrivalPlan().get(backsize).getArrivalTime();
	        String abackdepaturecode = allPlans.get(i).getArrivalPlan().get(backsize).getArrivalAirport().getCode();
			Date abackdeparturelocaltime=TimeManagement.gmtLocal(abackdepaturecode,abackDeparturetime);
	        @SuppressWarnings("deprecation")
			int dtoHour=dtodeparturelocaltime.getHours();
	        @SuppressWarnings("deprecation")
			int atoHour=atodeparturelocaltime.getHours();
	        @SuppressWarnings("deprecation")
			int dbackHour=dbackdeparturelocaltime.getHours();
	        @SuppressWarnings("deprecation")
			int abackHour=abackdeparturelocaltime.getHours();
	    	if(dtoHour>= dtoFrom&&dtoHour<dtoTo&&atoHour>= atoFrom&&atoHour<atoTo&&dbackHour<dbackTo&&dbackHour>=dbackFrom&&abackHour<abackTo&&abackHour>=abackFrom)
	    	{
	    		result.add(allPlans.get(i));
	    	}
	    }
		
		return result;
		}

}
