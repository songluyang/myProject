package taco.web;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import taco.Order;
import taco.User;
import taco.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository OrderRepo;
	
	private OrderProps orderProps;
	
	public OrderController(OrderRepository OrderRepo,OrderProps orderProps) {
		this.OrderRepo=OrderRepo;
		this.orderProps=orderProps;
	}

	@GetMapping("/current")
	public String OrderForm(Model model) {
		model.addAttribute("order",model.getAttribute("order"));
		log.info("OrderController order: " + model.getAttribute("order").toString());
		return "OrderForm";
	}
	
	@GetMapping
	public String orderForUser(@AuthenticationPrincipal User user, Model model){
		Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
		model.addAttribute("orders", OrderRepo.findByOwnerOrderByCreatedAtDesc(user, pageable));
		return "orderList";

	}
	
	@PostMapping
	public String processOrder(@Valid Order order,Errors errors,SessionStatus sessionStatus,@AuthenticationPrincipal User user) {
		if(errors.hasErrors()) {
			return "OrderForm";
		}
		order.setId(OrderRepo.findTacoId());
		order.setOwner(user);
		log.info("Order: " + order.getTacos().toString());
		OrderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	
	
}
