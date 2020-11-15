package taco.data;

import org.springframework.data.repository.CrudRepository;

import taco.Taco;
import org.springframework.data.jpa.repository.Query;

public interface TacoRepository extends CrudRepository<Taco, String> {

	 //获取oracle序列的下一个值，方法返回类型是Long或者String都可正常运行
	 @Query(value = "SELECT seq_tacoId.nextval from DUAL", nativeQuery = true)
	 String findTacoId();
	 
}
