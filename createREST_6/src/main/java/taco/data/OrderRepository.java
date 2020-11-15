package taco.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import taco.Order;
import taco.User;

public interface OrderRepository extends CrudRepository<Order, String>{
	
	List<Order> findByZip(String Zip);
	
	List<Order> readOrdersByZipAndCreatedAtBetween(String Zip,Date startDate,Date endDate);

//	List<Order> findByDeliveryToAndDeliveryCityAllIgnoresCase(String deliveryTo,String deliveryCity);

//	List< Order> findByDeliveryCityOrderByDeliveryTo(String city);
	
	@Query("select id,name from Order") 
	List<Order> readOrders();
	
//	List<Order> findByUserOrderByPlacedAtDesc(User user);

	List<Order> findByOwnerOrderByCreatedAtDesc(User owner,Pageable pageable);

	//获取oracle序列的下一个值，方法返回类型是Long或者String都可正常运行
	@Query(value = "SELECT seq_tacoId.nextval from DUAL", nativeQuery = true)
	String findTacoId();


}
