package com.wipro.medicine.service;

import java.util.List;

import com.wipro.medicine.entity.Category;

public interface CatagoryService {
	
	Category saveCatagory(Category catagory);
	Category getCatagoryById(int categoryId);
	List<Category> getAllCatagory();

}
