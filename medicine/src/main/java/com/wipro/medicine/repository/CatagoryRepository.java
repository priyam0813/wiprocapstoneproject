package com.wipro.medicine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.medicine.entity.Category;



public interface CatagoryRepository extends JpaRepository<Category, Integer>{
	
	 Category findByCategoryName(String categoryName);

}
