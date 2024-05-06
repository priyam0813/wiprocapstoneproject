package com.wipro.medicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.wipro.medicine.entity.Medicine;
import com.wipro.medicine.service.MedicineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	@GetMapping("/all")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        if (medicines.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(medicines);
        }
    }
	 @DeleteMapping("/{medicineId}")
	    public ResponseEntity<Void> deleteMedicine(@PathVariable int medicineId) {
	        medicineService.deleteMedicine(medicineId);
	        return ResponseEntity.ok().build();
	    }

	 @GetMapping("/category")
	    public ResponseEntity<List<Medicine>> findByCategoryName(@RequestParam String categoryName) {
	        List<Medicine> medicines = medicineService.findByCategoryName(categoryName);
	        if (medicines.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.ok(medicines);
	        }
	    }
	 @GetMapping("/{id}")
		public ResponseEntity<Medicine> fetchAllDetails(@PathVariable("id") int medicineId){
			Medicine medicine = medicineService.getMedicineById(medicineId);
			return new ResponseEntity<>(medicine , HttpStatus.OK);
		}
	
	@PostMapping("/save")
	public ResponseEntity<Medicine> saveMedicine(@Valid @RequestBody Medicine medicine, @RequestParam int categoryId) {
	        Medicine savedMedicine = medicineService.saveMedicine(medicine, categoryId);
	        return ResponseEntity.ok(savedMedicine);
	    }

	
	@PutMapping("/update")
	public  ResponseEntity<Medicine> editMedicine(@Valid @RequestBody Medicine medicine) {
		medicineService.updateMedicine(medicine);
		ResponseEntity<Medicine> responseEntity = new ResponseEntity<>(medicine,HttpStatus.OK);
		return responseEntity;
	}
	
	
	
}

