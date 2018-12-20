/*
* 自动生成,请勿修改
* Copyright  © shaoling
*/
package cn.shaolingweb.model;


import cn.shaolingweb.mybatis.daoBase.model.BaseModel;
/**
 * 自动生成，请勿修改
 * @table es_cluster_node   
 * @author  shaoling
 */
public class EsClusterNode extends BaseModel{
	private static final long serialVersionUID = 1L;
	/**
	 * 描述:id   
     * 字段: id  INT(10)  
     */	
	private java.lang.Integer id;
	/**
	 * 描述:集群id   
     * 字段: cluster_id  INT(10)  
     */	
	private java.lang.Integer clusterId;
	/**
	 * 描述:节点ip   
     * 字段: ip  VARCHAR(50)  
     */	
	private java.lang.String ip;
	/**
	 * 描述:节点端口   
     * 字段: http_port  VARCHAR(10)  
     */	
	private java.lang.String httpPort;
	/**
	 * 描述:"0":"主节点","1":"数据节点","2":"网关节点","3":"主数节点","4":"节点类型未知"   
     * 字段: type  INT(10)  
     */	
	private java.lang.Integer type;
	/**
	 * 描述:节点内存大小   
     * 字段: memory  INT(10)  
     */	
	private java.lang.Integer memory;
	/**
	 * 描述:pid   
     * 字段: pid  INT(10)  
     */	
	private java.lang.Integer pid;
	/**
	 * 描述:参数   
     * 字段: parameter  VARCHAR(1024)  
     */	
	private java.lang.String parameter;
	/**
	 * 描述:描述   
     * 字段: desc  VARCHAR(1024)  
     */	
	private java.lang.String desc;
	/**
	 * 描述:creator   
     * 字段: creator  VARCHAR(50)  
     */	
	private java.lang.String creator;
	/**
	 * 描述:createTime   
     * 字段: create_time  DATETIME(19)  
     */	
	private java.util.Date createTime;
	//【非数据库字段，查询时使用】
	private java.util.Date createTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date createTimeEnd;
	/**
	 * 描述:modifier   
     * 字段: modifier  VARCHAR(50)  
     */	
	private java.lang.String modifier;
	/**
	 * 描述:modifyTime   
     * 字段: modify_time  DATETIME(19)  
     */	
	private java.util.Date modifyTime;
	//【非数据库字段，查询时使用】
	private java.util.Date modifyTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date modifyTimeEnd;
	/**
	 * 描述:tcpPort   
     * 字段: tcp_port  VARCHAR(10)  
     */	
	private java.lang.String tcpPort;
	/**
	 * 描述:clusterServerId   
     * 字段: cluster_server_id  INT(10)  
     */	
	private java.lang.Integer clusterServerId;
	/**
	 * 描述:1:下线 2：上线 0：不显示任何值   
     * 字段: status  TINYINT(3)  
     */	
	private Integer status;
	/**
	 * 描述:dockerFlag   
     * 字段: docker_flag  TINYINT(3)  
     */	
	private Integer dockerFlag;
	/**
	 * 描述:dockerCoreIsoFlag   
     * 字段: docker_core_iso_flag  TINYINT(3)  
     */	
	private Integer dockerCoreIsoFlag;
	/**
	 * 描述:dockerCore   
     * 字段: docker_core  INT(10)  
     */	
	private java.lang.Integer dockerCore;
	/**
	 * 描述:dockerCoreDetail   
     * 字段: docker_core_detail  VARCHAR(256)  
     */	
	private java.lang.String dockerCoreDetail;
	/**
	 * 描述:dockerParamCpuShares   
     * 字段: docker_param_cpu_shares  VARCHAR(32)  
     */	
	private java.lang.String dockerParamCpuShares;
	/**
	 * 描述:dockerParamCpuPeriod   
     * 字段: docker_param_cpu_period  VARCHAR(32)  
     */	
	private java.lang.String dockerParamCpuPeriod;
	/**
	 * 描述:dockerParamCpuQuota   
     * 字段: docker_param_cpu_quota  VARCHAR(32)  
     */	
	private java.lang.String dockerParamCpuQuota;
	/**
	 * 描述:dockerParamCpusetCpus   
     * 字段: docker_param_cpuset_cpus  VARCHAR(32)  
     */	
	private java.lang.String dockerParamCpusetCpus;
	/**
	 * 描述:dockerParamCpusetMems   
     * 字段: docker_param_cpuset_mems  VARCHAR(32)  
     */	
	private java.lang.String dockerParamCpusetMems;
	/**
	 * 描述:dockerParamMemory   
     * 字段: docker_param_memory  VARCHAR(32)  
     */	
	private java.lang.String dockerParamMemory;
	/**
	 * 描述:dockerParamMemorySwap   
     * 字段: docker_param_memory_swap  VARCHAR(32)  
     */	
	private java.lang.String dockerParamMemorySwap;
	/**
	 * 描述:restartTime   
     * 字段: restart_time  DATETIME(19)  
     */	
	private java.util.Date restartTime;
	//【非数据库字段，查询时使用】
	private java.util.Date restartTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date restartTimeEnd;
	/**
	 * 描述:dockerParamDisk   
     * 字段: docker_param_disk  INT(10)  
     */	
	private java.lang.Integer dockerParamDisk;
	/**
	 * 描述:nodeModelId   
     * 字段: node_model_id  INT(10)  
     */	
	private java.lang.Integer nodeModelId;
	/**
	 * 描述:deployStatus   
     * 字段: deploy_status  INT(10)  
     */	
	private java.lang.Integer deployStatus;
	/**
	 * 描述:scheduleOrderId   
     * 字段: schedule_order_id  BIGINT(19)  
     */	
	private java.lang.Long scheduleOrderId;
	/**
	 * 描述:scheduleType   
     * 字段: schedule_type  INT(10)  
     */	
	private java.lang.Integer scheduleType;
	public EsClusterNode(){
	}

