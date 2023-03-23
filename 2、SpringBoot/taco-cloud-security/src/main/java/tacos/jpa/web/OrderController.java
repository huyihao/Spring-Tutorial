package tacos.jpa.web;

import javax.validation.Valid;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.jpa.data.OrderRepository;
import tacos.jpa.domain.Order;
import tacos.jpa.domain.User;


@Controller("jpa-orderController")
@RequestMapping("/jpa-orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "jpa-orderForm";
	}
	
	/**
	 * @AuthenticationPrincipal User user   获取当前会话的用户
	 */
	@PostMapping
	public String processOrder(@Valid Order order, 
							   Errors errors, 
							   SessionStatus sessionStatus,
							   @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "jpa-orderForm";
		}
		
		order.setUserInfo(user);
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/jpa-orders/list";
	}
	
	@ModelAttribute(name = "orders")
	private void addOrdersToModel(Model model) {
		Iterable<Order> orders = orderRepo.findAll();
		model.addAttribute("orders", IteratorUtils.toList(orders.iterator()));
	}
	
	@GetMapping("/list")
	public String listOrders() {
		return "jpa-orderList";
	}
	
}