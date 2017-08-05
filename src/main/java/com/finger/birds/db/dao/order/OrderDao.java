package com.finger.birds.db.dao.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.db.po.order.OrderAmountPO;
import com.finger.birds.db.po.order.OrderListPO;
import com.finger.birds.model.order.Order;

@Repository
public interface OrderDao {
	
	Order getById(Long id);
	
	void updateById(@Param("order")Order order, @Param("id")Long id);
	
	void save(Order order);
	
	Order getByCode(String code);
	
	List<Order> getListN(OrderQueryBean bean);

	List<OrderListPO> getList(@Param("bean")OrderQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Integer getListCount(@Param("bean")OrderQueryBean bean);

	Map<String, Object> getTotalPayPerson();

	Map<String, Object> getTotalProfitPerson();

	Map<String, Object> getTotalAmount();

	List<OrderListPO> searchByStart(@Param("bean")OrderQueryBean bean);

	LinkedList<OrderAmountPO> getPayAmountList(@Param("bean")OrderQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	List<Map<String, Object>> getOrderCodeByUserId(Long userId);

	List<OrderAmountPO> getProfitAmountList(@Param("bean")OrderQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Integer getPayListCount(OrderQueryBean bean);

	Integer getProfitListCount(OrderQueryBean bean);

	List<OrderListPO> getJobInfoByTimeTypeToday(@Param("timeType")Short timeType);

	Order getTopByUserId(Long userId);

	List<Map<String, Object>> getJobInfoByStatusToday();
	
	int checkXuDan(@Param("userId")Long userId, @Param("tuserId")Long tuserId, @Param("timeType") Short timeType, @Param("xudan") Short xudan);
}
