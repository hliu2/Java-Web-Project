package com.team2.model.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import com.team2.model.Flight;
import com.team2.model.TimeManagement;
import com.team2.model.ticket.OnewayTicketPlan;
import com.team2.model.ticket.TwowayTicketPlan;

/**
 * Two way customer is responsible for searching round-trip
 */
public class TwowayCustomer {
	

	private String arrivalDay; 
	private String departureCode;
	private String arrivalCode;
	private String ticketType;
	private String departureDay;
	/**
	 * @param arrivalCode client's arrival airport code
	 * @param departureCode client's departure airport code
	 * @param departureDay client's departure day, should be in "year_month_day"
	 * @param arrivalDay client's arrival day, should be in "year_month_day"
	 * @param ticketType should be either "firstClass" or "coach"
	 */
	public TwowayCustomer(String departureCode, String arrivalCode, String departureDay, String arrivalDay, String ticketType){
		this.arrivalDay = arrivalDay;
		this.departureDay = departureDay;
		this.ticketType = ticketType;
		this.arrivalCode = arrivalCode;
		this.departureCode = departureCode;
	}
	
	
    public ArrayList<TwowayTicketPlan> searchFlight() {
		String[] stringTime1 = departureDay.split("_");
		int year1 = Integer.parseInt(stringTime1[0]);
		int month1 = Integer.parseInt(stringTime1[1]);
		int day1 = Integer.parseInt(stringTime1[2])+1;
		String departureCandidateTime = year1 +"_"+ month1 + "_" + day1;
		
		
		String[] stringTime = arrivalDay.split("_");
		int year2 = Integer.parseInt(stringTime[0]);
		int month2 = Integer.parseInt(stringTime[1]);
		int day2 = Integer.parseInt(stringTime[2])+1;
		String arrivalCandidateTime = year2 +"_"+ month2 + "_" + day2;
		
		// To search a round trip, this amounts to search two one way trip, and combine all these plans, we use four thread to run in parallel.  
		CountDownLatch endSigle = new CountDownLatch(4); 
		SearchHelper helper1 = new SearchHelper(departureDay, departureDay, departureCode, arrivalCode, ticketType, endSigle );
		SearchHelper helper2 = new SearchHelper(departureCandidateTime, departureDay, departureCode, arrivalCode, ticketType, endSigle);
		SearchHelper helper3 = new SearchHelper(arrivalDay, arrivalDay, arrivalCode, departureCode,  ticketType, endSigle );
		SearchHelper helper4 = new SearchHelper(arrivalCandidateTime, arrivalDay,  arrivalCode,departureCode, ticketType, endSigle);
		
    	ThreadFactory factory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread s = Executors.defaultThreadFactory().newThread(r);
                s.setDaemon(true);
                return s;
            }
        };
        
    	ExecutorService exec = Executors.newCachedThreadPool(factory); 

    	Future<ArrayList<OnewayTicketPlan>> future1 = exec.submit(helper1);
    	Future<ArrayList<OnewayTicketPlan>> future2 = exec.submit(helper2);
    	Future<ArrayList<OnewayTicketPlan>> future3 = exec.submit(helper3);
    	Future<ArrayList<OnewayTicketPlan>> future4 = exec.submit(helper4);
    	
    	// must wait this four thread to complete, then continue to execute main tread. 
    	try {
			endSigle.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	ArrayList<OnewayTicketPlan> departurePlans1 = new ArrayList<OnewayTicketPlan>();
		try {
			departurePlans1 = future1.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ArrayList<OnewayTicketPlan> departurePlans2 = new ArrayList<OnewayTicketPlan>() ;
		try {
			departurePlans2 = future2.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ArrayList<OnewayTicketPlan> arrivalPlans1 = new ArrayList<OnewayTicketPlan>() ;
		try {
			arrivalPlans1 = future3.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ArrayList<OnewayTicketPlan> arrivalPlans2 = new ArrayList<OnewayTicketPlan>() ;
		try {
			arrivalPlans2 = future4.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayList<OnewayTicketPlan> allDeparturePlans = new ArrayList<OnewayTicketPlan>();
		allDeparturePlans.addAll(departurePlans1);
		allDeparturePlans.addAll(departurePlans2);

		ArrayList<OnewayTicketPlan> allArrivalPlans = new ArrayList<OnewayTicketPlan>();
		allArrivalPlans.addAll(arrivalPlans1);
		allArrivalPlans.addAll(arrivalPlans2);
	   
		/* considering the boundary situation, which one customer may departure and return back at the same day, the back trips's take off time must be 2 hours later
		than departure trip's landing time. Otherwise this is a stupid round-trip plan. */ 
	    ArrayList<TwowayTicketPlan> plans = new ArrayList<TwowayTicketPlan>();
	    for (OnewayTicketPlan arrivalPlan : allArrivalPlans) {
		    if (arrivalPlan.getDeparturePlan().size() == 0) continue;
		    for (OnewayTicketPlan departurePlan : allDeparturePlans ) {
			   /* as long as departure plan's arrival time is no later than customer's return day and arrival plan's departure time,
			    * this combination of departure plan and arrival plan maybe a valid round-trip plan.   
			    */
			   int size = departurePlan.getDeparturePlan().size();
			   if(size == 0) continue;
			   Flight departureLastFlight = departurePlan.getDeparturePlan().get( size - 1);
			   Flight arrivalFirstFlight = arrivalPlan.getDeparturePlan().get(0);
			   Date arrivalDate = departureLastFlight.getArrivalTime();
			   Date departureDate = arrivalFirstFlight.getDepartureTime();
			   Date localDate = TimeManagement.gmtLocal(departureLastFlight.getArrivalAirport().getCode(), arrivalDate);
			   Calendar arrivalCal = Calendar.getInstance();
			   arrivalCal.setTime(arrivalDate);
			   arrivalCal.add(Calendar.HOUR, 2);
			   Calendar departureCal = Calendar.getInstance();
			   departureCal.setTime(departureDate);
			   String[] string = this.arrivalDay.split("_");
			   int day = Integer.parseInt(string[2]);
			   @SuppressWarnings("deprecation")
			   int localDay = localDate.getDay();
			   if ( (localDay <= day) && (arrivalCal.before(departureCal)) ){
			   TwowayTicketPlan twowayTicketPlan = new TwowayTicketPlan(departurePlan.getDeparturePlan(), arrivalPlan.getDeparturePlan(), this.ticketType);
			   plans.add(twowayTicketPlan);
			   }
		   }
	   }
	   return plans;
   }
	    	
}

