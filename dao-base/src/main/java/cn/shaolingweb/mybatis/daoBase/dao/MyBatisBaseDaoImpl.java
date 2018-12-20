package cn.shaolingweb.mybatis.daoBase.dao;

import cn.shaolingweb.mybatis.daoBase.service.BaseService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("myBatisBaseDao")
@Transactional
public class MyBatisBaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	private static Logger logger = Logger.getLogger(MyBatisBaseDaoImpl.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/** 根据条件 分页查询 */
	public String COUNT = ".auto_findPage_count";
	/**  根据ID 删除  */
	public String DELETE = ".auto_delete";
	public String DELETECUSTOMSQL = ".deleteCustomSql";
	public String DELETE_BY_CONDITION = ".deleteByCondition";
	/**  根据ID 查询  */
	public String GETBYID = ".auto_getById";
	/**  插入  */
	public String INSERT = ".auto_insert";
	/**  批量插入  */
	public String INSERT_BATCH = ".auto_insertBatch";
	/**  根据条件 分页查询  */
	public String PAGESELECT = ".auto_findPage";//todo byCondition,与方法同名
	public String UPDATECUSTOMSQL = ".auto_updateCustomSql";
	/**  更新 */
	public String UPDATE = ".auto_update";
	
	@Override
	public int insert(T obj) {
		if (obj == null) {
			throw new RuntimeException(" obj can't null!");
		}
		return sqlSessionTemplate.insert(obj.getClass().getName() + INSERT, obj);
	}
	
	@Override
	public int insertBatch(Class<T> cls, List<T> domainList) {
		return sqlSessionTemplate.insert(cls.getName() + INSERT_BATCH, domainList);
	}
	
	@Override
	public int insertBatch(Class<T> cls, List<T> domainList, Integer count) {
		SqlSession sqlSession = null;
		int total=0;
		if (count==null) {
			count=BATCH_NUM;
		}
		try {
			if (domainList == null) {
				return 0;
			}
			sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int num = 0;
			for (T t : domainList) {
				sqlSession.insert(cls.getName() + INSERT, t);
				num++;
				total++;
				if (count == num) {
					sqlSession.commit();
					num = 0;
				}
			}
			sqlSession.commit();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(true);
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return total;
	}
	@Override
	public int deleteByCondition(T obj) {
		return sqlSessionTemplate.delete(obj.getClass().getName() + DELETE_BY_CONDITION, obj);
	}
	@Override
	public int delete(PK pk, Class<T> cls) {
		if (pk == null) {
			throw new RuntimeException(" pk can't null!");
		}
		return sqlSessionTemplate.delete(cls.getName() + DELETE, pk);
	}
	 
	@Override
	public int deleteCustomSql(Class<T> cls, String customSql) {
		Map<String, String> map= new HashMap<>();
		map.put("customSql", customSql);
		return sqlSessionTemplate.delete(cls.getCanonicalName()+DELETECUSTOMSQL, map);
	}
	@Override
	public int deleteByIds(String ids, Class<T> cls) {
		Map<String, Object> map = new HashMap<>();
		map.put("ids", ids);
		return getCurSqlSessionTemplate().delete(cls.getName() + ".delete_batch_string", map);
	}
	
	@Override
	public List<T> findByCondition(T obj) {
		if (obj == null) {
			throw new RuntimeException(" condition can't null!");
		}
		return (List<T>) sqlSessionTemplate.selectList(obj.getClass().getName() + PAGESELECT, obj);
	}
	
	@Override
	public List<T> findByCondition(T obj, int offset, int limit) {
		if (obj == null) {
			throw new RuntimeException(" obj can't null!");
		}
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (List<T>) sqlSessionTemplate.selectList(obj.getClass().getName() + PAGESELECT, obj, rowBounds);
	}
	
	@Override
	public List<T> findByCondition(T obj, BaseService.Pager pager) {
		if (obj == null) {
			throw new RuntimeException(" obj can't null!");
		}
		if (pager != null) {
			return this.findByCondition(obj, pager.getStartRow(), pager.getPageSize());
		} else {
			return this.findByCondition(obj);
		}
	}
	
	@Override
	public T findByPK(PK pk, Class<T> cls) {
		if (pk == null) {
			throw new RuntimeException(" pk can't null!");
		}
		return (T) sqlSessionTemplate.selectOne(cls.getName() + GETBYID, pk);
		
	}
	
	@Override
	public SqlSessionTemplate getCurSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	@Override
	public Integer getTotalCount(T object) {
		if (object == null) {
			throw new RuntimeException(" condition can't null!");
		}
		Object obj = sqlSessionTemplate.selectOne(object.getClass().getName() + COUNT, object);
		if (obj != null) {
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}

	@Override
	public int update(T obj) {
		if (obj == null) {
			throw new RuntimeException(" obj can't null!");
		}
		return sqlSessionTemplate.update(obj.getClass().getName() + UPDATE, obj);
	}

	@Override
	public int updateCustomSql(Class<T> cls,String customSql) {
		Map<String, String> map= new HashMap<>();
		map.put("customSql", customSql);
		return sqlSessionTemplate.update(cls.getCanonicalName()+UPDATECUSTOMSQL,map);
	}
	@Override
	public int updateByCondition(Class<T> cls,T queryObj, T updateObj,List<String> ignoreProperList) {
		List<T> list=findByCondition(queryObj);
		//todo list , always !=null?
		List<T> queryList= new ArrayList<>(list.size());
		if (logger.isDebugEnabled()) {
			logger.debug("queryObj:"+JSON.toJSONString(queryObj, SerializerFeature.WriteDateUseDateFormat)
					+" db:"+JSON.toJSONString(list,SerializerFeature.WriteDateUseDateFormat)
					);
		}
		int rtn=0;
		if (list != null && list.size() > 0) {
			for (T obj : list) {
				String[] ignoreProperties=null;
				if (ignoreProperList==null) {
					ignoreProperties= new String[]{"id"};
				}else {
					ignoreProperties=ignoreProperList.toArray(ignoreProperties);
				}
				BeanUtils.copyProperties(updateObj, obj,ignoreProperties);
				queryList.add(obj);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("updateBatch parameter:"+JSON.toJSONString(queryList,SerializerFeature.WriteDateUseDateFormat));
			}	
			rtn=updateBatch(cls, queryList, BATCH_NUM);
		}
		return rtn;
	}
	
	@Override
	public int updateBatch(Class<T> cls, List<T> domainList, int count) {
		if (domainList == null) {
			return 0;
		}
		int total=0;
		SqlSession sqlSession = null;
		if (count==0) {
			count=BATCH_NUM;
		}
		int num = 0;
		try {
			sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			for (T t : domainList) {
				sqlSession.update(cls.getName() + UPDATE, t);
				num++;
				if (count == num) {
					sqlSession.commit();
					num = 0;
				}
				total++;
			}
			sqlSession.commit();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(true);
			}
			logger.error(e.getMessage(), e.getCause());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return total;
	}
}
