package tacos.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	// 等同于 select * from `taco_order` where `user` = user order by `placed_at` desc limit 0,12; 
	List<Order> findByUserIdOrderByPlacedAtDesc(Long userId, Pageable pageable);	
	
}
