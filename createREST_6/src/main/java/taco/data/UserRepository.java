package taco.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import taco.User;

public interface UserRepository extends CrudRepository<User, String> {


	
	User findByUsername(String username);
	
	//获取oracle序列的下一个值，方法返回类型是Long或者String都可正常运行
	@Query(value = "SELECT seq_tacoId.nextval from DUAL", nativeQuery = true)
	String findId();
	
}
