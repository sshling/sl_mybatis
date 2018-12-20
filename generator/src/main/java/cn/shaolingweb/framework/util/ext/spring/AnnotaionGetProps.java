package cn.shaolingweb.framework.util.ext.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 基于注解获取属性文件中的值
 * @author  shaoling@shaolingweb.cn
 */
@Component
public class AnnotaionGetProps {
	/**
	 * 要想获取到context:property-placeholder加载的属性值，
	 * 必须使用context:component-scan扫描本类，使用Bean标签无法加载的
	 */
	@Value("${realName}")
	private String realName;
	
	public String getRealName() {
		return realName;
	}
}
