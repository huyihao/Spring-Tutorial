package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

	private OrderProps props;
	
	public OrderController(OrderProps props) {		
		this.props = props;		
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		ProdProfile prodProfile = SpringContext.getBean(ProdProfile.class);
		if (prodProfile != null) {
			log.info("This is prod env!!!");
		} else {
			log.info("This is dev env!!!");
		}
		
		log.info("PageSize is " + props.getPageSize());
		model.addAttribute("orderProps", props);
		return "orderForm";
	}
	
}
