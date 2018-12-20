/*
* 自动生成,请勿修改
* Copyright  © shaoling
*/
package cn.shaolingweb.model;

import cn.shaolingweb.mybatis.daoBase.dao.BaseDao;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 自动生成，请勿修改
 * @table demo_user   
 * @author  shaoling
 */
public class DemoUser extends BaseDao.BaseModel{
	private static final long serialVersionUID = 1L;
	/**
	 * 描述:id   
     * 字段: id  BIGINT(19)  
     */	
	private java.lang.Long id;
	/**
	 * 描述:name   
     * 字段: name  VARCHAR(20)  
     */	
	private java.lang.String name;
	/**
	 * 描述:age   
     * 字段: age  INT(10)  
     */	
	private java.lang.Integer age;
	/**
	 * 描述:身高,short类型   
     * 字段: height  SMALLINT(5)  
     */	
	private java.lang.Short height;
	/**
	 * 描述:测试属性,tinyint , The signed range is -128 to 127. The unsigned range is 0 to 255.   
     * 字段: attr_tinyint  TINYINT(3)  
     */	
	private Integer attrTinyint;
	/**
	 * 描述:性别:字符串枚举   
     * 字段: sex  ENUM(2)  
     */	
	private java.lang.String sex;
	/**
	 * 描述:测试熟悉,byte, zero is considered false. Nonzero values are considered true   
     * 字段: attr_bit  BIT(1)  
     */	
	private java.lang.Boolean attrBit;
	/**
	 * 描述:是否是热门,boolean类型的值   
     * 字段: is_hot  BIT(0)  
     */	
	private java.lang.Boolean isHot;
	/**
	 * 描述:metaData={"type":1,"fmt":"yyyy-MM-dd HH:mm:ss"}###   创建日期   
     * 字段: createDate  DATETIME(19)  
     */	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	//【非数据库字段，查询时使用】
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDateBegin;
	//【非数据库字段，查询时使用】
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setAge(java.lang.Integer age) {
		this.age = age;
	}
	
	public java.lang.Integer getAge() {
		return this.age;
	}
	
	public void setHeight(java.lang.Short height) {
		this.height = height;
	}
	
	public java.lang.Short getHeight() {
		return this.height;
	}
	
	public void setAttrTinyint(Integer attrTinyint) {
		this.attrTinyint = attrTinyint;
	}
	
	public Integer getAttrTinyint() {
		return this.attrTinyint;
	}
	
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	
	public void setAttrBit(java.lang.Boolean attrBit) {
		this.attrBit = attrBit;
	}
	
	public java.lang.Boolean getAttrBit() {
		return this.attrBit;
	}
	
	public void setIsHot(java.lang.Boolean isHot) {
		this.isHot = isHot;
	}
	
	public java.lang.Boolean getIsHot() {
		return this.isHot;
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

