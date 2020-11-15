package taco.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import taco.Ingredient;
import taco.Ingredient.Type;
import taco.Order;
import taco.Taco;
import taco.data.IngredientRepository;
import taco.data.TacoRepository;

@Slf4j                      //注解在类，生成log变量，严格意义来说是常量。
@Controller
@RequestMapping("/design")  //注解映射请求路径
@SessionAttributes("order")
public class DesignTecoController {
	
	private final IngredientRepository ingredientRePo;
	private TacoRepository designRepo;
	
	@Autowired
	public  DesignTecoController(IngredientRepository ingredientRePo,TacoRepository designRepo) {
		this.ingredientRePo=ingredientRePo;
		this.designRepo=designRepo;
	}
	
	@ModelAttribute(name="order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name="taco")
	private Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String ShowDesignForm(Model model) {
//		List<Ingredient> ingredients2 = Arrays.asList(
//				new Ingredient("FLTO", "Flour Tortilla",Type.WRAP),
//				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE),
//				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE),
//				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//				);
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRePo.findAll().forEach(i->ingredients.add(i));		
		
		Type[] types = Ingredient.Type.values();
		for(Type type:types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
		}
		log.info("Processing ingredients: " + ingredients.toString());
//		log.info("Processing ingredients2: " + ingredients2.toString());
		model.addAttribute("design", new Taco());
		return"design";
	}
	
	@PostMapping
	  public String processDesign(@Valid Taco design,Errors errors,@ModelAttribute Order order) {
		if(errors.hasErrors()||design.getName()==null||design.getIngredients().size()==0) {
			return "redirect:/design";
		}
		design.setId(designRepo.findTacoId());
		log.info("Processing ingredients: " + design.toString());
		Taco saved = designRepo.save(design);
//		List<Taco> tacos = new ArrayList<>();
//		tacos.add(saved);
//		order.setTacos(tacos);
		order.addDesign(saved);
		log.info("Processing order: " + order.getTacos().toString());
	    log.info("Processing design: " + design);
	    return "redirect:/orders/current";
//	    return "redirect:/";
	  }
	
	public List<Ingredient> filterByType(List <Ingredient> ingredients,Type type){
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
		
	}
}
