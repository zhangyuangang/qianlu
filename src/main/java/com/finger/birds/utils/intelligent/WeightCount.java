package com.finger.birds.utils.intelligent;

import com.finger.birds.db.po.intelligent.WeightPO;
import com.finger.birds.db.po.user.UserCenterPO;

public class WeightCount {

	public static Double productOfVector(WeightPO weight, UserCenterPO user, Integer score) {
		Double sum1 = weight.getGw_cp() * user.getGw_cp() + weight.getGw_cw() * user.getGw_cw();
		Double sum2 = weight.getGw_gl() * user.getGw_gl() + weight.getGw_js() * user.getGw_js();
		Double sum3 = weight.getGw_qt() * user.getGw_qt() + weight.getGw_yx() * user.getGw_yx();
		Double sum4 = weight.getHy_ct() * user.getHy_ct() + weight.getHy_it() * user.getHy_it();
		Double sum5 = weight.getHy_jr() * user.getHy_jr() + weight.getHy_qt() * user.getHy_qt();
		return (sum1 + sum2 + sum3 + sum4 + sum5) * score;
	}
}
