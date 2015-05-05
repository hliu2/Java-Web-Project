package com.team2.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author HAI LIU
 * @telephone 7744629288
 * @description process all things about the back-end database 
 */
public class DatabaseManagement {
	/**
	 * @return a XmlString which contains information of all airports
	 */
	
	private DatabaseManagement(){
		
	}
	
	private static DatabaseManagement instance = null; 
	
	
	public static DatabaseManagement getInstance(){
		instance = new DatabaseManagement(); 
		return instance == null ? new DatabaseManagement():instance;
	}
	
	
	
	
	
	public String getAirports(){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team02&action=list&list_type=airports");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","team2");
			
			int responseCode = connection.getResponseCode();
			if((responseCode >= 200) && (responseCode <=299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null? "URF-8":encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null){
					result.append(line);
				}
				reader.close();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/**
	 * @return a XmlString which contains information of all airplanes
	 */
	public String getAirplanes(){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team02&action=list&list_type=airplanes");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","team2");
			
			int responseCode = connection.getResponseCode();
			if((responseCode >= 200) && (responseCode <=299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null? "URF-8":encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null){
					result.append(line);
				}
				reader.close();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * @param airportCode string object of departure airport code 
	 * @param departureDay string object of departure day 
	 * @return a string object which contains all candidate flights information satisfying specified departure day and airport   
	 */
	public String getDepartureFlights(String airportCode, String departureDay){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?"
					+ "team=Team02&action=list&list_type=departing&airport="+airportCode+"&day="+departureDay);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","team2");
			
			int responseCode = connection.getResponseCode();
			if((responseCode >= 200) && (responseCode <=299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null? "URF-8":encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null){
					result.append(line);
				}
				reader.close();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
		
	/**
	 * @return a string object which contains all candidate flights information satisfying specified arriving day and airport   
	 */
	public String getArivalFlights(String airportCode, String arivalDay){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?"
					+ "team=Team02&action=list&list_type=arriving&airport="+airportCode+"&day="+arivalDay);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","team2");
			
			int responseCode = connection.getResponseCode();
			if((responseCode >= 200) && (responseCode <=299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null? "URF-8":encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null){
					result.append(line);
				}
				reader.close();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * @return a boolean which indicates whether successfully reset the database
	 */
	@SuppressWarnings("unused")
	private boolean resetDB(){
		URL url;
		HttpURLConnection connection;
	
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team02&action=resetDB");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","team2");
			
			int responseCode = connection.getResponseCode();
			if((responseCode >= 200) && (responseCode <=299)) {
				return true;
			} else return false;
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * @return a boolean which indicates whether successfully lock the database
	 */
	private boolean lockDB() {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			//String params = QueryFactory.lock("team02");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes("team=Team02&action=lockDB");
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			
			// print response code to see whether it successfully lock the database
			System.out.println("\nSending 'post' to lock databasebase");
			System.out.println("\nResponse Code: "+responseCode);
			
			if((responseCode >= 200)&&(responseCode <= 299)) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();
				
				while((line = in.readLine()) != null){
					response.append(line);
				}
				in.close();
				
				System.out.println(response.toString());
				return true;
			}
			else return false;
			
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
				
		
	}

	/** 
	 * @return a boolean which indicates whether successfully unlock the database 
	 */
	private boolean unlockDB() {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
					
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes("team=Team02&action=unlockDB");
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'post' to unlock databasebase");
			System.out.println("\nResponse Code: "+responseCode);
			
			if((responseCode >= 200)&&(responseCode <= 299)) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();
				
				while((line = in.readLine()) != null){
					response.append(line);
				}
				in.close();
				
				System.out.println(response.toString());
				return true;
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
		
		
		
	}
	
	/**
	 * @param flightData either one way 
	 * @return
	 */
	public boolean buyTickets(String flightData) {
		
		URL url;
		HttpURLConnection connection;
		
		// lock the database, if unsuccessful, means other agency has locked the dababase return false
		DatabaseManagement databaseManagement = new DatabaseManagement(); 
		boolean sucessfulLock = databaseManagement.lockDB();
		if (!sucessfulLock) {
			return false;
		}
		
		try {
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes("team=Team02&action=buyTickets&flightData="+flightData);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'post' to buy tickets");
			System.out.println("\nResponse Code: "+responseCode);
			
			if((responseCode >= 200)&&(responseCode <= 299)) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();
				
				while((line = in.readLine()) != null){
					response.append(line);
				}
				in.close();
				
				System.out.println(response.toString());
				return true;
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			databaseManagement.unlockDB();
		}
		return false;
			
		
		
	}
		


}


