package com.team2.model;

/**
 * @author Qiuzhe Ma 
 * @telephone 7744629288
 * @description responsible for generating flight data as a parameter of buyTicket method 
 */
public class FlightData {
	
	/**
	 * @param flightsNumber a array of flight number which represents multiple flight 
	 * @param ticketType ticket type travel want to buy, either first class or coach 
	 * @return a XML string of multiple flight data 
	 * @description responsible for generating multiple fight data 
	 */
	public String generatorFactory(String[] flightsNumber, String ticketType) {
		String result = "";
		for (String flightNumber: flightsNumber){
			result += this.addFlight(flightNumber, ticketType);
		}
		return "<Flights>" + result + "</Flights>";
		
	}
	
	/**
	 * @param flightNumber one flight number which represents only one flight 
	 * @param ticketType ticket type travel want to buy, either first class or coach
	 * @return a XML string of only one flight data 
	 * @description responsible for generating only one fight data 
	 */
	public String generatorFactory(String flightNumber, String ticketType){
		return "<Flights>"+this.addFlight(flightNumber, ticketType) + "</Flights>";
	}
	
	/**
	 * @param flightNumber 
	 * @param ticketType
	 * @description private method can only called by this class 
	 * @return a XML string representing one flight information entry
	 */
	private String addFlight(String flightNumber, String ticketType){
		return "<Flight number=\"" + flightNumber + "\" seating=\"" + ticketType+"\"/>";
		
	}

}
