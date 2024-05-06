package com.wipro.medicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.medicine.entity.Category;
import com.wipro.medicine.service.CatagoryService;

@RestController
@RequestMapping("/catagory")
public class CatagoryController {
	
	@Autowired
	private CatagoryService catagoryService;
	
	@PostMapping("/save")
	public ResponseEntity<Category> addMedicine(@RequestBody Category catagory) {
		catagoryService.saveCatagory(catagory);
		ResponseEntity<Category> responseEntity = new ResponseEntity<>(catagory,HttpStatus.CREATED);
		return responseEntity;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Category> fetchMedicineDetails(@PathVariable("id") int catagoryId) {
		Category catagory	 = catagoryService.getCatagoryById(catagoryId);
			return new ResponseEntity<>(catagory,HttpStatus.OK);
	}
	@GetMapping("/all")
	public List<Category> getAllCatagory(){
		List<Category> catagorys = catagoryService.getAllCatagory();
		return catagorys;
	}

}
