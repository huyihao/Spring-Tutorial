package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;

import tacos.jpa.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
