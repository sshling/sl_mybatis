/*
* 自动生成,请勿修改
* Copyright  © shaoling
*/package cn.shaolingweb.mybatis.daoBase.model;


import cn.shaolingweb.mybatis.daoBase.dao.BaseDao;

/**
 * 自动生成，请勿修改
 * @table demo_user   
 * @author  shaoling
 */
public class DemoUser extends BaseDao.BaseModel{
	private static final long serialVersionUID = 1L;
	/**
	 * 描述:id   
     * 字段: id  INT(10)  
     */	
	private Integer id;
	/**
	 * 描述:name   
     * 字段: name  VARCHAR(20)  
     */	
	private String name;
	/**
	 * 描述:age   
     * 字段: age  INT(10)  
     */	
	private Integer age;
	/**
	 * 描述:createDate   
     * 字段: createDate  DATETIME(19)  
     */	
	private java.util.Date createDate;
	//【非数据库字段，查询时使用】
	private java.util.Date createDateBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date createDateEnd;
	/**
	 * 描述:updateDate   
     * 字段: updateDate  TIMESTAMP(19)  
     */	
	private java.util.Date updateDate;
	//【非数据库字段，查询时使用】
	private java.util.Date updateDateBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date updateDateEnd;
	public DemoUser(){
	}

	public DemoUser(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
    public void setCreateDateBegin(java.util.Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	
	public java.util.Date getCreateDateBegin() {
		return this.createDateBegin;
	}
	
	public void setCreateDateEnd(java.util.Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	
	public java.util.Date getCreateDateEnd() {
		return this.createDateEnd;
	}	
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}
	
    public void setUpdateDateBegin(java.util.Date updateDateBegin) {
		this.updateDateBegin = updateDateBegin;
	}
	
	public java.util.Date getUpdateDateBegin() {
		return this.updateDateBegin;
	}
	
	public void setUpdateDateEnd(java.util.Date updateDateEnd) {
		this.updateDateEnd = updateDateEnd;
	}
	
	public java.util.Date getUpdateDateEnd() {
		return this.updateDateEnd;
	}	
	
}

