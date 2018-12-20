package cn.shaolingweb.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/**
 * 框架级别的日期工具操作类
 * @author  shaoling@shaolingweb.cn
 */
public abstract class DateUtil{
	
	private static Logger logger=Logger.getLogger(DateUtil.class);
	/** 常见格式化样式 ：年月日时分秒 */
	public static String FORMAT="yyyy-MM-dd HH:mm:ss";
	/** 常见格式化样式 ：年月日时分秒.毫秒 */
	public static String FORMAT_SSS=FORMAT+".SSS";
	/**
	 * 按照Calendar方式格式化
	 */
	public static String DATEFORMAT_CAL= "yyyy-M-d HH:m:s";

	/**求交集，起止
	 * @param dateStr ,目前支持2组
	 * @return null无交集，等于算有交集
	*  shaoling
	 */
	public static DatePair getIntersect(Date... dateArr) {
		Date g1Start=dateArr[0];
		Date g1End=dateArr[1];
		Date g2Start=dateArr[2];
		Date g2End=dateArr[3];
		boolean f1=g1Start.compareTo(g2End)>0;//无交集情况1
		boolean f2=g1End.compareTo(g2Start)<0;//无交集情况1
		if (f1||f2) {
			return null;
		}
		DatePair datePair=new DatePair();
		if (g1Start.compareTo(g2Start)<0) {
			datePair.setBeginDate(g1Start);
		}else {
			datePair.setEndDate(g2Start);
		}
		if (g1End.compareTo(g2End)>0) {
			datePair.setEndDate(g1End);
		}else {
			datePair.setEndDate(g2End);
		}
		return datePair;
	}
	public static String formatNow() {
		Date now=new Date();
		return format(now, FORMAT);
	}
	public static String formatNow(String format) {
		Date now=new Date();
		return format(now, format);
	}
	
	/**
	 * 格式化日期
	 * @param date 若为null，则使用当前时间
	 * @param format，若为null，则采用默认的{@link DateUtil.FORMAT}
	 * @return
	 */
	public static String format(Date date,String format) {
		DateFormat sdf=new SimpleDateFormat(format==null?FORMAT:format);
		if (date==null) {
			return sdf.format(new Date());
		}
		return sdf.format(date);
	}
	public static Date parse(String dateStr,String format) {
		Assert.notNull(dateStr);
		DateFormat sdf=new SimpleDateFormat(format==null?FORMAT:format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e.getCause());
		}
		return null;
	}
}