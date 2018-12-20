select * from es_cluster_node order by id desc ;
truncate es_cluster_apply;
truncate es_schedule;

select * from docker_models ;
-- 查询工单
select * from  es_schedule order by id desc ;
select * from es_cluster order by id desc ;
select * from es_cluster where name='jiesi-t9' order by id desc ;
select * from es_cluster_config order by id desc ;
select * from es_cluster_config where cluster_name like 'config_file%'
select * from es_cluster_apply order by id desc;
select * from es_cluster_server;
-- 查询已分配
select * from es_cluster_node order by id desc ;
select * from es_cluster_node where cluster_id=(select id from es_cluster where name='jiesi-t9')

-- 工单重置为调度等待 ,清理集群,及相关的节点
delete from  es_cluster_node  where cluster_id=(select id from es_cluster where name='jiesi-t2-17')
delete from es_cluster where name='jiesi-t%'
delete  from es_cluster_config where name='jiesi-t2-17'

-- 0 调度等待
update es_schedule set status=0,cluster_id=null where cluster_name='jiesi-t9'
-- 1 调度中
update es_schedule set status=1  where cluster_name='jiesi-t1'
-- execute执行,4-等待扩容
update es_schedule set status=4  where cluster_name='jiesi-t2-17'

-- 更新node为待启动
update es_cluster_node set deploy_status= 5 where cluster_id='350'


--------- 初始化数据  ---------------
update es_cluster_node set  node_model_id=1 where cluster_id=364




