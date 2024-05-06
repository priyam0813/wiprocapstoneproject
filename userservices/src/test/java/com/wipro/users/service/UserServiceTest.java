package com.wipro.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.users.entity.Customer;
import com.wipro.users.entity.User;
import com.wipro.users.exception.ResourceNotFoundException;
import com.wipro.users.repository.CustomerRepository;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class UserServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void testViewCustomerDetailsById() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("priya");
		user.setPassword("1234");
		customer.setFirstName("priyanka");
		customer.setLastName("mhetre");
		customer.setPhoneNo("8688233328");
		customer.setAddress("vij");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Customer actualObj = customerService.getCustomerById(1);
		assertEquals("subbu", actualObj.getFirstName());
	}

	@Test
	public void testViewCustomerDetailsByIdWithException() {
		when(customerRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(1));
	}

	@Test
	public void testViewAllCustomers() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("subbu");
		user.setPassword("1234");
		customer.setFirstName("Subbu");
		customer.setLastName("Konatham");
		customer.setPhoneNo("8688233328");
		customer.setAddress("vij");

		User user1 = new User();
		Customer customer1 = new Customer();
		user1.setUserId(2);
		user1.setUsername("sai");
		user1.setPassword("1234");
		customer1.setFirstName("sai");
		customer1.setLastName("bula");
		customer1.setPhoneNo("8688233328");
		customer1.setAddress("vyu");

		User user2 = new User();
		Customer customer2 = new Customer();
		user2.setUserId(3);
		user2.setUsername("vasu");
		user2.setPassword("1234");
		customer2.setFirstName("vasu");
		customer2.setLastName("bula");
		customer2.setPhoneNo("8688233328");
		customer2.setAddress("vyu");

		List<User> users = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);
		customers.add(customer);
		customers.add(customer1);
		customers.add(customer2);

		when(customerRepository.findAll()).thenReturn(customers);

		List<Customer> customerList = customerService.getAllCustomer();
		assertEquals(3, customerList.size());
	}

	@Test
	public void testSaveCustomer() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("John");
		user3.setPassword("1234");
		customer3.setFirstName("John");
		customer3.setLastName("Doe");
		customer3.setPhoneNo("8688233328");
		customer3.setAddress("123 Main St");

		when(customerRepository.save(customer3)).thenReturn(customer3);

		Customer savedCustomer = customerService.addCustomer(customer3);

		assertEquals("John", savedCustomer.getFirstName());
		assertEquals("Doe", savedCustomer.getLastName());
		assertEquals("8688233328", savedCustomer.getPhoneNo());

		assertEquals("123 Main St", savedCustomer.getAddress());

		verify(customerRepository, times(1)).save(customer3);
	}

	@Test
	public void testSaveCustomerWithException() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("John");
		user3.setPassword("1234");
		customer3.setFirstName("John");
		customer3.setLastName("Doe");
		customer3.setPhoneNo("8688233328");
		customer3.setAddress("123 Main St");

		when(customerRepository.save(customer3)).thenThrow(new RuntimeException("Failed to save customer"));

		assertThrows(RuntimeException.class, () -> customerService.addCustomer(customer3));
	}

	@Test
	public void testUpdateCustomer() {
		// Create a customer object
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setUsername("raj");
		customer.setPassword("1234");
		customer.setFirstName("raj");
		customer.setLastName("kumar");
		customer.setPhoneNo("8688233328");
		customer.setAddress("vij");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		Customer updatedCustomer = customerService.updateCustomer(customer);

		assertEquals("raj", updatedCustomer.getFirstName());

		verify(customerRepository, times(1)).save(customer);
	}

	@Test
	public void testUpdateCustomerWithException() {

		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("subbu");
		user.setPassword("1234");
		customer.setFirstName("Subbu");
		customer.setLastName("Konatham");
		customer.setPhoneNo("8688233328");

		customer.setAddress("vij");

		when(customerRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(customer));
	}

	@Test
	public void testDeleteCustomerById() {
		// Create a customer object
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("subbu");
		user.setPassword("1234");
		customer.setFirstName("Subbu");
		customer.setLastName("Konatham");
		customer.setPhoneNo("8688233328");
		customer.setAddress("vij");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		customerService.deleteCustomer(1);

		verify(customerRepository, times(1)).delete(customer);
	}

	@Test
	public void testDeleteCustomerByIdWithException() {

		when(customerRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(1));
	}

}
