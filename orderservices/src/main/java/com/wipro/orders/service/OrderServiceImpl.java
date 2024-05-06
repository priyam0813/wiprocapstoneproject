package com.wipro.orders.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.orders.entity.Order;
import com.wipro.orders.entity.OrderItem;
import com.wipro.orders.exception.ResourceNotFoundException;
import com.wipro.orders.model.Customer;
import com.wipro.orders.model.Medicine;
import com.wipro.orders.model.OrderItemResponse;
import com.wipro.orders.model.OrderResponse;
import com.wipro.orders.payload.OrderItemPayload;
import com.wipro.orders.repository.OrderRepository;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerServicesConsumer customerService;
	
	@Autowired
	private MedicineServiceConsumer medicineService;
	
	@Override
	public Order createOrder(long userId, List<OrderItemPayload> selectedProducts) {
      Customer customer = customerService.getCoustomerDetailsById(userId);
		
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("ordered");
		order.setUserId(userId);
		
		List<OrderItem> orderItems= new ArrayList<>();
		
		double orderTotal = 0;
		
		for(OrderItemPayload po :selectedProducts) {
			int medicineId = po.getMedicineId();
			int qyt = po.getQuntity();
		
			Medicine medicine = medicineService.getMedicineById(medicineId);
			
			System.out.println("ItemTotal : "+medicine.getPrice()*qyt);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setMedicineId(medicineId);
			
			orderItem.setItemTotal(medicine.getPrice()*qyt);
			orderItem.setQuntity(qyt);
			
			orderItems.add(orderItem);
			
			
			orderTotal = orderTotal+(medicine.getPrice()*qyt);
			
			orderItem.setOrder(order);
		};
		
		order.setOrderTotal(orderTotal);
		order.setOrderItems(orderItems);
		
		
		return order;
	}

	@Override
	public Order saveOrder(Order order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public OrderResponse getOrderDetails(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found with id:" +orderId);
		}
		Order order = optionalOrder.get();
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderTotal(order.getOrderTotal());
		
		orderResponse.setOrderStatus(order.getOrderStatus());
		
		
		
		long userId =order.getUserId();
		Customer customer = customerService.getCoustomerDetailsById(userId);
		
		orderResponse.setCustomer(customer);

		List<OrderItemResponse> orderItem = new ArrayList<>();
		
		List<OrderItem> oiItems = order.getOrderItems();
		for(OrderItem oi: oiItems) {
			
			OrderItemResponse oitemResp = new OrderItemResponse();
			oitemResp.setOrderItemId(oi.getOrderItemId());
			oitemResp.setItemTotal(oi.getItemTotal());
			oitemResp.setQuntity(oi.getQuntity());
			
			int mid = oi.getMedicineId();
			Medicine medicine = medicineService.getMedicineById(mid);
			oitemResp.setMedicine(medicine);
			orderItem.add(oitemResp);
		}
		orderResponse.setOrderItem(orderItem);
		
			return orderResponse;
		}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<OrderResponse> oresp = new ArrayList<>();
		List<Order> orders = orderRepository.findAll();
	for(Order o :orders) {
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(o.getOrderId());
		orderResponse.setOrderDate(o.getOrderDate());
		orderResponse.setOrderTotal(o.getOrderTotal());
		orderResponse.setOrderStatus(o.getOrderStatus());
		orderResponse.setOrderPrice(o.getOrderPrice());
		
	
		
		long userId =o.getUserId();
		Customer customer = customerService.getCoustomerDetailsById(userId);
		
		orderResponse.setCustomer(customer);
		
		List<OrderItemResponse> orderItem = new ArrayList<>();
		
		List<OrderItem> oiItems = o.getOrderItems();
		for(OrderItem oi: oiItems) {
			OrderItemResponse oitemResp = new OrderItemResponse();
			oitemResp.setOrderItemId(oi.getOrderItemId());
			oitemResp.setItemTotal(oi.getItemTotal());
			oitemResp.setQuntity(oi.getQuntity());
			
			int mid = oi.getMedicineId();
			
			Medicine medicine = medicineService.getMedicineById(mid);
			oitemResp.setMedicine(medicine);
			orderItem.add(oitemResp);
			}
		orderResponse.setOrderItem(orderItem);
		oresp.add(orderResponse);
	}
	return oresp;
}

	@Override
	public Order updateOrder(Order order) {
		Optional<Order> optionalOrder = orderRepository.findById(order.getOrderId());
        if(optionalOrder.isEmpty()) {
        	throw new ResourceNotFoundException("Order not existing with id: "+order.getOrderId());
        }
        orderRepository.save(order);
        return order;
	}

	@Override
	public void deleteOrder(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()) {
        	throw new ResourceNotFoundException("Order not existing with id: "+orderId);
        }
        Order order = optionalOrder.get();
        orderRepository.delete(order);
		
	}
}
