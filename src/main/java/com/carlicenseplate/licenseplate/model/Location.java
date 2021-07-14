package com.carlicenseplate.licenseplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Location  implements Serializable {
	private Double longitude;
	private Double latitude;

	public Location() {
	}

	public Location(@JsonProperty("longitude") Double longitude, @JsonProperty("latitude") Double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Get longitude.
	 *
	 * @return private variable of longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * Sets longitude.
	 *
	 * @param longitude private field of longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get latitude.
	 *
	 * @return private variable of latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * Sets latitude.
	 *
	 * @param latitude private field of latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location{" +
				"longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}
