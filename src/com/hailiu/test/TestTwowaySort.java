package com.hailiu.test;

import java.util.ArrayList;
import java.util.Date;

import com.team2.model.customer.TwowayCustomer;
import com.team2.model.customer.TwowaySort;
import com.team2.model.ticket.TwowayTicketPlan;

public class TestTwowaySort {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwowayCustomer twowayCustomer = new TwowayCustomer("SFO", "JFK", "2015_5_10","2015_5_11","firstClass");
		ArrayList<TwowayTicketPlan> allPlans = twowayCustomer.searchFlight();
		ArrayList<TwowayTicketPlan> result = TwowaySort.sortByTotalPrice(allPlans);
		result =TwowaySort.selectStopovers(result, 1,1);
		
		for (TwowayTicketPlan plan : result){
			System.out.println(plan);
			System.out.println(plan.getTotalPrice());
			System.out.println(plan.getDepartureNumOfStopovers());
			System.out.println(plan.getArrivalNumOfStopovers());
	}
		result =TwowaySort.sortByFlightTime(result);
		for (TwowayTicketPlan plan : result){
			System.out.println(plan);
			System.out.println(plan.getArrivalFlightTime());
			;
	}
		ArrayList<TwowayTicketPlan> resultTimeWindow=TwowaySort.sortByTimeWindow(allPlans, 4, 20, 7,20,10,24,10,24);
		for (TwowayTicketPlan plan : resultTimeWindow){
			System.out.println(plan);
			System.out.println(plan.getArrivalPlan().get(0).getDepartureTime().getHours());
			System.out.println(plan.getDeparturePlan().get(0).getDepartureTime());
            int tosize=plan.getDeparturePlan().size()-1;
            int backsize=plan.getArrivalPlan().size()-1;
	        Date toarrivaltime=plan.getDeparturePlan().get(tosize).getArrivalTime();
	        System.out.println("toarrivaltime"+toarrivaltime);
	        Date backavtime=plan.getArrivalPlan().get(backsize).getArrivalTime();
	        System.out.println("backavtime"+backavtime);
	}
}
}