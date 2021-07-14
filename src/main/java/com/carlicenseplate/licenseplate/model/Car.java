package com.carlicenseplate.licenseplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Car implements Serializable {
	@NotBlank
	private String licensePlate;
	private Location GPS;

	public Car() {
	}

	/**
	 * car default constructor
	 */
	public Car(@JsonProperty("plate") String licensePlate, @JsonProperty("gps") Location gps) {
		this.licensePlate = licensePlate;
		this.GPS = gps;
	}

	/**
	 * Get licensePlate.
	 *
	 * @return private variable of licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * Get GPS.
	 *
	 * @return private variable of GPS
	 */
	public Location getGPS() {
		return GPS;
	}

	/**
	 * Sets GPS.
	 *
	 * @param GPS private field of GPS
	 */
	public void setGPS(Location GPS) {
		this.GPS = GPS;
	}

	@Override
	public String toString() {
		return "Car{" +
				"licensePlate='" + licensePlate + '\'' +
				", GPS=" + GPS +
				'}';
	}
}
