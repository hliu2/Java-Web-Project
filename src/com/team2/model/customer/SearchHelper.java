package com.team2.model.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.team2.model.AirlinesSystem;
import com.team2.model.Airport;
import com.team2.model.Flight;
import com.team2.model.TimeManagement;
import com.team2.model.ticket.OnewayTicketPlan;

/**
 * A utility class which can help search flight for both one way and round trip, this class implements
 * Callable interface, and can be run in parallel 
 *
 */
public class SearchHelper implements Callable<ArrayList<OnewayTicketPlan>>  {
	private String departureTime;    // the day that you want to query the database.
	private String departureCode;
	private String arrivalCode;
	private String ticketType;
	private String realTime;  // customer's departure day, receive from GUI. 
	private CountDownLatch endSigle; // let this search helper thread must be completed before main thread can continue to execute the rest of operations 
	
	public SearchHelper(String departureTime, String realTime, String departureCode, String arrivalCode, String ticketType, CountDownLatch endSigle){
		this.departureTime = departureTime;
		this.departureCode = departureCode;
		this.arrivalCode = arrivalCode;
		this.ticketType = ticketType;
		this.realTime = realTime;
		this.endSigle = endSigle;
	}
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public ArrayList<OnewayTicketPlan> call() throws Exception {
		ArrayList<OnewayTicketPlan> plans =  addAllPlan();
		endSigle.countDown();
		return plans;
	}
	
	
    /**
     * for the given departure day (GMT day), arrival airport and departure airport, find all tickets plans with stop overs 0, 1 and 2.  
     */
    private ArrayList<OnewayTicketPlan> addAllPlan() {
    	
    	ArrayList<OnewayTicketPlan> allPlans =  new ArrayList<OnewayTicketPlan>();
    	
    	// figure out the customer's flight direction, positive value means east, otherwise west. 
    	HashMap<String, Airport> hashmap = AirlinesSystem.parseAirports();
    	Airport arrivalAirport = hashmap.get(arrivalCode);
    	Airport departureAirport = hashmap.get(departureCode);
    	float customerDirection = arrivalAirport.getLongitude() - departureAirport.getLongitude();
    	
    	
    	ArrayList<Flight> flights1 = AirlinesSystem.queryDepartureFlight(departureCode, departureTime);
    	ArrayList<Flight> oneFlights; 
    	ArrayList<Flight> twoFlights;
    	ArrayList<Flight> threeFlights;
    	for(Flight flight1 : flights1) {
			Date date = TimeManagement.gmtLocal(departureCode, flight1.getDepartureTime());
			String localDepartureDay = changeTimeToDay(date);
			
			// if this flight's ticket is full or departure day (changed to local time) doesn't matches users' departure day  or the flight's direction is wrong, ignore this flight
			if ( (localDepartureDay.equals(realTime))== false || isFull(flight1, ticketType) == true || checkDirection(flight1, customerDirection) == false) continue; 
			
			/* if both this flight's ticket is available and departure day matches, check whether arrival code matches, 
			 * if match find a non-stop plan, else initiate a another for loop */
			if (flight1.getArrivalAirport().getCode().equals(arrivalCode)){
				oneFlights = new ArrayList<Flight>();
				addOnePlan(flight1, oneFlights, allPlans, ticketType);
			}
			else {

				ArrayList<Flight> flights2 = candidateFlights(flight1);
				for (Flight flight2 : flights2) {
					twoFlights = new ArrayList<Flight>();
					twoFlights.add(flight1);
					
					/* if the layover time between flight1 and flight2 is not appropriate, or flight2 is full, or flight2's direction are opposite, ignore this flight2,
					 * otherwise this is a candidate flight, continue to check flight2's arrival airport, if match the final destination, find a flight plan with one stop, 
					 * otherwise  initiate another for loop*/
					if (isLagTimeAppropriate(flight1, flight2) == false || isFull(flight2, ticketType) == true || checkDirection(flight2, customerDirection) == false ) continue;
					if (flight2.getArrivalAirport().getCode().equals(arrivalCode)) {
						addOnePlan(flight2, twoFlights, allPlans, ticketType);
					}
					else {
						ArrayList<Flight> flights3 = candidateFlights(flight2);
						for (Flight flight3 : flights3) {
							threeFlights = new ArrayList<Flight>();
							threeFlights.add(flight1);
							threeFlights.add(flight2);
							if (isLagTimeAppropriate(flight2, flight3) == true && isFull(flight3, ticketType) == false && 
									flight3.getArrivalAirport().getCode().equals(arrivalCode)) addOnePlan(flight3, threeFlights, allPlans, ticketType);
						}
					}
				}
			}
    	}
    	return allPlans;
    }
    
    
    
