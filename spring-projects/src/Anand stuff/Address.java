package org.sample.demo;

public class Address {
	
	private String country;
	
	private String state;
	
	private String city;
	
	private long zipcode;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getZipcode() {
		return zipcode;
	}

	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", state=" + state + ", city="
				+ city + ", zipcode=" + zipcode + "]";
	}	
}
