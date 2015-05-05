package com.team2.model;

import java.util.Date;

/**
 * @author Qiuzhe Ma 
 * @description contains all information about flight 
 */
public class Flight {
	private Airplane airplane;
	private Airport depatureAirport;
	private Airport arrivalAirport;
	private Date departureTime; 
	private Date arrivalTime;
	private String flightNumber;
	private int flightTime;
	private float firstClassPrice;
	private float coachPrice;
	private int firstClassSeats; // number of first class seats available   
	private int coachSeats;  // number of coach seats available 
	
	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public Airport getDepatureAirport() {
		return depatureAirport;
	}

	public void setDepatureAirport(Airport depatureAirport) {
		this.depatureAirport = depatureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public float getFirstClassPrice() {
		return firstClassPrice;
	}

	public void setFirstClassPrice(float firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	public float getCoachPrice() {
		return coachPrice;
	}

	public void setCoachPrice(float coachPrice) {
		this.coachPrice = coachPrice;
	}

	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	public int getCoachSeats() {
		return coachSeats;
	}

	public void setCoachSeats(int coachSeats) {
		this.coachSeats = coachSeats;
	}


	


	
    // override toString method to help printing results for debugging 
    @SuppressWarnings("deprecation")
	public String toString(){
    	return airplane.toString() + " DepartureAirport" + depatureAirport.toString() + " ArrivalAiport"+ arrivalAirport.toString() +
    			" DepartureTime \"" + departureTime.toGMTString() + "\" ArrivalTime \"" + arrivalTime.toGMTString() + "\" FlightNumber \""+ flightNumber + 
    			"\" flightTime " + flightTime + " FirstClassPrice " + firstClassPrice+ " CoachPrice " + coachPrice + " FirstClassSeats " + 
    			firstClassSeats + " CoachSeats " + coachSeats;
    			
    }
	
}
