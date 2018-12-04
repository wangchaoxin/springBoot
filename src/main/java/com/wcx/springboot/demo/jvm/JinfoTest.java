package com.wcx.springboot.demo.jvm;

import java.util.Map;

public class JinfoTest {
	
	public static void main(String[] args) {
		while(true)
	       {
	           byte[] b = null;
	           for (int i = 0; i < 10; i++)
	               b = new byte[1 * 1024 * 1024];
	 
	           try {
	               Thread.sleep(2000);
	           } catch (InterruptedException e) {
	               e.printStackTrace();
	           }
	           
	           //打印所有线程信息，和jstack差不多
	           Map<Thread, StackTraceElement[]> threadMap 
	           =  Thread.getAllStackTraces();
	           for(Map.Entry<Thread, StackTraceElement[]> entry:
	        	   threadMap.entrySet()) {
	        	   Thread t = entry.getKey();
	        	   StackTraceElement[] ss = entry.getValue();
	        	   System.out.println(t.getName()+"-"+t.getId());
	        	   for(StackTraceElement s:ss) {
	        		   System.out.println(s);
	        	   }
	        	   
	           }
	           	           
	       }
	}

}
