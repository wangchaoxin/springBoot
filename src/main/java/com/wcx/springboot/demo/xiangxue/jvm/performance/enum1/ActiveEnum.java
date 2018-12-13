package com.wcx.springboot.demo.xiangxue.jvm.performance.enum1;

/**
 *类说明：枚举和行为绑定
 */
public class ActiveEnum {
	
	public enum NormalActive{
		PLUS,MINUS,MULTI,DIVIDS,DIFFER;
		
		double oper(double x,double y) {
			//swich语句
			switch(this) {
			case PLUS:return x+y;
			case MINUS:return x-y;
			case MULTI:return x*y;
			case DIVIDS:return x/y;
			}
			throw new UnsupportedOperationException();
		}		
	}
	
	public enum BetterActive{
		PLUS {
			@Override
			double oper(double x, double y) {
				return x+y;
			}
		},MINUS {
			@Override
			double oper(double x, double y) {
				return x-y;
			}
		};
		//可以定义abstract方法
		abstract double oper(double x,double y);
		
//		double oper(double x,double y) {
//			switch(this) {
//			case PLUS:return x+y;
//			case MINUS:return x-y;
//			case MULTI:return x*y;
//			case DIVIDS:return x/y;
//			
//			//default:
//			}
//			throw new UnsupportedOperationException();
//		}		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(NormalActive.PLUS.oper(0.1, 0.2));
	}

}
