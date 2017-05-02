package com.wangzi.test.strategy;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class StrategyFactory {
	private static StrategyFactory factory = new StrategyFactory();

	private StrategyFactory(){

	}
	@SuppressWarnings("rawtypes")
	private static Map strategyMap = new HashMap<>();
	static{
		strategyMap.put(RechargeTypeEnum.E_BANK.value(), new EBankStrategy());
		strategyMap.put(RechargeTypeEnum.BUSI_ACCOUNTS.value(), new BusiAcctStrategy());
		strategyMap.put(RechargeTypeEnum.MOBILE.value(), new MobileStrategy());
		strategyMap.put(RechargeTypeEnum.CARD_RECHARGE.value(), new CardStrategy());
	}
	public Strategy creator(Integer type){
		return (Strategy) strategyMap.get(type);
	}
	public static StrategyFactory getInstance(){
		return factory;
	}
}