    /**
     * check whether one flight's specified ticket type is still available, if yes, return false  
     */
    private boolean isFull(Flight flight, String ticketType){
    	int availableSeat = 0;
    	if (ticketType.equals("firstClass")) availableSeat = flight.getFirstClassSeats();
    	else availableSeat = flight.getCoachSeats();
    	return availableSeat == 0;
    }
    
    /**
     * judge whether two flight's layover time large than 30 minutes and less than 3 hours, if yes, return true 
     */
    private boolean isLagTimeAppropriate(Flight lastFlight, Flight nextFlight){
    	
    	Date arrivalTime = lastFlight.getArrivalTime();
    	Date departureTime = nextFlight.getDepartureTime();
    	long lagTime = (departureTime.getTime() - arrivalTime.getTime())/(60*1000);
    	if (lagTime >= 30 && lagTime <=60*3	 ) return true;
    	else return false;
    }
    
    /**
     * find all possible flights which follows a given flight, due to the boundary conditions(e.g today's midnight with 
     * tommorow's early morning can be also less than 3 hours), not only previous flight's arrival day should be queried, but also the day after this day 
     * also need to be queried.   
     */
    private ArrayList<Flight> candidateFlights(Flight lastFlight){
    	Date arrivalTime = lastFlight.getArrivalTime();
		String lastFlightArrivalCode = lastFlight.getArrivalAirport().getCode();
		String departureDayTemp1 = changeTimeToDay(arrivalTime);
		String departureDayTemp2 = computeTomorrow(arrivalTime);
		
		ArrayList<Flight> flights1 = AirlinesSystem.queryDepartureFlight(lastFlightArrivalCode, departureDayTemp1);
    	ArrayList<Flight> flights2 = AirlinesSystem.queryDepartureFlight(lastFlightArrivalCode, departureDayTemp2);
        
    	ArrayList<Flight> al= new ArrayList<Flight>();
        al.addAll(flights1);
        al.addAll(flights2);
        
        return al;
    }
    
    
    /**
     * change the date type value into particular (year_month_day) String type value 
     * so that it can be passed as parameter to queryDepartureFlight method
     * @param time in type of Date
     * @return String value in format such as "2015_5_14"
     */
    private String changeTimeToDay(Date time){
		@SuppressWarnings("deprecation")
		int year1 = time.getYear()+1900;
		@SuppressWarnings("deprecation")
		int month1 = time.getMonth()+1;
		@SuppressWarnings("deprecation")
		int day1 = time.getDate();
		String day  = year1 + "_" + month1 + "_" + day1;
		return day;
    }
    
    /** given a one day, change it into tomorrow in format of "year_month_day"
     * @param a specific day
     */
    private String computeTomorrow(Date time){
    	@SuppressWarnings("deprecation")
		int year1 = time.getYear()+1900;
		@SuppressWarnings("deprecation")
		int month1 = time.getMonth()+1;
		@SuppressWarnings("deprecation")
		int day1 = time.getDate() + 1;
		String tomorrow  = year1 + "_" + month1 + "_" + day1;
		return tomorrow;
    }
    
    /**
     * add one flight to a flightsTemp and add this plan to allPlans
     * @param newFlight This flight is the last flight which its arrival code is customer's destination airport code 
     */
    private ArrayList<OnewayTicketPlan> addOnePlan(Flight newFlight, ArrayList<Flight> flightsTemp, ArrayList<OnewayTicketPlan> allPlans, String ticketType ){
    	flightsTemp.add(newFlight);
    	OnewayTicketPlan onePlane = new OnewayTicketPlan(flightsTemp, ticketType);
    	allPlans.add(onePlane);
    	return allPlans;
    }
    
    /**
     * check this flight's direction is the same as customer's direction. this is used to pruning the searching tree.
     * @return boolean value, true means they are the in the same direction 
     */
    private boolean checkDirection(Flight flight, float customerDirection){

    	float thisDirection = flight.getArrivalAirport().getLongitude() - flight.getDepatureAirport().getLongitude();
    	if (customerDirection * thisDirection > 0) return true;
    	else return false;
    }
	

}
