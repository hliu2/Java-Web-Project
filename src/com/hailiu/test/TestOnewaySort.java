package com.hailiu.test;

import java.util.ArrayList;



import java.util.Date;

import com.team2.model.customer.OnewayCustomer;
import com.team2.model.ticket.OnewayTicketPlan;
import com.team2.model.customer.OnewaySort;

public class TestOnewaySort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OnewayCustomer onewayCustomer = new OnewayCustomer("SFO", "JFK", "2015_5_14","firstClass");
		ArrayList<OnewayTicketPlan> allPlans = onewayCustomer.searchFlight();
		ArrayList<OnewayTicketPlan> result = OnewaySort.sortByTotalPrice(allPlans);
		result =OnewaySort.selectStopovers(result, 2);
		ArrayList<OnewayTicketPlan> resultTimeWindow=OnewaySort.sortByTimeWindow(allPlans, 8, 24,10,24);
		for (OnewayTicketPlan plan : result){
			System.out.println(plan);
			System.out.println(plan.getTotalPrice());
			System.out.println(plan.getDepartureNumOfStopovers());
		
	}
		for (OnewayTicketPlan plan : resultTimeWindow){
			System.out.println(plan);
			System.out.println(plan.getDeparturePlan().get(0).getDepartureTime());
			int size=plan.getDeparturePlan().size()-1;
	    	
	        Date arrivaltime=plan.getDeparturePlan().get(size).getArrivalTime();
	        System.out.println(arrivaltime);
	}

}

	
}