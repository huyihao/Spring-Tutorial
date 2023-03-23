package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.domain.Order;
import tacos.domain.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	private JdbcTemplate jdbc; 

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc)
				                .withTableName("Taco_Order")
				                .usingGeneratedKeyColumns("id");	// 使用系统生成的id并返回

		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
									.withTableName("Taco_Order_Tacos");

		this.objectMapper = new ObjectMapper();
		this.jdbc = jdbc;
	}

	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);
		
		List<Taco> tacos = order.getTacos();
	    for (Taco taco : tacos) {
	        saveTacoToOrder(taco, orderId);
	    }

	    return order;
	}

	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		// objectMapper会将placedAt的值转化为long，所以这里要覆盖设置以下map
		values.put("placedAt", order.getPlacedAt());

		long orderId = orderInserter.executeAndReturnKey(values).longValue();
		return orderId;
	}
	
	private void saveTacoToOrder(Taco taco, long orderId) {
	    Map<String, Object> values = new HashMap<>();
	    values.put("tacoOrder", orderId);
	    values.put("taco", taco.getId());
	    orderTacoInserter.execute(values);
	}

	@Override
	public List<Order> queryOrders() {
		return jdbc.query("select * from Taco_Order", new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				order.setDeliveryName(rs.getString("deliveryName"));
				order.setDeliveryStreet(rs.getString("deliveryStreet"));
				order.setDeliveryCity(rs.getString("deliveryCity"));
				order.setDeliveryState(rs.getString("deliveryState"));
				order.setDeliveryZip(rs.getString("deliveryZip"));
				order.setCcNumber(rs.getString("ccNumber"));
				order.setCcExpiration(rs.getString("ccExpiration"));
				order.setCcCVV(rs.getString("ccCVV"));
				order.setId(rs.getLong("id"));
				order.setPlacedAt(rs.getDate("placedAt"));
				return order;
			}
		});		
	}	
	
	
}