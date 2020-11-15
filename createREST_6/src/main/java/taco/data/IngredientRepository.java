package taco.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

import taco.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,String>{

	@Query("from Ingredient") 
	public List<Ingredient> findAll();
	
}
