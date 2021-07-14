package com.carlicenseplate.licenseplate.api;

import com.carlicenseplate.licenseplate.model.Car;
import com.carlicenseplate.licenseplate.model.Location;
import com.carlicenseplate.licenseplate.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@RequestMapping("api/v1/car_info")
@RestController
public class CarInfoController {
	private final CarInfoService carInfoService;

	@Autowired
	public CarInfoController(CarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}


	@PostMapping
	public void addCar(@Valid @NonNull @RequestBody Car car) {
		if (!carInfoService.addCar(car)) throw new IllegalArgumentException("车辆信息已存在");
	}

	@GetMapping(path = "/all")
	public Collection<Car> getCars() {
		return carInfoService.getCars();
	}

	@GetMapping
	public Car getCar(@Valid @NonNull @RequestBody Map<String, String> licensePlate) {
		if (!licensePlate.containsKey("plate")) throw new NullPointerException("json格式错误，未找到plate信息");
		Car retval = carInfoService.getCar(licensePlate.get("plate"));
		if (retval == null) return new Car(licensePlate.get("plate"), new Location(null, null));
		return retval;
	}

	@PutMapping
	public void setCar(@Valid @NonNull @RequestBody Car car) {
		if (!carInfoService.setCar(car.getLicensePlate(), car.getGPS()))
			throw new IllegalArgumentException("车辆信息不存在" + car.getLicensePlate());
	}

	@DeleteMapping
	public void removeCar(@Valid @NonNull @RequestBody Map<String, String> licensePlate) {
		if (!licensePlate.containsKey("plate")) throw new NullPointerException("json格式错误，未找到plate信息");
		if (!carInfoService.removeCar(licensePlate.get("plate")))
			throw new IllegalArgumentException("车辆信息不存在" + licensePlate);
	}

	// Kafka Below

	@KafkaListener(topics = "CarTopic", groupId = "group_id")
	public void consume(Car car) {
		carInfoService.setCarAlways(car);
	}
}
