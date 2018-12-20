package cn.shaolingweb.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

/**
 * 提供JavaBean的相关操作：
 * <br>1 动态地设定、获取对象的属性：直接setter/getter一个属性的值;
 * 	<br>分3类：简单、索引类型、Map类型
 * <br>2 把HTTP请求中的字符串绑定到对象的属性中
 * 
 * @author  shaoling@shaolingweb.cn
 * @see {@link http://commons.apache.org/proper/commons-beanutils }
 */
public abstract class BeanUtil {
	
	private static Logger logger=Logger.getLogger(BeanUtil.class);
	
	/** Map中的键值对复制到对象的属性中，对象属性须有setter/getter；
	 * <br>应用场景：HTTP的request参数映射到Model中
	 * @param bean
	 * @param properties
	 */
	public static void populate(Object bean, Map<String, ? extends Object> properties) {
		try {
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	 /**给对象的属性设置值
	 * @param bean
	 * @param name Property name (can be nested/indexed/mapped/combo)
	 * TODO 
	 * @param value
	 * @ref  BeanUtilsBean 
	 */
	public static void setProperty(Object bean, String name, Object value) {
		 try {
			BeanUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error(e);
			e.printStackTrace();
		}
	 }
 
	public static void copyProperties(Object target, Object source,String ... ignoreProperties) {
		try {
			org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
