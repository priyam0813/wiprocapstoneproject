package com.wipro.medicine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wipro.medicine.entity.Medicine;



public interface MedicineRepositoy extends JpaRepository<Medicine, Integer>{

	 @Query("SELECT m FROM Medicine m WHERE m.category.categoryName = :categoryName")
	    List<Medicine> findByCategoryName(@Param("categoryName") String categoryName);
	}

