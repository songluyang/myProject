package taco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Entity
@Data
@Table(name="taco_taco_order")
public class Order 
//implements Serializable
{
	@Id  
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="orderId")
//	@SequenceGenerator(name="orderId",sequenceName="seq_tacoId",allocationSize=1)
    @Column(name="id", nullable=false, length=20)	
	
	private String id;
	
	@Column(name = "CREATEDAT")
	private Date createdAt;

//	@NotBlank(message = "name is required")
	private String name;

//	@NotBlank(message = "street is required")
	private String street;

//	@NotBlank(message = "city is required")
	private String city;

//	@NotBlank(message = "state is required")
	private String state;

//	@NotBlank(message = "zip is required")
	private String zip;

//	@CreditCardNumber(message = "not a valid credit card number")
	@Column(name = "CCNUMBER")
	private String ccNumber;

//	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	@Column(name = "CCEXPIRATION")
	private String ccExpiration;

//	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	@Column(name = "CCCVV")
	private String ccCVV;
	
//	@Column(name="owner")
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
//	@ManyToMany(targetEntity = Taco.class)
//	private List<Taco> tacos;
//	@ElementCollection(targetClass=Taco.class)
	@ManyToMany(targetEntity= Taco.class,cascade = {CascadeType.ALL})
	  @JoinTable(name="taco_Taco_Order_Tacos",
	           joinColumns={ @JoinColumn(name="TACOORDER",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="TACO",referencedColumnName="id")})
	private List<Taco> tacos=new ArrayList<Taco>();
	  
	  public void addDesign(Taco design) {
//		  if(this.tacos.size()>0)
	          this.tacos.add(design);
//		  else {
//			  this.tacos=new ArrayList<Taco>();
//			  this.tacos.add(design);
//		}
	  }
	  
	  @PrePersist
	  void createdAt() { 
		this.createdAt = new Date(); 
	  }

}
