package cn.shaolingweb.demo.curd.service;

import java.io.Serializable;
import java.util.List;

import cn.shaolingweb.mybatis.daoBase.service.BaseService;
import org.apache.ibatis.logging.LogFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.shaolingweb.demo.curd.model.User;

import com.alibaba.fastjson.JSON;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springExt/spring-BaseServiceTest.xml")
public class BaseServiceTest {
	private Logger logger=Logger.getLogger(BaseServiceTest.class);
	@Autowired
	private BaseService<User, Serializable> baseService;
	
	@Test
	public void testQuery() {
		logger.info("........测试查询........");
			User obj=new User();
			obj.setUsername("admin");
			List<User> users= baseService.findByCondition(obj);
			System.out.println("结果_基本序列化:\r\n"+JSON.toJSONString(users));
//			System.out.println("结果_使用单引号:\r\n"+JSON.toJSONString(users,SerializerFeature.UseSingleQuotes));
			//默认日志格式化为long,
//			System.out.println("结果_日期格式化:默认年月日时分秒\r\n"+JSON.toJSONString(users,SerializerFeature.WriteDateUseDateFormat));
//			System.out.println("结果_日期格式化:自定义日期格式\r\n"+JSON.toJSONStringWithDateFormat(users, FrameworkDateUtil.FORMAT_SSS));
			//使用WriteClassName，保留类型信息到JSON串，便于正确反序列化
//			System.out.println("结果_输出类型信息\r\n"+JSON.toJSONString(users,
//					SerializerFeature.WriteDateUseDateFormat
//					,SerializerFeature.WriteClassName
//					));
			//循环引用：引用通过$ref表示
			//"$ref":".."  上一级   
//			"$ref":"@"  当前对象，也就是自引用   "$ref":"$"  根对象   
//			"$ref":"$.children.0"  基于路径的引用，相当于 root.getChildren().get(0)
			//美化输出
//			System.out.println("结果:美化输出\r\n"+JSON.toJSONString(users,SerializerFeature.PrettyFormat,SerializerFeature.WriteDateUseDateFormat));
//		fail("Not yet implemented");
	}
	@Test
	public void testAdd() {
		LogFactory.useLog4JLogging();
		User user=new User();
		user.setUsername("shaoling1130");
		user.setEmail("shaoling@jd.com");
		int i=baseService.insert(user);
		System.out.println("i="+i);
		System.out.println(user.getUserId());
	}
	@Test
	public void testQueryByPK() {
		User user=baseService.findByPK(23, User.class);
		System.out.println(user.getUserId());
		System.out.println(user.getUsername());
	}
	
	
}
