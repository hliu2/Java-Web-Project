package com.team2.model;

import java.util.*;
import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;


/**
 * @author Hai Liu 
 * @description responsible for processing  transaction all about airline system. 
 */
public class AirlinesSystem {
	
	/**
	 * @return a hashmap which key is the airport code, value is the airport object
	 * @description parse the xmlString of all airports information to every single airport object  
	 */
	public static HashMap<String, Airport> parseAirports() {
		HashMap<String, Airport> al = new HashMap<String, Airport>();
		
		// call DatabaseManagement's getAirports to get a xmlString of all airports information
		DatabaseManagement databasemanagement = DatabaseManagement.getInstance();
		String xmlAirports = databasemanagement.getAirports();
		
		//parse xmlString of all airports to airport object  
		try {
			// convert the xmlString to XML document 
			StringReader sr = new StringReader(xmlAirports); 
			InputSource is = new InputSource(sr); 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(is);
			
			// traverse the document object to parse all information 
			NodeList airportList = doc.getElementsByTagName("Airport");
			for(int i = 0; i<airportList.getLength();i++){
				Node airportNode= airportList.item(i);
				// for each airport node parse the node store it into a airport object and put it into the HashMap  
				if (airportNode.getNodeType() == Node.ELEMENT_NODE){
					Element airportElement = (Element) airportNode;
					Airport airport = new Airport();
					airport.setCode(airportElement.getAttribute("Code"));
					airport.setName(airportElement.getAttribute("Name"));
					Element latitude= (Element) airportElement.getElementsByTagName("Latitude").item(0);
					airport.setLatitude(Float.parseFloat(latitude.getTextContent()));
					Element longitude= (Element) airportElement.getElementsByTagName("Longitude").item(0);
					airport.setLongitude(Float.parseFloat(longitude.getTextContent()));
					al.put(airport.getCode(), airport);
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return al;
	}
		
	/**
	 * @return Get all airplanes and put it into a hashmap which key is the airplane model, value is airplane object
	 */
	public static HashMap<String, Airplane> parseAirplanes() {
		
		// call DatabaseManagement's getAirplanes to get a xmlString of all airplanes information
		HashMap<String, Airplane> al = new HashMap<String, Airplane>();
		DatabaseManagement databasemanagement = DatabaseManagement.getInstance();
		String xmlAirplane = databasemanagement.getAirplanes();
		//parse xmlString of all airports to airport object  
		
		try {
			// convert the xmlString to XML document 
			StringReader sr = new StringReader(xmlAirplane); 
			InputSource is = new InputSource(sr); 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(is);
			
			// traverse the document object to parse all information 
			NodeList airplaneList = doc.getElementsByTagName("Airplane");
			for(int i = 0; i<airplaneList.getLength();i++){
				Node airplaneNode= airplaneList.item(i);
				// for each airplanes node parse the node store it into a airplane object and put it into the HashMap
				if (airplaneNode.getNodeType() == Node.ELEMENT_NODE){
					Element airplaneElement = (Element) airplaneNode;
					Airplane airplane = new Airplane();
					airplane.setManufacturer(airplaneElement.getAttribute("Manufacturer"));
					airplane.setModel(airplaneElement.getAttribute("Model"));
					Element FirstClassSeats= (Element) airplaneElement.getElementsByTagName("FirstClassSeats").item(0);
					airplane.setFirstClassSeats(Integer.parseInt(FirstClassSeats.getTextContent()));
					Element CoachSeats = (Element) airplaneElement.getElementsByTagName("CoachSeats").item(0);
					airplane.setCoachSeats(Integer.parseInt(CoachSeats.getTextContent()));
					al.put(airplane.getModel(),airplane);
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return al;
	}
	
	/** 
	 * @param arrivalAirportCode Arrival airport code 
	 * @param arivalDay Arival day
	 * @return an ArrayList of Flight object which satisfy the specified arrival day and airport
	 */
	public static ArrayList<Flight> queryArrivalFlight(String arrivalAirportCode, String arivalDay){
		ArrayList<Flight> al = new ArrayList<Flight>();
		
		// call DatabaseManagement's getArivalFlight to get a xmlString of all satisfied arrival flight information
		DatabaseManagement databasemanagement = DatabaseManagement.getInstance();
		String xmlArivalFlights = databasemanagement.getArivalFlights(arrivalAirportCode, arivalDay);
		
		try {
			// call parseAirplanes and parseAirports method to store all airplanes and all airports into a hashmap for join 
			HashMap<String,Airplane> allAirplanes = parseAirplanes();
			HashMap<String,Airport> allAirports = parseAirports();
			
			// convert the xmlString to XML document 
			StringReader sr = new StringReader(xmlArivalFlights); 
			InputSource is = new InputSource(sr); 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(is);
			
			// traverse the document object to parse flight information
			NodeList flightList = doc.getElementsByTagName("Flight");
			
			for(int i = 0; i<flightList.getLength();i++){
				Node flightNode= flightList.item(i);
				
				// for each flight node parse the node, store it into a flight object and add it into ArrayList  
				if (flightNode.getNodeType() == Node.ELEMENT_NODE){
					Element flightElement = (Element) flightNode;
					Flight flight = new Flight();
					
					// get Airplane model, use identification to get the entire airplane object from hashmap of all airports and assign it to airplane attribute 
					String airplaneModel = flightElement.getAttribute("Airplane");
					flight.setAirplane(allAirplanes.get(airplaneModel));
					
					flight.setFlightTime(Integer.parseInt(flightElement.getAttribute("FlightTime")));
					flight.setFlightNumber(flightElement.getAttribute("Number"));
					
					Element departure= (Element) flightElement.getElementsByTagName("Departure").item(0);
					Element departureAirportCodeElement =(Element) departure.getElementsByTagName("Code").item(0);
					flight.setDepatureAirport(allAirports.get(departureAirportCodeElement.getTextContent()));
					Element departureTimeElement = (Element) departure.getElementsByTagName("Time").item(0);
					String time = departureTimeElement.getTextContent();
					@SuppressWarnings("deprecation")
					long timeMilliseconds = Date.parse(time); 
					Date date = new Date(timeMilliseconds);
					flight.setDepartureTime(date);
					
					
					Element arrival= (Element) flightElement.getElementsByTagName("Arrival").item(0);
					Element arrivalAirportCodeElement =(Element) arrival.getElementsByTagName("Code").item(0);
					flight.setArrivalAirport(allAirports.get(arrivalAirportCodeElement.getTextContent()));
					Element arrivalTimeElement = (Element) arrival.getElementsByTagName("Time").item(0);
					String time1 = arrivalTimeElement.getTextContent();
					
					@SuppressWarnings("deprecation")
					long timeMilliseconds1 = Date.parse(time1); 
					Date date1 = new Date(timeMilliseconds1);
					flight.setArrivalTime(date1);
					
					Element seating= (Element) flightElement.getElementsByTagName("Seating").item(0);
					Element firstClassElement =(Element) seating.getElementsByTagName("FirstClass").item(0);
					
					String priceString = firstClassElement.getAttribute("Price");
					float price = Float.parseFloat(priceString.replace("," , "").substring(1));
					flight.setFirstClassPrice(price);
					
					// FirstClass left equals to number of maximum first class seats for this airplane minuses the number of seats which have been booked 
					flight.setFirstClassSeats(flight.getAirplane().getFirstClassSeats() - Integer.parseInt(firstClassElement.getTextContent())); 
					
					Element coachElement =(Element) seating.getElementsByTagName("Coach").item(0);
					
					String priceString2 = coachElement.getAttribute("Price");
					float price2 = Float.parseFloat(priceString2.replace("," , "").substring(1));
					flight.setCoachPrice(price2);
					
					// Coach seats left equals to number of maximum coach seats for this airplane minuses the number of seats which have been booked  
					flight.setCoachSeats(flight.getAirplane().getCoachSeats() - Integer.parseInt(coachElement.getTextContent()));	

					al.add(flight);
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<Flight> queryDepartureFlight(String departureAirportCode, String DepatureDay){
		ArrayList<Flight> al = new ArrayList<Flight>();
		DatabaseManagement databasemanagement = DatabaseManagement.getInstance();
		String xmlArivalFlights = databasemanagement.getDepartureFlights(departureAirportCode, DepatureDay);
		try {
			HashMap<String,Airplane> allAirplanes = parseAirplanes();
			HashMap<String,Airport> allAirports = parseAirports();
			
			StringReader sr = new StringReader(xmlArivalFlights); 
			InputSource is = new InputSource(sr); 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(is);
			NodeList flightList = doc.getElementsByTagName("Flight");
			
			for(int i = 0; i<flightList.getLength();i++){
				Node flightNode= flightList.item(i);
				if (flightNode.getNodeType() == Node.ELEMENT_NODE){
					Element flightElement = (Element) flightNode;
					Flight flight = new Flight();
					
					String airplaneModel = flightElement.getAttribute("Airplane");
					flight.setAirplane(allAirplanes.get(airplaneModel));
					
					flight.setFlightTime(Integer.parseInt(flightElement.getAttribute("FlightTime")));
					flight.setFlightNumber(flightElement.getAttribute("Number"));
					
					Element departure= (Element) flightElement.getElementsByTagName("Departure").item(0);
					Element departureAirportCodeElement =(Element) departure.getElementsByTagName("Code").item(0);
					flight.setDepatureAirport(allAirports.get(departureAirportCodeElement.getTextContent()));
					Element departureTimeElement = (Element) departure.getElementsByTagName("Time").item(0);
					String time1 = departureTimeElement.getTextContent();
					@SuppressWarnings("deprecation")
					long timeMilliseconds1 = Date.parse(time1); 
					Date date1 = new Date(timeMilliseconds1);
					flight.setDepartureTime(date1);
					
					Element arrival= (Element) flightElement.getElementsByTagName("Arrival").item(0);
					Element arrivalAirportCodeElement =(Element) arrival.getElementsByTagName("Code").item(0);
					flight.setArrivalAirport(allAirports.get(arrivalAirportCodeElement.getTextContent()));
					Element arrivalTimeElement = (Element) arrival.getElementsByTagName("Time").item(0);
					String time2 = arrivalTimeElement.getTextContent();
					@SuppressWarnings("deprecation")
					long timeMilliseconds2 = Date.parse(time2); 
					Date date2 = new Date(timeMilliseconds2);
					flight.setArrivalTime(date2);
					
					Element seating= (Element) flightElement.getElementsByTagName("Seating").item(0);
					Element firstClassElement =(Element) seating.getElementsByTagName("FirstClass").item(0);
					
					String priceString = firstClassElement.getAttribute("Price");
					float price = Float.parseFloat(priceString.replace("," , "").substring(1));
					flight.setFirstClassPrice(price);
					flight.setFirstClassSeats(flight.getAirplane().getFirstClassSeats() - Integer.parseInt(firstClassElement.getTextContent()));
					Element coachElement =(Element) seating.getElementsByTagName("Coach").item(0);
					
					String priceString2 = coachElement.getAttribute("Price");
					float price2 = Float.parseFloat(priceString2.replace("," , "").substring(1));
					flight.setCoachPrice(price2);
					
					flight.setCoachSeats(flight.getAirplane().getCoachSeats() - Integer.parseInt(coachElement.getTextContent()));	

					al.add(flight);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return al;
	}


		
	
		



	

}
