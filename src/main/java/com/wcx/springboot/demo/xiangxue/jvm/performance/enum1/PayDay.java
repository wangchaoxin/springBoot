package com.wcx.springboot.demo.xiangxue.jvm.performance.enum1;

/**
 *
 *类说明：加班费计算,工作日加班2倍，节假日3倍
 */
enum PayDay {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY,WUYI;
	
	private static final int HOURS_WORK = 2;
	private static final int HOURS_REST = 3;
	
	//超时时间
	double pay(double hoursOvertime) {
		switch(this) {
		case SATURDAY:case SUNDAY:
			return hoursOvertime*HOURS_REST;
		default:
			return hoursOvertime*HOURS_WORK;
		}
	}

	
}
