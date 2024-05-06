package com.wipro.medicine.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wipro.medicine.entity.Category;
import com.wipro.medicine.entity.Medicine;
import com.wipro.medicine.exception.ResourceNotFoundException;
import com.wipro.medicine.repository.CatagoryRepository;
import com.wipro.medicine.repository.MedicineRepositoy;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepositoy medicineRepository;

	@Autowired
	private CatagoryRepository categoryRepository;

	@Override
	public Medicine saveMedicine(Medicine medicine, int categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Medicine not exit with id"+categoryId));
		medicine.setCategory(category);
		return medicineRepository.save(medicine);
	}

	@Override
	public List<Medicine> getAllMedicines() {
		return medicineRepository.findAll();
	}

	@Override
	public void deleteMedicine(int medicineId) {
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
	    if(optionalMedicine.isEmpty()) {
	    	throw new ResourceNotFoundException("Medicine not exit with id"+medicineId);
	    }
	    Medicine medicine = optionalMedicine.get();
		medicineRepository.delete(medicine);
	}

	@Override
	public List<Medicine> findByCategoryName(String categoryName) {
		Category category = categoryRepository.findByCategoryName(categoryName);
		if (category != null) {
			return category.getMedicines();
		}
		return Collections.emptyList();
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) {
    Optional<Medicine> optionalMedicine = medicineRepository.findById(medicine.getMedicineId());
    if(optionalMedicine.isEmpty()) {
    	throw new ResourceNotFoundException("Medicine not exit with id"+medicine.getMedicineId());
    }
    optionalMedicine.get();
    Medicine medicines = medicineRepository.save(medicine);
		return medicines;
	}

	@Override
	public Medicine getMedicineById(int medicineId) {
		Optional<Medicine> optionalCustomer = medicineRepository.findById(medicineId);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Medicine not exit with id"+medicineId);
		}
		Medicine medicine = optionalCustomer.get();
		return medicine;
	}

}
