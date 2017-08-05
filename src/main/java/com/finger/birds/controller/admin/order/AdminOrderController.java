package com.finger.birds.controller.admin.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.db.po.order.OrderAmountPO;
import com.finger.birds.db.po.order.OrderListPO;
import com.finger.birds.db.po.user.UserSearchPO;
import com.finger.birds.param.order.OrderCountParam;
import com.finger.birds.param.order.OrderQueryParam;
import com.finger.birds.service.order.OrderService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController<AdminOrderController>{

	@Resource
	private OrderService orderService;

	@RequestMapping(value="/loadingMore.html")
	public void loadingMore(@Valid OrderQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<OrderListPO>> rslt = new Result<>();
		OrderQueryBean bean = param.initBean();
		PageBean<OrderListPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(orderService.getList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/searchByStart.html")
	public void searchByStart(@Valid OrderQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<UserSearchPO>> rslt = new Result<>();
		OrderQueryBean bean = param.initBean();
		rslt.initRslt(orderService.searchByStart(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}

	@RequestMapping(value="/getOrderData.html")
	public void getOrderData(HttpServletResponse response){
		super.getAdminUser();
		Map<String, Object> data = orderService.getOrderData();
		AjaxUtils.write(response, data);
	}
	
	@RequestMapping(value="/getPayAmountList.html")
	public void getPayAmountList(@Valid OrderCountParam param, HttpServletResponse response){
		super.getAdminUser();
		Result<List<Map<String, Object>>> rslt = new Result<>();
		OrderQueryBean bean = param.initBean();
		PageBean<OrderAmountPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(orderService.getPayAmountList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getProfitAmountList.html")
	public void getProfitAmountList(@Valid OrderCountParam param, HttpServletResponse response){
		super.getAdminUser();
		Result<List<Map<String, Object>>> rslt = new Result<>();
		OrderQueryBean bean = param.initBean();
		PageBean<OrderAmountPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(orderService.getProfitAmountList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getMsgByOrderCode.html")
	public void getMsgByOrderCode(@Valid String orderCode, HttpServletResponse response){
		super.getAdminUser();
		Map<String, Object> map = orderService.getMsgByOrderCode(orderCode);
		AjaxUtils.write(response, map);
	}
}
