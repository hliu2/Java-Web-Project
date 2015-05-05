package com.hailiu.test;

import java.util.ArrayList;

import com.team2.model.customer.TwowayCustomer;
import com.team2.model.ticket.TwowayTicketPlan;

public class TestTwowayCustomer {
	public static void main(String[] args) {
		TwowayCustomer customer = new TwowayCustomer("JFK", "SFO", "2015_5_10",
				"2015_5_11", "firstClass");
		ArrayList<TwowayTicketPlan> plans = customer.searchFlight();
		for (TwowayTicketPlan plan : plans) {
			System.out.println(plan);
		}
	}

}
