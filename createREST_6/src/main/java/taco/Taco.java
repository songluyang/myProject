package taco;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import javax.persistence.JoinColumn; 

import lombok.Data;

@Entity
@Data
@Table(name="Taco_Taco")
public class Taco {
	@Id  
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tacoId")
//	@SequenceGenerator(name="tacoId",sequenceName="seq_tacoId",allocationSize=1)
    @Column(name="id")
	private String id;
	
	@Column(name = "CREATEDAT")
	private Date createdAt;
	@NotNull
//	@Size(min=5,message="name must be at least 5 characters long")
	private String name;
	
//	@Size(min=1,message="you must choose at least 1 ingredient")
//	@ManyToMany(targetEntity= Ingredient.class)
	@ManyToMany(targetEntity= Ingredient.class,cascade = {CascadeType.ALL})
	  @JoinTable(name="taco_Taco_Ingredients",
	           joinColumns={ @JoinColumn(name="taco",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="ingredient",referencedColumnName="id")})
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createdAt() { 
		this.createdAt = new Date(); 
		}
	
//	@ElementCollection(targetClass=Ingredient.class)
//	private List<Ingredient> ingredients;

}
