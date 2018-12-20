package cn.shaolingweb.mybatis.daoBase.service;

import cn.shaolingweb.mybatis.daoBase.dao.BaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("baseService")
@Transactional
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    @Qualifier("myBatisBaseDao")
    private BaseDao<T, Serializable> baseDao;

    @Override
    public int insert(T obj) throws RuntimeException {
        return baseDao.insert(obj);
    }

    @Override
    public List<T> findByCondition(T obj) {
        return baseDao.findByCondition(obj);
    }

    @Override
    public List<T> findByCondition(T obj, int start, int limit) {
        return baseDao.findByCondition(obj, start, limit);
    }

    @Override
    public List<T> findByCondition(T obj, Pager pager) {
        return baseDao.findByCondition(obj, pager);
    }

    @Override
    public T findByPK(PK pk, Class<T> cls) {
        return baseDao.findByPK(pk, cls);
    }

    @Override
    public int update(T obj) {
        return baseDao.update(obj);
    }

    @Override
    public int updateByCondition(Class<T> cls, T queryObj, T updateObj, List<String> ignoreProperList) {
        return baseDao.updateByCondition(cls, queryObj, updateObj, ignoreProperList);
    }

    @Override
    public int delete(PK pk, Class<T> cls) {
        return baseDao.delete(pk, cls);
    }

    public int deleteByCondition(T obj) {
        return baseDao.deleteByCondition(obj);
    }

    @Override
    public int deleteByIds(String ids, Class<T> cls) {
        return baseDao.deleteByIds(ids, cls);
    }

    @Override
    public Integer getTotalCount(T object) {
        return baseDao.getTotalCount(object);
    }

    @Override
    public int insertBatch(Class<T> cls, List<T> domainList, Integer count) {
        return baseDao.insertBatch(cls, domainList, count);
    }

    @Override
    public int insertBatch(Class<T> cls, List<T> domainList) {
        return baseDao.insertBatch(cls, domainList);
    }

    @Override
    public int updateBatch(Class<T> cls, List<T> domainList, int count) {
        return baseDao.updateBatch(cls, domainList, count);
    }

    @Override
    public int updateBatch(Class<T> cls, List<T> domainList) {
        return updateBatch(cls,domainList,0);//0采用默认值
    }

    @Override
    public SqlSessionTemplate getCurSqlSessionTemplate() {
        return baseDao.getCurSqlSessionTemplate();
    }
}
