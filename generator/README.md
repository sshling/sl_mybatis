
#====聪明、精力充沛、敢于打破常规、勇于创新、有企图心====

#开发中。。。。。。11
- 运行：tomcat7:run
- [Druid监控](http://localhost/druid/sql.html)
 IDE插件 [mybatipse](https://github.com/harawata/mybatipse)

#MyBatis优势
- 动态SQL，拒绝拼接,基于OGNL表达式。
- if    
		<if test="author != null and author.name != null">
- choose(when,otherwise),类似java的switch    
		<choose>
		    <when test="title != null">
		      AND title like #{title}
		    </when>
		    <when test="author != null and author.name != null">
		      AND author_name like #{author.name}
		    </when>
		    <otherwise>
		      AND featured = 1
		    </otherwise>
		</choose>
- trim(where,set)
	1. where 元素知道只有在一个以上的if条件有值的情况下才去插入“WHERE”子句。
	2. trim(强化版的where)  
			<trim prefix="WHERE" prefixOverrides="AND |OR ">    
		prefixOverrides属性会忽略通过`管道分隔(|)`的文本序列（注意此例中的空格也是必要的）。它带来的结果就是所有在 prefixOverrides 属性中指定的内容将被移除，并且插入 prefix 属性中指定的内容。
	3. set 被用于动态包含需要更新的列，而舍去其它的。
			<update id="updateAuthorIfNecessary">
			  update Author
			    <set>
			      <if test="username != null">username=#{username},</if>
			      <if test="password != null">password=#{password},</if>
			      <if test="email != null">email=#{email},</if>
			      <if test="bio != null">bio=#{bio}</if>
			    </set>
			  where id=#{id}
			</update>
	set元素会动态前置 SET 关键字，同时也会消除无关的逗号，等价自定义trim元素来实现：
			<trim prefix="SET" suffixOverrides=",">
- foreach  对集合进行遍历
		<select id="selectPostIn" resultType="domain.blog.Post">
		  SELECT *
		  FROM POST P
		  WHERE ID in
		  <foreach item="item" index="index" collection="list" open="(" separator="," close=")">
		        #{item}
		  </foreach>
		</select>
	**注意** 将List或者数组作为参数传给MyBatis,会自动将它包装在一个Map中，List以“list”为键，而数组的键将是“array”,实例为值。

- ${#obj.name} 和#{#obj.name} 

#MyBatis独立使用
- 建立PO，映射DB中的表
- 建立Mapper接口，即DAO，用于映射数据库操作
- 建立配置文件
	1. configuration根元素
	2. properties属性外在化
	3. settings全局性配置
	4. typeAliases：类别名
	5. typeHandlers:类型处理，即Java类型和数据库中数据类型之间的转换关系
	6. objectFactory：指定结果集对象的实例是如何创建的
	7. plugins：插件，可以修改MyBatis内部的运行机制
	8. transactionManager 事务管理器
	9. dataSource数据源
	10. mappers：指定映射文件或映射类  
- 建立映射文件
	MyBatis会将这里的SQL和Java映射接口相关联，保证在MyBatis中调用接口的时候会到数据库中执行相应的SQL。
	1. **`mapper元素的namespace必须和映射接口的全路径。`**
	2. CURD的id属性的值必须和映射接口的方法名对应
	3. 常见属性
		1. resultMap：id、type，定义DB到java类映射
		2. sql：定义script型公用SQL，
			1. set
			2. where
		3. insert：useGeneratedKeys、keyProperty
		4. select
		5. update
		6. delete

#mybatis-spring
----------
- SqlSessionFactoryBean
- MapperFactoryBean
2个接口都实现了InitializingBean和FactoryBean接口，前者会在bean初始化后调用afterPropertiessSet进行逻辑初始化；后者getBean返回的是getObject方法返回的实例。

#整合cache
##[ehcache](https://github.com/mybatis/ehcache-cache/releases)

  

#待开发：  公司saveOrUpdatexml配置  `JPA注解`、`分页解耦`;

## 支持Spring+JPA注解
## 支持Spring+XML配置
## 生成器不覆盖自定义SQL，利用XML Item判断,约定大于配置

 - 自定义SQL的命名和生成的命名重复，覆盖自定义的，[参考官方的](http://mybatis.github.io/generator/)
 - Model融合，命名冲突的，自动生成覆盖自定义的
 
 
 
## 集成分页插件[Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)


#源码分析
##核心接口
- Configuration 全局配置信息
- SqlSessionFactory 操作SqlSession的工厂接口，具体的实现类是DefaultSqlSessionFactory.openSessionFromDataSource(...)
- SqlSession 执行sql，管理事务的接口，具体的实现类是DefaultSqlSession
- Executor sql执行器，SqlSession执行sql最终是通过该接口实现的，常用的实现类有SimpleExecutor和CachingExecutor，这些实现类都使用了装饰者设计模式

##缓存作用域
- 一级缓存(事务)：SqlSession
- 二级缓存(全局)：SqlSessionFactory
- [通过源码分析MyBatis的缓存](http://www.cnblogs.com/fangjian0423/p/mybatis-cache.html)
- [详解SpringMVC请求的时候是如何找到正确的Controller](http://www.cnblogs.com/fangjian0423/p/springMVC-request-mapping.html#3095293)
- [logstash搭建日志追踪系统 ](http://www.cnblogs.com/fangjian0423/p/logstash-elasticsearch-build.html)
- [SpringMVC关于json、xml自动转换的原理研究](http://www.cnblogs.com/fangjian0423/p/springMVC-xml-json-convert.html)
- [我希望自己尽早知道的7个JavaScript怪癖](http://web.jobbole.com/69284/)





#其它参考
1.Java快速开发框架：[jeecg](http://www.jeecg.org/)
- Java快速开发框架：bboss1
- Java快速开发框架：bboss2
#学习参考	
- []()
- [es](https://github.com/sshling/es)
- [加速Java应用开发速度](http://jinnianshilongnian.iteye.com/blog/1887788)
- [Mybatis通用Mapper](http://git.oschina.net/free/Mapper)
- [Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
- [github搜索： spring+mybatis](https://github.com/search?utf8=%E2%9C%93&q=spring+mybatis)
- [mybatis-spring](https://github.com/sxyx2008/mybatis-spring) log4jdbc或者p6spy输出sql
- [jpetstore-6](https://github.com/mybatis/jpetstore-6)
- [SpringMVC-Spring-MyBatis](https://github.com/ZhibingXie/SpringMVC-Spring-MyBatis)
- [mybatis学习笔记](http://legend2011.blog.51cto.com/3018495/888848)
- [Spring，Struts的DTD验证](http://a123159521.iteye.com/blog/782198)
- [我是这样认识注解和XML的](http://jinnianshilongnian.iteye.com/blog/1879910)
- [log4j的PatternLayout参数含义](http://blog.csdn.net/guoquanyou/article/details/5689652)
### 动态数据源
- []()
- [Spring3 整合MyBatis3 配置多数据源 动态选择SqlSessionFactory](http://www.cnblogs.com/hoojo/archive/2013/10/22/dynamic_switch_sqlSessionfactory_muliteSqlSessionFactory.html)
- [整合Mybatis实现动态数据源切换教程配置](http://www.zuidaima.com/share/1816310397144064.htm)
- [多数据源动态切换的问题](http://blog.csdn.net/zl3450341/article/details/20150687)
### druid教程
- []()
- [druid简单教程](http://blog.csdn.net/yunnysunny/article/details/8657095) 包含常见配置项说明
- []()
- [springdata jpa 1.7.0.RELEASE各种查询方法总结demo](http://www.zuidaima.com/share/2086915582987264.htm)
- =====

#linux
- [/var/spool/clientmqueue目录文件清理](http://blogread.cn/it/article/7139)

#qcon
- [ArchSummit北京2014十大优秀演讲](http://www.infoq.com/cn/news/2014/12/archsummit-bj-2014-top-speech)