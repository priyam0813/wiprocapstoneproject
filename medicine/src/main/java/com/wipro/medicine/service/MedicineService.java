package com.wipro.medicine.service;

import java.util.List;

import com.wipro.medicine.entity.Medicine;

public interface MedicineService {
	
	Medicine saveMedicine(Medicine medicine, int categoryId);
    List<Medicine> getAllMedicines();
    void deleteMedicine(int medicineId);
    List<Medicine> findByCategoryName(String categoryName);
	Medicine getMedicineById(int medicineId);
	Medicine updateMedicine(Medicine medicine);
	
	
	

}