	public EsClusterNode(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setClusterId(java.lang.Integer clusterId) {
		this.clusterId = clusterId;
	}
	
	public java.lang.Integer getClusterId() {
		return this.clusterId;
	}
	
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	
	public java.lang.String getIp() {
		return this.ip;
	}
	
	public void setHttpPort(java.lang.String httpPort) {
		this.httpPort = httpPort;
	}
	
	public java.lang.String getHttpPort() {
		return this.httpPort;
	}
	
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setMemory(java.lang.Integer memory) {
		this.memory = memory;
	}
	
	public java.lang.Integer getMemory() {
		return this.memory;
	}
	
	public void setPid(java.lang.Integer pid) {
		this.pid = pid;
	}
	
	public java.lang.Integer getPid() {
		return this.pid;
	}
	
	public void setParameter(java.lang.String parameter) {
		this.parameter = parameter;
	}
	
	public java.lang.String getParameter() {
		return this.parameter;
	}
	
	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}
	
	public java.lang.String getDesc() {
		return this.desc;
	}
	
	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
    public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}	
	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
	}
	
	public java.lang.String getModifier() {
		return this.modifier;
	}
	
	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	
    public void setModifyTimeBegin(java.util.Date modifyTimeBegin) {
		this.modifyTimeBegin = modifyTimeBegin;
	}
	
	public java.util.Date getModifyTimeBegin() {
		return this.modifyTimeBegin;
	}
	
	public void setModifyTimeEnd(java.util.Date modifyTimeEnd) {
		this.modifyTimeEnd = modifyTimeEnd;
	}
	
	public java.util.Date getModifyTimeEnd() {
		return this.modifyTimeEnd;
	}	
	public void setTcpPort(java.lang.String tcpPort) {
		this.tcpPort = tcpPort;
	}
	
	public java.lang.String getTcpPort() {
		return this.tcpPort;
	}
	
	public void setClusterServerId(java.lang.Integer clusterServerId) {
		this.clusterServerId = clusterServerId;
	}
	
	public java.lang.Integer getClusterServerId() {
		return this.clusterServerId;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setDockerFlag(Integer dockerFlag) {
		this.dockerFlag = dockerFlag;
	}
	
	public Integer getDockerFlag() {
		return this.dockerFlag;
	}
	
	public void setDockerCoreIsoFlag(Integer dockerCoreIsoFlag) {
		this.dockerCoreIsoFlag = dockerCoreIsoFlag;
	}
	
	public Integer getDockerCoreIsoFlag() {
		return this.dockerCoreIsoFlag;
	}
	
	public void setDockerCore(java.lang.Integer dockerCore) {
		this.dockerCore = dockerCore;
	}
	
	public java.lang.Integer getDockerCore() {
		return this.dockerCore;
	}
	
	public void setDockerCoreDetail(java.lang.String dockerCoreDetail) {
		this.dockerCoreDetail = dockerCoreDetail;
	}
	
	public java.lang.String getDockerCoreDetail() {
		return this.dockerCoreDetail;
	}
	
	public void setDockerParamCpuShares(java.lang.String dockerParamCpuShares) {
		this.dockerParamCpuShares = dockerParamCpuShares;
	}
	
	public java.lang.String getDockerParamCpuShares() {
		return this.dockerParamCpuShares;
	}
	
	public void setDockerParamCpuPeriod(java.lang.String dockerParamCpuPeriod) {
		this.dockerParamCpuPeriod = dockerParamCpuPeriod;
	}
	
	public java.lang.String getDockerParamCpuPeriod() {
		return this.dockerParamCpuPeriod;
	}
	
	public void setDockerParamCpuQuota(java.lang.String dockerParamCpuQuota) {
		this.dockerParamCpuQuota = dockerParamCpuQuota;
	}
	
	public java.lang.String getDockerParamCpuQuota() {
		return this.dockerParamCpuQuota;
	}
	
	public void setDockerParamCpusetCpus(java.lang.String dockerParamCpusetCpus) {
		this.dockerParamCpusetCpus = dockerParamCpusetCpus;
	}
	
	public java.lang.String getDockerParamCpusetCpus() {
		return this.dockerParamCpusetCpus;
	}
	
	public void setDockerParamCpusetMems(java.lang.String dockerParamCpusetMems) {
		this.dockerParamCpusetMems = dockerParamCpusetMems;
	}
	
	public java.lang.String getDockerParamCpusetMems() {
		return this.dockerParamCpusetMems;
	}
	
	public void setDockerParamMemory(java.lang.String dockerParamMemory) {
		this.dockerParamMemory = dockerParamMemory;
	}
	
	public java.lang.String getDockerParamMemory() {
		return this.dockerParamMemory;
	}
	
	public void setDockerParamMemorySwap(java.lang.String dockerParamMemorySwap) {
		this.dockerParamMemorySwap = dockerParamMemorySwap;
	}
	
	public java.lang.String getDockerParamMemorySwap() {
		return this.dockerParamMemorySwap;
	}
	
	public void setRestartTime(java.util.Date restartTime) {
		this.restartTime = restartTime;
	}
	
	public java.util.Date getRestartTime() {
		return this.restartTime;
	}
	
    public void setRestartTimeBegin(java.util.Date restartTimeBegin) {
		this.restartTimeBegin = restartTimeBegin;
	}
	
	public java.util.Date getRestartTimeBegin() {
		return this.restartTimeBegin;
	}
	
	public void setRestartTimeEnd(java.util.Date restartTimeEnd) {
		this.restartTimeEnd = restartTimeEnd;
	}
	
	public java.util.Date getRestartTimeEnd() {
		return this.restartTimeEnd;
	}	
	public void setDockerParamDisk(java.lang.Integer dockerParamDisk) {
		this.dockerParamDisk = dockerParamDisk;
	}
	
	public java.lang.Integer getDockerParamDisk() {
		return this.dockerParamDisk;
	}
	
	public void setNodeModelId(java.lang.Integer nodeModelId) {
		this.nodeModelId = nodeModelId;
	}
	
	public java.lang.Integer getNodeModelId() {
		return this.nodeModelId;
	}
	
	public void setDeployStatus(java.lang.Integer deployStatus) {
		this.deployStatus = deployStatus;
	}
	
	public java.lang.Integer getDeployStatus() {
		return this.deployStatus;
	}
	
	public void setScheduleOrderId(java.lang.Long scheduleOrderId) {
		this.scheduleOrderId = scheduleOrderId;
	}
	
	public java.lang.Long getScheduleOrderId() {
		return this.scheduleOrderId;
	}
	
	public void setScheduleType(java.lang.Integer scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	public java.lang.Integer getScheduleType() {
		return this.scheduleType;
	}
	
	
}

