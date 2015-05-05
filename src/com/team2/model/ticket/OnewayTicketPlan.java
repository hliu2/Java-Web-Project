package com.team2.model.ticket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import com.team2.model.Flight;

public class OnewayTicketPlan extends TicketPlan {
	public static final Comparator<OnewayTicketPlan> BY_FLIGHT_TIME = new ByFlight_Time();
	public static final Comparator<OnewayTicketPlan> BY_TOTAL_TIME = new ByTotal_Time();
	public static final Comparator<OnewayTicketPlan> BY_PRICE = new ByPrice();
	
	public static class ByTotal_Time implements Comparator<OnewayTicketPlan>{
		public int compare(OnewayTicketPlan a, OnewayTicketPlan b){
			Integer integer_a = new Integer(a.getDepartureTotalTime());
			Integer integer_b = new Integer(b.getDepartureTotalTime());
			return integer_a.compareTo(integer_b);
		}
	}
	
	public static class ByPrice implements Comparator<OnewayTicketPlan> {
		public int compare(OnewayTicketPlan a, OnewayTicketPlan b){
			Float float_a = new Float(a.totalPrice);
			Float float_b = new Float(b.totalPrice);
			return float_a.compareTo(float_b);
		}
	}
	
	public static class ByFlight_Time implements Comparator<OnewayTicketPlan> {
		public int compare(OnewayTicketPlan a, OnewayTicketPlan b){
			Integer integer_a = new Integer(a.departureFlightTime);
			Integer integer_b = new Integer(b.departureFlightTime);
			return integer_a.compareTo(integer_b);

		}
	}
	

	public void setDepartureLayoverTimes() {
		int numOfFlights = this.departurePlan.size();
		if (numOfFlights == 1) this.departureLayoverTimes.add(0);
		if (numOfFlights >1){
			Iterator<Flight> it = this.departurePlan.iterator();
			Date currentDepartureTime;
			Date currentArrivalTime = it.next().getArrivalTime();
			while (it.hasNext()) {
				Flight currentFlight = it.next();
				currentDepartureTime = currentFlight.getDepartureTime();
				int layoverTime = (int) (currentDepartureTime.getTime()-currentArrivalTime.getTime())/(60*1000);
				this.departureLayoverTimes.add(layoverTime);
				currentArrivalTime = currentFlight.getArrivalTime();
			}
			
		}
	}
	
	public ArrayList<Integer> getDepartureLayoverTimes(){
		return this.departureLayoverTimes;
		
	}
	
	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public ArrayList<Flight> getDeparturePlan() {
		return departurePlan;
	}

	public void setDeparturePlan(ArrayList<Flight> plan) {
		this.departurePlan = plan;
	}
	

	public float getTotalPrice() {
		return totalPrice;
	}

	private void setTotalPrice() {
		if (ticketType.equals("firstClass")){
			for(Flight f :this.departurePlan){
				this.totalPrice += f.getFirstClassPrice();
			}
		}
		if (ticketType.equals("coach")){
			for(Flight f :this.departurePlan){
				this.totalPrice += f.getCoachPrice();
			}
		} 
		
	}

	public int getDepartureNumOfStopovers() {
		return departureNumOfStopovers;
	}

	private void setDepartureNumOfStopovers() {
		this.departureNumOfStopovers = this.departurePlan.size() - 1;
	}

	public int getDepartureFlightTime() {
		return departureFlightTime;
	}

	private void setDepartureFlightTime() {
		for (Flight f : this.departurePlan){
			this.departureFlightTime +=f.getFlightTime();
		}
		
	}

	public int getDepartureTotalTime() {
		return departureTotalTime;
	}

	private void setDepartureTotalTime() {
		int size = this.departurePlan.size();
		int totalTime;
		if (size == 0) ;
		if (size == 1) {
			Flight flight = this.departurePlan.get(0);
			Long diffInMillies = flight.getArrivalTime().getTime() - flight.getDepartureTime().getTime();
			totalTime = (int) (diffInMillies/(60*1000)) ;
			this.departureTotalTime = totalTime;
			
		}
		if (size > 1) {
			Flight firstFlight = departurePlan.get(0);
			Flight lastFlight = departurePlan.get(size-1);
			long diffInMillies = lastFlight.getArrivalTime().getTime() - firstFlight.getDepartureTime().getTime();
			totalTime = (int) (diffInMillies/(1000*60));
			this.departureTotalTime = totalTime;
		}
	}

	

	public OnewayTicketPlan(ArrayList<Flight> plan, String ticketType){
		this.setDeparturePlan(plan);
		this.setTicketType(ticketType);
		this.setDepartureFlightTime();
		this.setDepartureNumOfStopovers();
		this.setTotalPrice();
		this.setDepartureTotalTime();
		this.setDepartureLayoverTimes();
		
		
	}
	
	public String toString() {
		String result = new String();
		for (Flight f : this.departurePlan){
			 result +=("Flight Number: " + f.getFlightNumber() +" Departure Airport Code: "+ f.getDepatureAirport().getCode() + 
					 " Arrival Airprot code: "+ f.getArrivalAirport().getCode()
					 + " Departure Time: " + f.getDepartureTime()  + " Arrival Time" + f.getArrivalTime() + "\n");
		}
		String layoversTimes = "layover times: [";
		ArrayList<Integer> layoverstimes = this.getDepartureLayoverTimes();
		for (Integer i : layoverstimes){
			layoversTimes += i.toString() + " ";
			
		}
		layoversTimes += "]";
		return result + layoversTimes + "\n";
	}

	
}

