package tacos.data;

import java.util.List;

import tacos.domain.Order;

public interface OrderRepository {
	
	Order save(Order order);
	List<Order> queryOrders();
	
}
