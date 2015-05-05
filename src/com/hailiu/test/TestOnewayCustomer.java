package com.hailiu.test;

import java.util.ArrayList;
import java.util.Collections;

import com.team2.model.customer.OnewayCustomer;
import com.team2.model.ticket.OnewayTicketPlan;

public class TestOnewayCustomer {
	public static void main(String[] args){
		OnewayCustomer onewayCustomer = new OnewayCustomer("SFO", "JFK", "2015_5_11","firstClass");
		ArrayList<OnewayTicketPlan> allPlans = onewayCustomer.searchFlight();

		Collections.sort(allPlans, OnewayTicketPlan.BY_TOTAL_TIME);
		for (OnewayTicketPlan plan : allPlans){
			System.out.println(plan);
			
	   }
	}

}
