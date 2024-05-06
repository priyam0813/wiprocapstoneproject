package com.wipro.orders.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.wipro.orders.model.Medicine;

@FeignClient(name="MEDICINE" , fallbackFactory =MedicineFallbackFactory.class)
public interface MedicineServiceConsumer {
	@GetMapping("/medicine/{id}")
	Medicine getMedicineById(@PathVariable("id") int medicineId);
}
