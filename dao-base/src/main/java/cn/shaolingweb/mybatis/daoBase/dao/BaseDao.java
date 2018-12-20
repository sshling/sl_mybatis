package cn.shaolingweb.mybatis.daoBase.dao;

import cn.shaolingweb.mybatis.daoBase.service.BaseService;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatis DAO通用操作类
 * 
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T, PK extends Serializable> {

	int BATCH_NUM = 100;
	
	/**
	 * 保存单一实体
	 * 
	 * @param obj ，若采用自增，保存后则可以从该对象获取数据库设置的自增值
	 * @return 受影响的行数
	 */
	int insert(T obj);
	
	/**
	 * 批量插入,自增ID会更新到入参集合的每一个元素
	 * 
	 * @description 基本原生SQL的数据库插入，性能稍好
	 * @param cls
	 * @param domainList
	 * @return The number of rows
	 */
	int insertBatch(Class<T> cls, List<T> domainList);
	
	/**
	 * 批量插入，基于JDBC按批次提交，性能稍差于 {@link #insertBatch(Class, List)}
	 * 
	 * @param domainList 需要更新的集合
	 * @param count 表示多少笔数据提交一次
	 * @return The number of rows affected
	 */
	int insertBatch(Class<T> cls, List<T> domainList, Integer count);
	
	/**
	 * 按条件查询实体,不分页
	 * 
	 * @param obj
	 * @return
	 */
	List<T> findByCondition(T obj);
	
	/**
	 * 按条件查询实体并分页
	 * 
	 * @param obj
	 * @return
	 */
	List<T> findByCondition(T obj, int start, int limit);
	
	/**
	 * @description 分页查询
	 * @param obj 查询对象
	 * @param pager 分页对象,若为null,查询所有
	 * @return 查询结果
	 * @throws
	 */
	List<T> findByCondition(T obj, BaseService.Pager pager);
	
	/**
	 * 按主键查询
	 * 
	 * @param pk
	 * @return
	 */
	T findByPK(PK pk, Class<T> cls);
	
	/**
	 * 更新实体，以主键、或逗号分隔的主键
	 * 
	 * @param obj
	 * @return The number of rows affected by the update.
	 */
	int update(T obj);
	
	int updateCustomSql(Class<T> cls, String customSql);
	
	/**
	 * 按查询条件更新实体，不局限于依据主键更新
	 * 
	 * @param queryObj 查询条件
	 * @param updateObj 更新对象
	 * @param ignoreProperList 从updateObj拷贝更新字段的值 到 queryObj查询返回的对象中， <br>
	 *                忽略拷贝的字段(默认为id),一般忽略主键,以主键为条件进行更新操作
	 * @return The number of rows affected by the update.
	 */
	int updateByCondition(Class<T> cls, T queryObj, T updateObj, List<String> ignoreProperList);
	
	/**
	 * 按主键删除实体
	 * 
	 * @param pk
	 * @param cls
	 * @return The number of rows affected by the delete.
	 */
	int delete(PK pk, Class<T> cls);
	
	/**
	 * 自定义删除操作的SQL
	 * 
	 * @param cls
	 * @param customSql 自定义SQL
	 * @return shaoling
	 */
	int deleteCustomSql(Class<T> cls, String customSql);
	
	/**
	 * 按条件删除
	 * 
	 * @param obj
	 * @return
	 */
	int deleteByCondition(T obj);
	
	/**
	 * 根据ID串，进行批量删除
	 * 
	 * @param ids id字符串 如: 1,2,3
	 * @param cls 实体类型
	 * @return The number of rows affected by the delete.
	 */
	int deleteByIds(String ids, Class<T> cls);
	
	/**
	 * 按条件查询总记录数
	 * 
	 * @param object
	 * @return
	 */
	Integer getTotalCount(T object);
	
	/**
	 * 批量更新
	 * @param domainList 需要更新的集合
	 * @param count 多少笔数据提交一次
	 * @return The number of rows affected
	 */
	int updateBatch(Class<T> cls, List<T> domainList, int count);
	
	/**
	 * 取得当前SqlManClient
	 * 
	 * @Title: getCurSqlSessionTemplate
	 * @Description: TODO描述
	 * @param @return 设定文件
	 * @return SqlSessionTemplate 返回类型
	 * @throws
	 */
	SqlSessionTemplate getCurSqlSessionTemplate();

	/**
	 * <br>1. 查询特定列：逗号分隔列  {@link #setSelectColumns(String)}
	 * <br>2. 按特定列排序：逗号分隔 {@link #setSortColumns(String)}
	 * <br>3. 基于主键删除、更新  {@link #setIds(String)}
	 * <br>4. 模糊查询特定列 {@link #setSelectType(String...)}
	 * <br>5. 自定义where {@link #setWhere(String)}
	 * <br>6. 自定义where {@link #setExtraConditions(String)}
	 */
    class BaseModel implements Serializable {

		private static final long serialVersionUID = 1L;
		/* 支持模糊查询的字段k-v，k-字段，v=1 */
		private Map<String, String> selectType = new HashMap<>();
		/* 排序字段,排序列表，逗号分隔 */
		private String sortColumns;
		/* 分组字段列表，逗号分隔 */
		private String groupColumns;
		/* 查询特定的列，用于查询少数列的场景 */
		private String selectColumns;
		/* 主键ID串，用于基于单主键批量删除 */
		private String ids;
		/* 完全自定义SQL */
		private String customSql;
		/* 自定义设定where条件 参考{@link #extraConditions} ，性能好些 */
		private String where;
		/**
		 * 原有where之外自定义的SQL条件
		 * <pre>
		 *  如"and isDelete=1 "，若无其它查询条件，会自动去除and
		 * </pre>
		 */
		private String extraConditions;

		public String getCustomSql() {
			return customSql;
		}

		public void setCustomSql(String customSql) {
			this.customSql = customSql;
		}

		public String getGroupColumns() {
			return groupColumns;
		}

		public void setGroupColumns(String groupColumns) {
			this.groupColumns = groupColumns;
		}

		public String getExtraConditions() {
			return extraConditions;
		}

		public void setExtraConditions(String extraConditions) {
			this.extraConditions = extraConditions;
		}

		public String getWhere() {
			return where;
		}

		public void setWhere(String where) {
			this.where = where;
		}

		public String getSelectColumns() {
			return selectColumns;
		}

		public void setSelectColumns(String selectColumns) {
			this.selectColumns = selectColumns;
		}

		public String getIds() {
			return ids;
		}

		/**
		 * 设定基于多个主键的操作，逗号分隔
		 */
		public void setIds(String ids) {
			this.ids = ids;
		}

		public Map<String, String> getSelectType() {
			return selectType;
		}

		public void setSelectType(Map<String, String> selectType) {
			this.selectType = selectType;
		}

		/**
		 * 设置需要模糊查询的表字段列表
		 */
		public void setSelectType(String... fields) {
			for (String field : fields) {
				selectType.put(field, "1");
			}
		}

		public String getSortColumns() {
			return sortColumns;
		}

		public void setSortColumns(String sortColumns) {
			this.sortColumns = sortColumns;
		}
	}

}
