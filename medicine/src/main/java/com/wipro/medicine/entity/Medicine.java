package com.wipro.medicine.entity;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "medicines_tbl")
public class Medicine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicineId;
    
    private String medicineName;
    private double price;
    
    @Past(message = "Manufacture date must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
     private LocalDate mfd;
    
    @Future(message = "Expiration date must be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expireDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}