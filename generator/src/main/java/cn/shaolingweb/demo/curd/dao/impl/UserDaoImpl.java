/*
* Copyright  © shaoling
*/package cn.shaolingweb.demo.curd.dao.impl;


import cn.shaolingweb.mybatis.daoBase.dao.MyBatisBaseDaoImpl;
import org.springframework.stereotype.Repository;
import cn.shaolingweb.demo.curd.model.User;
import cn.shaolingweb.demo.curd.dao.UserDao;

@Repository
public class UserDaoImpl extends MyBatisBaseDaoImpl<User,Integer> implements  UserDao{
	
}
