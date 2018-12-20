package cn.shaolingweb.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author  shaoling@shaolingweb.cn
 */
public abstract class FrameworkStringUtil {
	
	/**是否与指定的正则匹配,用于学习，一般使用正则性能不怎么样。
	 * @param regex 正则表达式
	 * @param flag 正则标志开关，默认不区分大小写
	 * @param input 待匹配的字符串
	 * @return true-匹配，false-不匹配
	 */
	public static boolean matchRegex(String regex,Integer flag,String input) {
		if (flag==null) {
			flag=Pattern.CASE_INSENSITIVE;
		}
		Pattern pattern=Pattern.compile(regex, flag);
		Matcher matcher=pattern.matcher(input);
		matcher.find();
		return matcher.matches();
	}
}
