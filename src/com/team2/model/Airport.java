package com.team2.model;

/**
 * @author Yu tian
 * @description contains all information about airport
 */
public class Airport {
	private String code;
	private String name;
	private float latitude;
	private float longitude;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	// override toString method to help printing results for debugging 
	public String toString(){
		return "Airport [code=\""+code+"\" name=\""+name+"\" longtitude="+longitude
				+" latidude="+latitude+" ]";
	}

}
