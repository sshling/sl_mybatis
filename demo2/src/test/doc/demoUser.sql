create table demo_user
(
	id bigint auto_increment
		primary key,
	name varchar(20) not null,
	age int null,
	height smallint(6) null comment '身高,short类型',
	attr_tinyint tinyint null comment '测试属性,tinyint , The signed range is -128 to 127. The unsigned range is 0 to 255.',
	sex enum('男', '女') null comment '性别:字符串枚举',
	attr_bit bit null comment '测试熟悉,byte, zero is considered false. Nonzero values are considered true',
	is_hot tinyint(1) null comment '是否是热门,boolean类型的值',
	createDate datetime null comment 'metaData={"type":1,"fmt":"yyyy-MM-dd HH:mm:ss"}###   创建日期',
	updateDate timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	constraint demo_user_name_uindex
		unique (name)
)
engine=InnoDB
;


