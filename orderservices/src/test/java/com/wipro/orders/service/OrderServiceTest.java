package com.wipro.orders.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.orders.entity.Order;
import com.wipro.orders.entity.OrderItem;
import com.wipro.orders.repository.OrderRepository;
@SpringBootTest(properties="eureka.client.enabled=false")
public class OrderServiceTest {

	@InjectMocks
	private OrderServiceImpl orderService;

	@Mock
	private OrderRepository orderRepository;

	@Test
    public void testSaveOrder() {
        Order order3= new Order();
        OrderItem orderItem3 = new OrderItem();
        order3.setOrderId(5);
        order3.setOrderTotal(250);
        order3.setOrderDate(LocalDate.of(2024,05,03));
        order3.setOrderStatus("pending");
        orderItem3.setOrderItemId(3);
        orderItem3.setMedicineId(11);;
        orderItem3.setQuntity(4);
        orderItem3.setItemTotal(250);
        
        when(orderRepository.save(order3)).thenReturn(order3);
        
        Order savedOrder = orderService.saveOrder(order3);
        
        assertEquals(250, savedOrder.getOrderTotal());
        assertEquals(LocalDate.of(2024,05,03), savedOrder.getOrderDate());
        assertEquals("pending", savedOrder.getOrderStatus());
        
        
        verify(orderRepository, times(1)).save(order3);
    }
    
    @Test
    public void testSaveCustomerWithException() {
        Order order3= new Order();
        OrderItem orderItem3 = new OrderItem();
        order3.setOrderId(5);
        order3.setOrderTotal(250);
        order3.setOrderDate(LocalDate.of(2024,05,03));
        order3.setOrderStatus("pending");
        orderItem3.setOrderItemId(3);
        orderItem3.setMedicineId(11);;
        orderItem3.setQuntity(4);
        orderItem3.setItemTotal(250);
        
        when(orderRepository.save(order3)).thenThrow(new RuntimeException("Failed to save order"));
        
        assertThrows(RuntimeException.class, () -> orderService.saveOrder(order3));
    }
}
