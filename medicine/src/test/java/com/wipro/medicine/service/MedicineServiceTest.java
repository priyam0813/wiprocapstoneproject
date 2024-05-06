package com.wipro.medicine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.medicine.entity.Medicine;
import com.wipro.medicine.exception.ResourceNotFoundException;
import com.wipro.medicine.repository.MedicineRepositoy;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class MedicineServiceTest {

	@InjectMocks
	private MedicineServiceImpl serviceImpl;

	@Mock
	private MedicineRepositoy medicineRepositoy;

	@Test
	public void testGetMedicineDetails() {
		Medicine medicine = new Medicine();
		medicine.setMedicineId(101);
		medicine.setMedicineName("xyz");
		medicine.setMfd(LocalDate.of(2024, 10, 10));
		medicine.setExpireDate(LocalDate.of(2025, 10, 10));

		medicine.setPrice(900);

		when(medicineRepositoy.findById(101)).thenReturn(Optional.of(medicine));
		Medicine actulObj = serviceImpl.getMedicineById(101);
		assertEquals("xyz", actulObj.getMedicineName());
	}

	@Test
	public void testGetAllMedicine() {
		Medicine medicine = new Medicine();
		medicine.setMedicineId(101);
		medicine.setMedicineName("xyz");
		medicine.setMfd(LocalDate.of(2024, 10, 10));
		medicine.setExpireDate(LocalDate.of(2025, 10, 10));

		medicine.setPrice(900);

		Medicine medicine2 = new Medicine();
		medicine2.setMedicineId(102);
		medicine2.setMedicineName("xyzb");
		medicine2.setMfd(LocalDate.of(2023, 10, 10));
		medicine2.setExpireDate(LocalDate.of(2025, 10, 10));
		medicine2.setCategory(null);

		medicine2.setPrice(700);

		List<Medicine> medicines = new ArrayList<>();
		medicines.add(medicine);
		medicines.add(medicine2);

		when(medicineRepositoy.findAll()).thenReturn(medicines);
		List<Medicine> medicineList = serviceImpl.getAllMedicines();
		assertEquals(2, medicineList.size());
	}

	@Test
	public void testGetMedicineDetailsException() {
		when(medicineRepositoy.findById(100)).thenThrow(new ResourceNotFoundException("Product Not Found With Id 100"));
		assertThrows(ResourceNotFoundException.class, () -> serviceImpl.getMedicineById(100));
	}

	@Test
	void testDeleteMedicine() {
		Medicine medicine = new Medicine();
		medicine.setMedicineId(101);
		medicine.setMedicineName("xyz");
		medicine.setMfd(LocalDate.of(2024, 10, 10));
		medicine.setExpireDate(LocalDate.of(2025, 10, 10));

		medicine.setPrice(900);
		when(medicineRepositoy.findById(101)).thenReturn(Optional.of(medicine));

		doNothing().when(medicineRepositoy).delete(medicine);

		serviceImpl.deleteMedicine(101);

		verify(medicineRepositoy, times(1)).findById(101);
		verify(medicineRepositoy, times(1)).delete(medicine);

	}

	@Test
	void testDeleteMedicineWithException() {
		Medicine medicine = new Medicine();
		medicine.setMedicineId(111);
		medicine.setMedicineName("xyz");
		medicine.setMfd(LocalDate.of(2024, 10, 10));
		medicine.setExpireDate(LocalDate.of(2025, 10, 10));

		medicine.setPrice(900);
		when(medicineRepositoy.findById(111))
				.thenThrow(new ResourceNotFoundException("Medicine Not Found With Id 111"));
		assertThrows(ResourceNotFoundException.class, () -> serviceImpl.deleteMedicine(111));
		verify(medicineRepositoy, times(0)).delete(medicine);

	}

}
