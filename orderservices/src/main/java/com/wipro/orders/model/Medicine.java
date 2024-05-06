package com.wipro.orders.model;

import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicine {
	
	  
	   private int medicineId;
	   private String medicineName;
	   private double price;
	   private LocalDate mfd;
	   private LocalDate expireDate;
}
