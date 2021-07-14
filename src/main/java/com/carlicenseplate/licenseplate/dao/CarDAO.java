package com.carlicenseplate.licenseplate.dao;

import com.carlicenseplate.licenseplate.model.Car;
import com.carlicenseplate.licenseplate.model.Location;

import java.util.Collection;

public interface CarDAO {
	boolean addCar(Car car);
	Collection<Car> getCars();
	Car getCar(String licensePlate);
	boolean setCar(String licensePlate, Location location);
	boolean removeCar(String licensePlate);
	void setCarAlways(Car car);
}
