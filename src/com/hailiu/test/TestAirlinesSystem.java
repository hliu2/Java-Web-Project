package com.hailiu.test;

import java.util.*;

import com.team2.model.*;

public class TestAirlinesSystem {
	public static void main(String[] args){
		
		HashMap<String,Airport> airports = new HashMap<String,Airport>();
		airports = AirlinesSystem.parseAirports();
		for(String key:airports.keySet()) {
			System.out.println(airports.get(key));
			
		}

		
		HashMap<String,Airplane> airplanes = new HashMap<String,Airplane>();
		airplanes = AirlinesSystem.parseAirplanes();
		for(String key:airplanes.keySet()) {
			System.out.println(airplanes.get(key));
			
		}
		
		
		ArrayList<Flight> arivalFlights = AirlinesSystem.queryArrivalFlight("JFK", "2015_05_17");
		for(Flight flight:arivalFlights)
		System.out.println(flight);
		
		ArrayList<Flight> departureFlights = AirlinesSystem.queryDepartureFlight("JFK", "2015_05_17");
		for(Flight flight:departureFlights)
		System.out.println(flight);
		
		HashMap<String,Airport> airport1 = new HashMap<String,Airport>();
		airport1 = AirlinesSystem.parseAirports();
		for(String key:airport1.keySet()) {
			System.out.println(airports.get(key).getCode()+"\t"+(airports.get(key).getName()));
			
		}
		
	}

}
