package com.wcx.springboot.demo.xiangxue.jvm.performance.createobj;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FastPerson {
	private final Date birthDate;

	public FastPerson(Date birthDate) {
		this.birthDate = new Date(birthDate.getTime());
	}
	
	private static final Date Begin;
	//private static final Date End;
	//共同的代码放入到静态代码段中
	static {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.set(1990, Calendar.JANUARY,1,0,0,0);
		Begin = cal.getTime();
		//.....
	}

	
	public boolean is90s() {
		//比较begin和end
		//.....
		return false;
	}

}
