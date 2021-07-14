package com.carlicenseplate.licenseplate.dao;

import com.carlicenseplate.licenseplate.model.Car;
import com.carlicenseplate.licenseplate.model.Location;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("redis")
public class RedisDB implements CarDAO{
	private final HashOperations<String, String, Car> hashOperations;
	private final static String HASH_KEY = "CAR";

	public RedisDB(RedisTemplate<String, Car> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public boolean addCar(Car car) {
		return hashOperations.putIfAbsent(HASH_KEY, car.getLicensePlate(), car);
	}

	@Override
	public Collection<Car> getCars() {
		return hashOperations.entries(HASH_KEY).values();
	}

	@Override
	public Car getCar(String licensePlate) {
		return hashOperations.get(HASH_KEY, licensePlate);
	}

	@Override
	public boolean setCar(String licensePlate, Location location) {
		if (hashOperations.get(HASH_KEY, licensePlate) == null) return false;
		hashOperations.put(HASH_KEY, licensePlate, new Car(licensePlate, location));
		return true;
	}

	@Override
	public void setCarAlways(Car car) {
		hashOperations.put(HASH_KEY, car.getLicensePlate(), car);
	}

	@Override
	public boolean removeCar(String licensePlate) {
		return hashOperations.delete(HASH_KEY, licensePlate).intValue() == 1;
	}
}
