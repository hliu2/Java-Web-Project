package com.team2.model.ticket;

import java.util.ArrayList;

import com.team2.model.Flight;

public abstract class TicketPlan {
	protected ArrayList<Flight> departurePlan;
	protected float totalPrice;
	protected int departureNumOfStopovers;
	protected int departureFlightTime;
	protected int departureTotalTime;
	protected String ticketType;
	protected ArrayList<Integer> departureLayoverTimes = new ArrayList<Integer>() ;
	public ArrayList<Flight> getDeparturePlan() {
		return departurePlan;
	}
	public void setDeparturePlan(ArrayList<Flight> departurePlan) {
		this.departurePlan = departurePlan;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getDepartureNumOfStopovers() {
		return departureNumOfStopovers;
	}
	public void setDepartureNumOfStopovers(int departureNumOfStopovers) {
		this.departureNumOfStopovers = departureNumOfStopovers;
	}
	public int getDepartureFlightTime() {
		return departureFlightTime;
	}
	public void setDepartureFlightTime(int departureFlightTime) {
		this.departureFlightTime = departureFlightTime;
	}
	public int getDepartureTotalTime() {
		return departureTotalTime;
	}
	public void setDepartureTotalTime(int departureTotalTime) {
		this.departureTotalTime = departureTotalTime;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public ArrayList<Integer> getDepartureLayoverTimes() {
		return departureLayoverTimes;
	}
	public void setDepartureLayoverTimes(ArrayList<Integer> departureLayoverTimes) {
		this.departureLayoverTimes = departureLayoverTimes;
	}
    
	
}
