package com.wangzi.test.strategy;

public class MobileStrategy implements Strategy {

	@Override
	public Double calRecharge(Double charge, RechargeTypeEnum type) {
		return charge;
	}

}
