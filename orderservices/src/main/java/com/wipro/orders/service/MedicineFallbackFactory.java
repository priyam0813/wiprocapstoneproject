package com.wipro.orders.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.wipro.orders.model.Medicine;


@Component
public class MedicineFallbackFactory implements FallbackFactory<MedicineServiceConsumer>{

	@Override
	public MedicineServiceConsumer create(Throwable cause) {
		// TODO Auto-generated method stub
		return new MedicineServiceConsumer() {
			
			@Override
			public Medicine getMedicineById(int medicineId) {
				// TODO Auto-generated method stub
				return new Medicine();
			}
		};
	}

	
}
