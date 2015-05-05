package com.hailiu.test;

import com.team2.model.*;

public class TestFlightData {
	public static void main(String[] args){
		FlightData xmlStringFlight = new FlightData();
		String flightData = xmlStringFlight.generatorFactory("12262","FirstClass");
		System.out.println(flightData);	
		
		String[] flights = {"12000","12001"};
		String flightsData =  xmlStringFlight.generatorFactory(flights, "FirstClass"); 
		System.out.println(flightsData);
	}

}
