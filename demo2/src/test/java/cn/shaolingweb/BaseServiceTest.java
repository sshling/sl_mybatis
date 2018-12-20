package cn.shaolingweb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.shaolingweb.model.DemoUser;
import cn.shaolingweb.mybatis.daoBase.service.BaseService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class BaseServiceTest {
	private Logger logger=Logger.getLogger(BaseServiceTest.class);

	@Autowired
	private BaseService<DemoUser, Serializable> baseService;

	@Test
	public void  single_save_query(){
		DemoUser obj = new DemoUser();

		obj.setName("t"+new Random().nextInt(100));
		obj.setAge(2);
		obj.setCreateDate(new Date());

		int num = baseService.insert(obj);
		Assert.assertTrue(num==1);
		logger.info("插入["+num+"]条记录,自动生产ID:"+obj.getId());
		DemoUser result = baseService.findByPK(obj.getId(), DemoUser.class);
		logger.info("查询结果:"+ JSON.toJSONString(result));
	}


	@Test
	public void  batch_save_update(){
		List<DemoUser> users = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			DemoUser demoUser = new DemoUser();
			demoUser.setAge(i*2);
			demoUser.setName("user"+i);
			users.add(demoUser);
		}
		int nums = baseService.insertBatch(DemoUser.class, users);
		logger.info("批量插入记录条数:"+nums+"->"+JSON.toJSONString(users));
		for (DemoUser user : users) {
			user.setName(user.getName()+"-update by test");
		}
		logger.info("更新前:"+JSON.toJSONString(users));
		nums = baseService.updateBatch(DemoUser.class, users);
		logger.info("批量更新记录条数:"+nums);
	}
	@Test
	public void  del_all_oneByOne(){
		List<DemoUser> users = baseService.findByCondition(new DemoUser());
		for (DemoUser user : users) {
			logger.info("即将删除用户:"+JSON.toJSONString(user));
			int delNums = baseService.delete(user.getId(), DemoUser.class);
			logger.info("成功删除条数:"+delNums);
		}
	}



	//todo 批量写入和更新,中间一个失败,则都失败

	//todo 事务性,一个写入,一个删除不存在的,写之前的写入将失败



	@Test
	public void testQuery() {
		logger.info("........测试查询........");
	}

	
	
}
