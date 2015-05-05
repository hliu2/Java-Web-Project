package com.team2.model.ticket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import com.team2.model.Flight;


public class TwowayTicketPlan extends TicketPlan {
	public static final Comparator<TwowayTicketPlan> BY_FLIGHT_TIME = new ByFlight_Time();
	public static final Comparator<TwowayTicketPlan> BY_TOTAL_TIME = new ByTotal_Time();
	public static final Comparator<TwowayTicketPlan> BY_PRICE = new ByPrice();
	
	private ArrayList<Flight> arrivalPlan;
	private int arrivalNumOfStopovers;
	private int arrivalFlightTime;
	private int arrivalTotalTime;
	private ArrayList<Integer> arrivalLayoverTimes = new ArrayList<Integer>();
	
	public static class ByTotal_Time implements Comparator<TwowayTicketPlan>{
		public int compare(TwowayTicketPlan a, TwowayTicketPlan b){
			Integer integer_a = new Integer(a.arrivalTotalTime + a.departureTotalTime);
			Integer integer_b = new Integer(b.arrivalTotalTime + b.departureTotalTime);
			return integer_a.compareTo(integer_b);
		}
	}
	
	public static class ByPrice implements Comparator<TwowayTicketPlan> {
		public int compare(TwowayTicketPlan a, TwowayTicketPlan b){
			Float float_a = new Float(a.totalPrice);
			Float float_b = new Float(b.totalPrice);
			return float_a.compareTo(float_b);
		}
	}
	
	public static class ByFlight_Time implements Comparator<TwowayTicketPlan> {
		public int compare(TwowayTicketPlan a, TwowayTicketPlan b){
			Integer integer_a = new Integer(a.departureFlightTime + a.arrivalFlightTime);
			Integer integer_b = new Integer(b.departureFlightTime + a.arrivalFlightTime);
			return integer_a.compareTo(integer_b);

		}
	}
	
	public void setDepartureLayoverTimes() {
		if (this.departurePlan.size() == 0);
		if (this.departurePlan.size() == 1) this.departureLayoverTimes.add(0);
		else {
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
	
	public ArrayList<Integer> getDepatureLayoverTimes(){
		return this.departureLayoverTimes;
		
	}
	
	public void setArrivalLayoverTimes() {
		if (this.arrivalPlan.size() == 0);
		if (this.arrivalPlan.size() == 1) this.arrivalLayoverTimes.add(0);
		else {
			Iterator<Flight> it = this.arrivalPlan.iterator();
			Date currentDepartureTime;
			Date currentArrivalTime = it.next().getArrivalTime();
			while (it.hasNext()) {
				Flight currentFlight = it.next();
				currentDepartureTime = currentFlight.getDepartureTime();
				int layoverTime = (int) (currentDepartureTime.getTime()-currentArrivalTime.getTime())/(60*1000);
				this.arrivalLayoverTimes.add(layoverTime);
				currentArrivalTime = currentFlight.getArrivalTime();
			}
			
		}
	}
	
	public ArrayList<Integer> getArrivalLayoverTimes(){
		return this.arrivalLayoverTimes;
		
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
	
	public ArrayList<Flight> getArrivalPlan() {
		return arrivalPlan;
	}

	private void setArrivalPlan(ArrayList<Flight> plan) {
		this.arrivalPlan = plan;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	private void setTotalPrice() {
		if (ticketType.equals("firstClass")){
			for(Flight f :this.departurePlan){
				this.totalPrice += f.getFirstClassPrice();
			}
			for(Flight f :this.arrivalPlan){
				this.totalPrice += f.getFirstClassPrice();
			}
		}
		if (ticketType.equals("coach")){
			for(Flight f :this.departurePlan){
				this.totalPrice += f.getCoachPrice();
			}
			for(Flight f :this.arrivalPlan){
				this.totalPrice += f.getCoachPrice();
			}
		} 
		
	}

	public int getDepartureNumOfStopovers() {
		return departureNumOfStopovers;
	}

	private void setDepartureNumOfStopovers() {
		this.departureNumOfStopovers = this.departurePlan.size() - 1 ;
	}
	
	public int getArrivalNumOfStopovers() {
		return arrivalNumOfStopovers;
	}

	private void setArrivalNumOfStopovers() {
		this.arrivalNumOfStopovers = this.arrivalPlan.size() - 1;
	}

	public int getDepartureFlightTime() {
		return departureFlightTime;
	}

	private void setDepartureFlightTime() {
		for (Flight f : this.departurePlan){
			this.departureFlightTime +=f.getFlightTime();
		}	
	}
	
	public int getArrivalFlightTime() {
		return arrivalFlightTime;
	}

	private void setArrivalFlightTime() {
		for (Flight f : this.arrivalPlan){
			this.arrivalFlightTime +=f.getFlightTime();
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
	
	public int getArrivalTotalTime() {
		return arrivalTotalTime;
	}
	
	private void setArrivalTotalTime() {
		int size = this.arrivalPlan.size();
		int totalTime;
		if (size == 0) ;
		if (size == 1) {
			Flight flight = this.arrivalPlan.get(0);
			Long diffInMillies = flight.getArrivalTime().getTime() - flight.getDepartureTime().getTime();
			totalTime = (int) (diffInMillies/(60*1000)) ;
			this.arrivalTotalTime = totalTime;
			
		}
		if (size > 1) {
			Flight firstFlight = arrivalPlan.get(0);
			Flight lastFlight = arrivalPlan.get(size-1);
			long diffInMillies = lastFlight.getArrivalTime().getTime() - firstFlight.getDepartureTime().getTime();
			totalTime = (int) (diffInMillies/(1000*60));
			this.arrivalTotalTime = totalTime;
		}
	}

	

	public TwowayTicketPlan(ArrayList<Flight> departurePlan, ArrayList<Flight> arrivalPlan , String ticketType){
		this.setArrivalPlan(arrivalPlan);
		this.setDeparturePlan(departurePlan);
		this.setTicketType(ticketType);
		this.setDepartureFlightTime();
		this.setArrivalFlightTime();
		this.setDepartureNumOfStopovers();
		this.setArrivalNumOfStopovers();
		this.setTotalPrice();
		this.setDepartureTotalTime();
		this.setArrivalTotalTime();
		this.setDepartureLayoverTimes();
		this.setArrivalLayoverTimes();
	}
	
	public String toString() {
		String result = new String();
		for (Flight f : this.departurePlan){
			 result +=(f.toString() + "\t");
		}
		result += "\n";
		for (Flight f : this.arrivalPlan){
			 result +=(f.toString() + "\t");
		}
		result += "\n";
		return result;
	}

	

}
