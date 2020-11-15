package taco;


import javax.persistence.Entity;
import org.hibernate.mapping.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data                         //生成setter、getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@RequiredArgsConstructor      //注解在类，为类中需要特殊处理的字段生成构造方法，比如final和被@NonNull注解的字段。
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
@Table(name="Taco_Ingredient")
public class Ingredient {

	@Id
	private final String ID;
	private final String name;
	@Enumerated(EnumType.STRING)
	private final Type type;
	
	public static enum Type{
		WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
	}
	
}
