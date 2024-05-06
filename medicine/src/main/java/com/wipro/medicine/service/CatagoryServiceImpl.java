package com.wipro.medicine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.medicine.entity.Category;
import com.wipro.medicine.exception.ResourceNotFoundException;
import com.wipro.medicine.repository.CatagoryRepository;
@Service
public class CatagoryServiceImpl implements CatagoryService{

	@Autowired
	private CatagoryRepository catagoryRepository;
	

	
	
	@Override
	public Category saveCatagory(Category catagory) {
		catagoryRepository.save(catagory);
		return catagory;
	}
/*
	@Override
	public List<Catagory> getAllMedicineByCatagory(int categoryId) {
		Medicine medicine=medicineService.getMedicineById(categoryId);
		List<Catagory> catagory = medicine.get
		return null;
	}
*/

	@Override
	public Category getCatagoryById(int categoryId) {
	Optional<Category> optionalCatagory = catagoryRepository.findById(categoryId);
	 if(optionalCatagory.isEmpty()) {
     	throw new ResourceNotFoundException("Medicine not existing with id: "+categoryId);
     }
	 Category catagory = optionalCatagory.get();
		return catagory;
	}

	@Override
	public List<Category> getAllCatagory() {
		List<Category> catagorysList = catagoryRepository.findAll();
		return catagorysList;
	}
	
}
