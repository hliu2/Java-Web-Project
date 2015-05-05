package com.team2.model.customer;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import com.team2.model.ticket.OnewayTicketPlan;

/**
 * Oneway Customer is responsible for search oneway trip
 *
 */
public class OnewayCustomer {
	private String departureCode;
	private String arrivalCode;
	private String ticketType;
	private String departureDay;
	
	public OnewayCustomer(String departureCode, String arrivalCode, String departureDay, String ticketType){
		this.arrivalCode = arrivalCode;
		this.ticketType = ticketType;
		this.departureCode = departureCode;
		this.departureDay = departureDay;
	}
	
	/**
	 * To search the trips of specified day and arrival, departure airports, due to local time is always later than GMT time, 
	 * we should actually search today and tomorrow's all qualified flights. We implements it by using two thread, and we should
	 * must wait this two threads to complete, before the main thread combines all these plans.   
	 */
	public ArrayList<OnewayTicketPlan> searchFlight(){
		
		String[] stringTime = departureDay.split("_");
		int year = Integer.parseInt(stringTime[0]);
		int month = Integer.parseInt(stringTime[1]);
		int day = Integer.parseInt(stringTime[2])+1;
		String candidateTime = year +"_"+ month + "_" + day;
		CountDownLatch endSigle = new CountDownLatch(2); 
		SearchHelper helper1 = new SearchHelper(departureDay, departureDay, departureCode, arrivalCode, ticketType, endSigle );
		SearchHelper helper2 = new SearchHelper(candidateTime, departureDay, departureCode, arrivalCode, ticketType, endSigle);
		
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
    	try {
			endSigle.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	ArrayList<OnewayTicketPlan> plans1 = new ArrayList<OnewayTicketPlan>();
		try {
			plans1 = future1.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ArrayList<OnewayTicketPlan> plans2 = new ArrayList<OnewayTicketPlan>() ;
		try {
			plans2 = future2.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<OnewayTicketPlan> allPlans = new ArrayList<OnewayTicketPlan>();
		allPlans.addAll(plans1);
		allPlans.addAll(plans2);
		return allPlans;
	}


}


