/*
* Copyright  © shaoling
*/package cn.shaolingweb.demo.curd.model;


import cn.shaolingweb.mybatis.daoBase.dao.BaseDao;

/**
 * 自动生成，请勿修改
 * @table jc_user   
 * @author  shaoling
 */
public class User extends BaseDao.BaseModel {
	private static final long serialVersionUID = 1L;
	/**描述:userId   
        * 字段: user_id  INT(10)  
       */	
	private java.lang.Integer userId;
	/**描述:groupId   
        * 字段: group_id  INT(10)  
       */	
	private java.lang.Integer groupId;
	/**描述:用户名   
        * 字段: username  VARCHAR(100)  
       */	
	private java.lang.String username;
	/**描述:邮箱   
        * 字段: email  VARCHAR(100)  
       */	
	private java.lang.String email;
	/**描述:注册时间   
        * 字段: register_time  DATETIME(19)  
       */	
	private java.util.Date registerTime;
	//【非数据库字段，查询时使用】
	private java.util.Date registerTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date registerTimeEnd;
	/**描述:注册IP   
        * 字段: register_ip  VARCHAR(50)  
       */	
	private java.lang.String registerIp;
	/**描述:最后登录时间   
        * 字段: last_login_time  DATETIME(19)  
       */	
	private java.util.Date lastLoginTime;
	//【非数据库字段，查询时使用】
	private java.util.Date lastLoginTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date lastLoginTimeEnd;
	/**描述:最后登录IP   
        * 字段: last_login_ip  VARCHAR(50)  
       */	
	private java.lang.String lastLoginIp;
	/**描述:登录次数   
        * 字段: login_count  INT(10)  
       */	
	private java.lang.Integer loginCount;
	/**描述:管理员级别   
        * 字段: rank  INT(10)  
       */	
	private java.lang.Integer rank;
	/**描述:上传总大小   
        * 字段: upload_total  BIGINT(19)  
       */	
	private java.lang.Long uploadTotal;
	/**描述:上传大小   
        * 字段: upload_size  INT(10)  
       */	
	private java.lang.Integer uploadSize;
	/**描述:上传日期   
        * 字段: upload_date  DATE(10)  
       */	
	private java.util.Date uploadDate;
	//【非数据库字段，查询时使用】
	private java.util.Date uploadDateBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date uploadDateEnd;
	/**描述:是否管理员   
        * 字段: is_admin  BIT(0)  
       */	
	private java.lang.Boolean isAdmin;
	/**描述:是否只读管理员   
        * 字段: is_viewonly_admin  BIT(0)  
       */	
	private java.lang.Boolean isViewonlyAdmin;
	/**描述:是否只管理自己的数据   
        * 字段: is_self_admin  BIT(0)  
       */	
	private java.lang.Boolean isSelfAdmin;
	/**描述:是否禁用   
        * 字段: is_disabled  BIT(0)  
       */	
	private java.lang.Boolean isDisabled;
	public User(){
	}

	public User(
		java.lang.Integer userId
	){
		this.userId = userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}
	
	public java.util.Date getRegisterTime() {
		return this.registerTime;
	}
	
    public void setRegisterTimeBegin(java.util.Date registerTimeBegin) {
		this.registerTimeBegin = registerTimeBegin;
	}
	
	public java.util.Date getRegisterTimeBegin() {
		return this.registerTimeBegin;
	}
	
	public void setRegisterTimeEnd(java.util.Date registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}
	
	public java.util.Date getRegisterTimeEnd() {
		return this.registerTimeEnd;
	}	
	public void setRegisterIp(java.lang.String registerIp) {
		this.registerIp = registerIp;
	}
	
	public java.lang.String getRegisterIp() {
		return this.registerIp;
	}
	
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
    public void setLastLoginTimeBegin(java.util.Date lastLoginTimeBegin) {
		this.lastLoginTimeBegin = lastLoginTimeBegin;
	}
	
	public java.util.Date getLastLoginTimeBegin() {
		return this.lastLoginTimeBegin;
	}
	
	public void setLastLoginTimeEnd(java.util.Date lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}
	
	public java.util.Date getLastLoginTimeEnd() {
		return this.lastLoginTimeEnd;
	}	
	public void setLastLoginIp(java.lang.String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	public java.lang.String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLoginCount(java.lang.Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public java.lang.Integer getLoginCount() {
		return this.loginCount;
	}
	
	public void setRank(java.lang.Integer rank) {
		this.rank = rank;
	}
	
	public java.lang.Integer getRank() {
		return this.rank;
	}
	
	public void setUploadTotal(java.lang.Long uploadTotal) {
		this.uploadTotal = uploadTotal;
	}
	
	public java.lang.Long getUploadTotal() {
		return this.uploadTotal;
	}
	
	public void setUploadSize(java.lang.Integer uploadSize) {
		this.uploadSize = uploadSize;
	}
	
	public java.lang.Integer getUploadSize() {
		return this.uploadSize;
	}
	
	public void setUploadDate(java.util.Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public java.util.Date getUploadDate() {
		return this.uploadDate;
	}
	
    public void setUploadDateBegin(java.util.Date uploadDateBegin) {
		this.uploadDateBegin = uploadDateBegin;
	}
	
	public java.util.Date getUploadDateBegin() {
		return this.uploadDateBegin;
	}
	
	public void setUploadDateEnd(java.util.Date uploadDateEnd) {
		this.uploadDateEnd = uploadDateEnd;
	}
	
	public java.util.Date getUploadDateEnd() {
		return this.uploadDateEnd;
	}	
	public void setIsAdmin(java.lang.Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public java.lang.Boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsViewonlyAdmin(java.lang.Boolean isViewonlyAdmin) {
		this.isViewonlyAdmin = isViewonlyAdmin;
	}
	
	public java.lang.Boolean getIsViewonlyAdmin() {
		return this.isViewonlyAdmin;
	}
	
	public void setIsSelfAdmin(java.lang.Boolean isSelfAdmin) {
		this.isSelfAdmin = isSelfAdmin;
	}
	
	public java.lang.Boolean getIsSelfAdmin() {
		return this.isSelfAdmin;
	}
	
	public void setIsDisabled(java.lang.Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	public java.lang.Boolean getIsDisabled() {
		return this.isDisabled;
	}
	
}

