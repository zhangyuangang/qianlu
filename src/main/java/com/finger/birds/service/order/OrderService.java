package com.finger.birds.service.order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.db.po.order.OrderAmountPO;
import com.finger.birds.db.po.order.OrderListPO;
import com.finger.birds.model.order.Order;
import com.finger.birds.utils.bean.PageBean;

public interface OrderService {

	Long add(Long userId, Long tuserId, String intro);
	
	Order getById(Long id);

	void synPayCallBack(Long orderId);

	PageBean<OrderListPO> getList(OrderQueryBean bean, PageBean<OrderListPO> page);

	void asnPayCallBack(String thridCode, String orderCode, String totalFee, HttpSession session);

	void choose(Long orderId, Short timeType, Integer status, Long userId, Timestamp startDate);

	void refundMoney(Long orderId);
	
	Map<String, Object> getOrderData();

	PageBean<OrderListPO> searchByStart(OrderQueryBean bean, PageBean<OrderListPO> page);

	PageBean<OrderAmountPO> getPayAmountList(OrderQueryBean bean, PageBean<OrderAmountPO> page);

	PageBean<OrderAmountPO> getProfitAmountList(OrderQueryBean bean, PageBean<OrderAmountPO> page);

	List<OrderListPO> getJobInfoByTimeTypeToday(Short timeType);

	Long checkQianlu(Long userId, Long tuserId);

	List<Short> getListN(OrderQueryBean bean);

	Order getTopByUserId(Long tuserId);

	List<Map<String, Object>> getJobInfoByStatusToday();

	Long xuDan(Long userId, Long tuserId);

	void xudanConfirm(Long orderId, Long userId, Integer status);
	
	void sendXuDanMsg(Long orderId);

	Map<String, Object> getMsgByOrderCode(String orderCode);
}
