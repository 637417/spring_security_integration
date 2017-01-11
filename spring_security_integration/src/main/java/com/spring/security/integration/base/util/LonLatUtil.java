package com.spring.security.integration.base.util;

import java.text.DecimalFormat;


public class LonLatUtil {
	private static DecimalFormat digits=new DecimalFormat("0.00000000");//取8位小数，如要取多位，写多几个0上去 
	
	/**
	 * 
	 * @title: getLon
	 * @category: 将经度的度数转化为度分秒
	 * @author: hejiang@zhichenhaixin.com 2015年11月6日
	 * @return
	 */
	public static String getLonOrLat(String du,int length){
		if(StringUtil.isBlank(du)){
			return du;
		}
		
		String edu=du.substring(0,length);//取度
		int edu2=Integer.parseInt(edu);
		
		String fen=du.substring(length,du.length());
		double fen2=Double.parseDouble(fen);
		double fen3=fen2/60;
		double fen4 = edu2+fen3;
		String es = digits.format(fen4);//取八位小数
		return es;
		
		/*
		double yuan=Double.parseDouble(du);
		double shu=yuan/100;
		int zdu =(int)Math.floor(shu);//取整数
		
		BigDecimal b1 = new BigDecimal(Double.toString(shu));
		BigDecimal b2 = new BigDecimal(Integer.toString(zdu));
		double fPoint = b1.subtract(b2).floatValue();//取小数
		
		double fen=fPoint/60;
		
		double endshu = zdu+fen;//最后的度分
		String es = digits.format(endshu);//取八位小数
		System.out.println(es);
		return es;
		*/
	}
	
	public static void main(String[] args) {
		//11401.51283
		//getLonOrLat("114+(11.51283/60)");
		//getLonOrLat("11401.51283",3);
		getLonOrLat("2232.12213",2);
	}
	
}
