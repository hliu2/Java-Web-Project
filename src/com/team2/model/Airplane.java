package com.team2.model;

/**
 * @author Yu Tian 
 * @description contains all information about airplane
 */
public class Airplane {
	String manufacturer;
	String model;
	int coachSeats;
	int firstClassSeats;
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCoachSeats() {
		return coachSeats;
	}
	public void setCoachSeats(int coachSeats) {
		this.coachSeats = coachSeats;
	}
	public int getFirstClassSeats() {
		return firstClassSeats;
	}
	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}
	
	// override toString method to help printing results for debugging 
	public String toString(){
		return "Airplane [manufacturer= \""+manufacturer+"\" model=\""+model+"\" coachSeats="+coachSeats
				+" firstClassSeats="+firstClassSeats+" ]";
	}

}
