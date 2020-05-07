package com.yuanyu.multids.repository.order;


import com.yuanyu.multids.domain.order.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author yuanyu
 */

public interface OrderRepository extends JpaSpecificationExecutor<Order>, CrudRepository<Order, String> {

}
