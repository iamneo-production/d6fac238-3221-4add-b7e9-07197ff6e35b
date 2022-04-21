package com.examly.springapp.service;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.dto.AddressDto;
import com.examly.springapp.model.Address;
import com.examly.springapp.model.Cart;
import com.examly.springapp.model.CartItem;
import com.examly.springapp.model.Order;
import com.examly.springapp.model.OrderItem;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.OrderItemsRepo;
import com.examly.springapp.repository.OrderRepo;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private CartService cartService;
	@Autowired 
	private OrderItemsRepo orderItemsRepo;
	@Autowired
	private ProductService productService; 
	public Order placeOrder(UserModel user,AddressDto address) {
      
        Cart cart  = cartService.getCartByUser(user.getId());
        Address addressModel=new Address();
        addressModel.setHouseno(address.getHouseno());
        addressModel.setStreet(address.getStreet());
        addressModel.setCity(address.getCity());
        addressModel.setState(address.getState());
        addressModel.setPincode(address.getPincode());
        List<CartItem> cartItemList = cart.getItems();
        
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date(System.currentTimeMillis()));
        newOrder.setAddress(addressModel);
        newOrder.setUser(user);
        newOrder.setTotalPrice(cart.getTotal());
        
        orderRepo.save(newOrder);

        for (CartItem cartItem : cartItemList) {
           
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date(System.currentTimeMillis()));
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(newOrder);
            
            orderItemsRepo.save(orderItem);
            newOrder.getOrderItems().add(orderItem);
            orderRepo.save(newOrder);
            productService.changeQuantity(orderItem.getProduct().getPid(),orderItem.getQuantity());
            

        }
     
                cartService.deleteCart(user.getId());
                
           return newOrder;
    }
	public List<Order> listOrder(UserModel user) {
		
		return orderRepo.findAllByUserOrderByCreatedDateDesc(user);
	}
	public Order getOneOrder(long id) {
		Order order= orderRepo.findById(id).orElse(null);
		return order;
	}
	
	public List<Order> allOrders(){
		return orderRepo.findAll();
	}
	
	public void deleteOrder(long id) {
		Order order=orderRepo.findById(id).orElse(null);
		if(order!=null) {
		
		orderRepo.delete(order);
		
		}
		
		
	}
	
}

