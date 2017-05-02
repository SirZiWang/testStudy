package com.wangzi.test.strategy;

public class BusiAcctStrategy implements Strategy {

	@Override
	public Double calRecharge(Double charge, RechargeTypeEnum type) {
		return charge*0.90;
	}

}
