package cn.shaolingweb.framework.util.ext.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.shaolingweb.demo.model.UserInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springExt/spring-PropertyPlaceholderConfigurerTest.xml")
public class PropertyPlaceholderConfigurerExtTest {
	
	@Autowired
	private UserInfo userInfo;
	//注意：写法不是@Value("${util.realName}")
	@Value("#{props['util.realName']}")
	private String realName;
	@Autowired
	private AnnotaionGetProps annotaionGetProps;
	
	@Test
	public void test() {
		System.out.println("不扫描直接使用："+realName);
		System.out.println("Bean组件使用Value注入属性值："+annotaionGetProps.getRealName());
		 System.out.println("xml里获取属性值："+userInfo.getUsername());
	}
}
