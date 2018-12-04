package com.wcx.springboot.demo.jvm.performance.createobj;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SlowPerson {
	private final Date birthDate;//生日

	public SlowPerson(Date birthDate) {
		this.birthDate = new Date(birthDate.getTime());
	}

	/**
	 * 每次都new的情况放入到静态代码段
	 * @return
	 */
	public boolean is90s() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.set(1990, Calendar.JANUARY,1,0,0,0);
		Date begin = cal.getTime();
		//.....
		return false;
	}

}
