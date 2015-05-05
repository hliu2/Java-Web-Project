package com.team2.model.customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.team2.model.TimeManagement;
import com.team2.model.ticket.OnewayTicketPlan;


/**
 * @author Naihui Wang
 *
 */

/**
 * @author Administrator
 *
 */
public class OnewaySort {

/**
 * @param f an arraylist of OnewayTicketPlan
 * @param stopOver valid value is 123
 * @return an arraylist of OnewayTicketPlan which contains flights with certain stopover
 */
public static ArrayList<OnewayTicketPlan> selectStopovers(ArrayList<OnewayTicketPlan> f,int stopOver)
{   
	ArrayList<OnewayTicketPlan> allPlans = f;
	ArrayList<OnewayTicketPlan> result=new ArrayList<OnewayTicketPlan>();
    int stop=stopOver;
    if(stop<0&&stop>2)
    {
    	throw new IllegalArgumentException();
    }
    for(OnewayTicketPlan i:allPlans)
    {
    	if(i.getDepartureNumOfStopovers()==stop)
    	{
    		result.add(i);
    	}
    
    }
	
	return result;
	}

/**
 * @param f an arraylist of OnewayTicketPlan
 * @return  an arraylist of OnewayTicketPlan sorted by total price of each ticket plan
 */
public static ArrayList<OnewayTicketPlan> sortByTotalPrice(ArrayList<OnewayTicketPlan> f)
{
	ArrayList<OnewayTicketPlan> allPlans = f;

	Collections.sort(allPlans, OnewayTicketPlan.BY_PRICE );
	return allPlans;
	
	}

/**
 * @param f an arraylist of OnewayTicketPlan
 * @return an arraylist of OnewayTicketPlan sorted by total time of each ticket plan
 */
public static ArrayList<OnewayTicketPlan> sortByTotalTime(ArrayList<OnewayTicketPlan> f)
{
	ArrayList<OnewayTicketPlan> allPlans = f;

	Collections.sort(allPlans, OnewayTicketPlan.BY_TOTAL_TIME);
	return allPlans;
	}

/**
 * @param f arraylist of OnewayTicketPlan
 * @return an arraylist of OnewayTicketPlan sorted by flight time of each ticket plan
 */
public static ArrayList<OnewayTicketPlan> sortByFlightTime(ArrayList<OnewayTicketPlan> f)
{
	ArrayList<OnewayTicketPlan> allPlans = f;

	Collections.sort(allPlans, OnewayTicketPlan.BY_FLIGHT_TIME);
	return allPlans;
	}

/**
 * @param f an arraylist of OnewayTicketPlan 
 * @param dfrom flight departure time window start hour
 * @param dto   flight departure time window end hour
 * @param afrom flight arrival time window start hour
 * @param ato   flight arrival time window end hour
 * @return an  arraylist of flight plan of which will departure between the departure time window and arrive between the arrival time window
 */
public static ArrayList<OnewayTicketPlan> sortByTimeWindow(ArrayList<OnewayTicketPlan> f,int dfrom, int dto,int afrom, int ato)
{
	ArrayList<OnewayTicketPlan> allPlans = f;
	ArrayList<OnewayTicketPlan> result=new ArrayList<OnewayTicketPlan>();
	
    for(int i=0;i<allPlans.size();i++)
    {   
    	int size=allPlans.get(i).getDeparturePlan().size()-1;
    	Date departuretime=allPlans.get(i).getDeparturePlan().get(0).getDepartureTime();
    	String depaturecode = allPlans.get(i).getDeparturePlan().get(0).getDepatureAirport().getCode();
    	Date departurelocaltime=TimeManagement.gmtLocal(depaturecode,departuretime);
    	
        Date arrivaltime=allPlans.get(i).getDeparturePlan().get(size).getArrivalTime();
        String arrivalcode=allPlans.get(i).getDeparturePlan().get(size).getArrivalAirport().getCode();
        Date arrivallocaltime=TimeManagement.gmtLocal(arrivalcode,arrivaltime);
        @SuppressWarnings("deprecation")
		int dHour=departurelocaltime.getHours();
        @SuppressWarnings("deprecation")
		int aHour=arrivallocaltime.getHours();
    	if(dHour>= dfrom&&dHour<dto&&aHour>=afrom&&aHour<ato)
    	{
    		result.add(allPlans.get(i));
    	}
    }

	return result;
	}

}
