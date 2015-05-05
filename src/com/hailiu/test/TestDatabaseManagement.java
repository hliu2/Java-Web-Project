package com.hailiu.test;

import com.team2.model.*;
public class TestDatabaseManagement {
	public static void main(String[] args){
		DatabaseManagement databaseManagement = DatabaseManagement.getInstance();
		String arrivalflights = databaseManagement.getArivalFlights("JFK", "2015_05_17");
		String departureFlights = databaseManagement.getDepartureFlights("JFK", "2015_05_17");
		String airplanes = databaseManagement.getAirplanes();
		String airports = databaseManagement.getAirports(); 
		
		System.out.println(arrivalflights);
		System.out.println(departureFlights);
		System.out.println(airplanes);
		System.out.println(airports);
		
		FlightData xmlStringFlight = new FlightData();
		String flightData = xmlStringFlight.generatorFactory("12262","FirstClass");
		System.out.println(databaseManagement.buyTickets(flightData));	
		
		String[] flights = {"12215","12216"};
		String flightsData =  xmlStringFlight.generatorFactory(flights, "FirstClass"); 
		System.out.println(databaseManagement.buyTickets(flightsData));
		

	}

}
