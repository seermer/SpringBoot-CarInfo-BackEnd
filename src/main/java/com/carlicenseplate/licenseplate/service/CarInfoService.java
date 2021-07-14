package com.carlicenseplate.licenseplate.service;

import com.carlicenseplate.licenseplate.dao.CarDAO;
import com.carlicenseplate.licenseplate.model.Car;
import com.carlicenseplate.licenseplate.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarInfoService {
	private final CarDAO carDAO;

	@Autowired
	public CarInfoService(@Qualifier("redis") CarDAO carDAO) {
		this.carDAO = carDAO;
	}

	public boolean addCar(Car car) {
		return carDAO.addCar(car);
	}

	public Collection<Car> getCars() {
		return carDAO.getCars();
	}

	public Car getCar(String licensePlate) {
		return carDAO.getCar(licensePlate);
	}

	public boolean setCar(String licensePlate, Location location) {
		return carDAO.setCar(licensePlate, location);
	}

	public void setCarAlways(Car car) {
		carDAO.setCarAlways(car);
	}

	public boolean removeCar(String licensePlate) {
		return carDAO.removeCar(licensePlate);
	}
}
