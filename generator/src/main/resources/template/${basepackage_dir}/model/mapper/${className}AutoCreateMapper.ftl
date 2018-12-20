<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#macro mapperEl value>${r"#{"}${value}}</#macro><#-- fm注释：用来生成#{value} -->
<#macro mapperItem value>${r"#{item."}${value}}</#macro><#-- fm注释：用来生成#{value} -->
<#macro jspEl         value>${r"${"}${value}}</#macro><#-- fm注释：用来生成${value} -->
<#macro namespace>${className}.</#macro>
<#assign   ids="$\{ids}" />
<#--  自动生成1,请勿修改：若用映射接口则namespace必须和映射接口全路径对应 
知识1：#{value} MyBatis会把value里的双引号、单引号去掉，${value}则会值原封不动替换
 -->
<#compress><#-- 压缩空白代码 --></#compress>
<mapper namespace="${basepackage}.model.${className}">

<!-- =================== 自定义代码区start =================== -->
	
<!-- =================== 自定义代码区 end ===================  -->
<#--
参考：http://mybatis.github.io/mybatis-3/sqlmap-xml.html#cache
	看命中日志：org.apache.ibatis.cache.decorators.LoggingCache设为debug
	默认使用内置HashMap缓存
	     <cache /> 
		<cache type="org.mybatis.caches.ehcache.EhcacheCache"/> 
-->
	<resultMap id="auto_RM_${className}" type="${basepackage}.model.${className}">
        <#list table.columns as column>
		<result property="${column.columnNameLower}" column="${column.sqlName}"/>
	</#list>
	</resultMap>
	<sql id="auto_columns">
	<#-- 字段名称最好用反引号,比如 desc等字段会出错-->
    <![CDATA[<#list table.columns as column>`${column.sqlName}`<#if column_has_next>,</#if></#list> ]]>
</sql>
<#--
useGeneratedKeys配置:
    sqlserver and mysql
        useGeneratedKeys="true" keyProperty="xxx"
    oracle:
        order="BEFORE" SELECT sequenceName.nextval AS ID FROM DUAL
        <selectKey resultType="java.lang.Long" order="BEFORE" keyProperty="userId">
            SELECT sequenceName.nextval AS ID FROM DUAL
        </selectKey>
    保证已设置自增，自增列在插入语句可有可无
-->
	<insert id="auto_insert" useGeneratedKeys="true" keyProperty="${table.idColumn.columnNameFirstLower}">
	    INSERT INTO ${table.sqlName}(<include refid="auto_columns" />)  
	    <![CDATA[VALUES(<#list table.columns as column><@mapperEl column.columnNameFirstLower/><#if column_has_next>,</#if> </#list>)]]>
	</insert>
	<!-- 批量插入 -->
	<insert id="auto_insertBatch" useGeneratedKeys="true" keyProperty="${table.idColumn.columnNameFirstLower}">
		INSERT INTO ${table.sqlName}(<include refid="auto_columns" />) 
		VALUES
		<foreach collection="list" item="item" index="index" separator=",">
			(<#list table.columns as column><@mapperItem column.columnNameFirstLower/><#if column_has_next>,</#if></#list>)
		</foreach>
	</insert>
	<!-- 更新条件 -->
     <sql id="auto_update_sql">
         <set>
		 	<!-- set元素：前置set关键字且消除无关逗号,等价<trim prefix="SET" suffixOverrides=","> -->
          <#list table.notPkColumns as column>
            <#if column.isDateTimeColumn>
                <if test="${column.columnNameFirstLower} != null">`${column.sqlName}` =<@mapperEl column.columnNameFirstLower/><#if column_has_next>,</#if> </if>
	       </#if>  
	       <#if column.isNumberColumn>
                <if test="${column.columnNameFirstLower} != null">`${column.sqlName}` =<@mapperEl column.columnNameFirstLower/><#if column_has_next>,</#if></if>
	       </#if>
	        <#if column.isStringColumn>
                <if test="${column.columnNameFirstLower} != null and ${column.columnNameFirstLower} != ''">`${column.sqlName}` = <@mapperEl column.columnNameFirstLower/> <#if column_has_next>,</#if></if>
	       </#if>
         </#list>  
       </set>
    </sql>
    <!-- 完全自定义更新SQL -->
    <update id="auto_updateCustomSql" ><@jspEl 'customSql'/></update>
	<!-- 更新语句 -->
	<update id="auto_update" >
		UPDATE  ${table.sqlName}  <include refid="auto_update_sql" />
		<where>
			<if test="extraConditions != null  and extraConditions !=''"> <@jspEl 'extraConditions'/></if>
		<choose>  
		<when test="ids != null  and ids !=''">id in (${ids})</when>  
		<otherwise>
			<#list table.compositeIdColumns as column>
			${column.sqlName} =<@mapperEl column.columnNameLower/><#if column_has_next> AND </#if>
			</#list>
		</otherwise>  
		</choose> 
		</where>
	</update>

	<!-- 删除:自定义 -->
	<delete id="auto_deleteCustomSql" ><@jspEl 'customSql'/></delete>
	<delete id="auto_delete">
		DELETE FROM ${table.sqlName} 
		<where> 
		   	<#list table.compositeIdColumns as column>
			${column.sqlName}=<@mapperEl column.columnNameLower/><#if column_has_next> AND </#if>
			</#list>
	    </where>
	</delete>
	<delete id="auto_deleteByCondition">
		DELETE FROM ${table.sqlName}
		<choose> 
			<!-- todo --> 
			<when test="where != null and where !=''">
				<@jspEl 'where'/>
			</when>  
		    <otherwise>
				<include refid="auto_findPage_where"/>
			</otherwise>  
		</choose>
	</delete>
	<delete id="auto_delete_batch_string" >
		<![CDATA[ DELETE FROM sales_ko_product WHERE id IN (${ids}) ]]>
	</delete>
    <select id="auto_getById" resultMap="auto_RM_${className}">
	    SELECT <include refid="auto_columns" />
	    <![CDATA[FROM ${table.sqlName} WHERE <#list table.compositeIdColumns as column>${column.sqlName} = <@mapperEl 'id'/> <#if column_has_next> AND </#if></#list>]]>
    </select>
    <sql id="auto_findPage_where"><!-- ognl访问静态方法为@class@method(args),以下Ognl.isNotEmpty()方法,其它如isNotBlank()可用，具体查Ognl类 -->
		<where> 
			<if test="ids != null  and ids !=''"> AND id in (${ids})</if>
			<#list table.columns as column><#if column.isDateTimeColumn>
			<if test="${column.columnNameFirstLower}Begin != null">AND ${column.sqlName} >= <@mapperEl column.columnNameFirstLower+"Begin"/></if>
			<if test="${column.columnNameFirstLower}End != null">AND ${column.sqlName} &lt;= <@mapperEl column.columnNameFirstLower+"End"/></if><#elseif column.isNumberColumn>
			<if test="${column.columnNameFirstLower} != null">AND ${column.sqlName} = <@mapperEl column.columnNameFirstLower/></if><#elseif column.isStringColumn>
			<if test="${column.columnNameFirstLower} != null and ${column.columnNameFirstLower} !=''">
				<choose>
					<when test="selectType['${column.columnNameFirstLower}']==1">AND ${column.sqlName} like CONCAT('%', <@mapperEl column.columnNameFirstLower/>, '%')</when>  
					<otherwise>AND `${column.sqlName}` = <@mapperEl column.columnNameFirstLower/></otherwise>
				</choose>
			</if></#if>
			</#list>
			<if test="extraConditions != null">AND <@jspEl 'extraConditions'/></if>
		</where>
    </sql>
    <select id="auto_findPage_count" resultType="long">
        SELECT count(*) FROM ${table.sqlName} 
		<choose>  
			<when test="where != null and where !=''"> <@jspEl 'where'/></when>  
		<otherwise>
			<include refid="auto_findPage_where"/>
		</otherwise>  
		</choose>
    </select>
    <select id="auto_findPage" resultMap="auto_RM_${className}"><!-- 分页查询已用Dialect分页,也可以直接编写分页,将传 offset,pageSize,lastRows 3个参数,不同的数据库可以根于此三个参数属性应用不同的分页实现 -->
    	SELECT
    	<choose>  
	   		<when test="selectColumns != null and selectColumns !=''">
			   	<@jspEl 'selectColumns'/>
			</when>  
			<otherwise>
				<include refid="auto_columns" />
			</otherwise>  
		</choose>
    	FROM ${table.sqlName}
		<choose>  
			<when test="where != null and where !=''"> <@jspEl 'where'/></when>  
			<otherwise>
				<include refid="auto_findPage_where"/>
				<#-- 先分组后排序 -->
				<if test="groupColumns != null and groupColumns!=''">group by <@jspEl 'groupColumns'/></if>
				<if test="sortColumns != null  and sortColumns!=''">order by <@jspEl 'sortColumns'/></if>
			</otherwise>  
		</choose>
    </select>

	<!-- 基于有unique索引的字段查询，非主键 -->
    <#list table.columns as column>
	<#if column.unique && !column.pk>
		<select id="getBy${column.columnName}" resultMap="auto_RM_${className}" parameterType="${column.javaType}">
		SELECT <include refid="auto_columns"/><![CDATA[FROM ${table.sqlName} where ${column.sqlName} = <@mapperEl column.columnNameLower/>]]>
		</select>
	</#if>
    </#list>
    <!-- 示例用法 -->
    <delete id="auto_delete_batch_list">
        DELETE FROM ${table.sqlName} WHERE id IN <foreach item="item" collection="list" open="(" separator="," close=")">${r"#{item}"}</foreach>
    </delete>
<#-- 返回结果 映射为 Map的kv对
    <resultMap id="groupResultMap"  type="HashMap">
        <result column="node_model_id" property="nodeModelId"/>
        <result column="total" property="total" />
    </resultMap>
    <select id="nodeModelIdCount" resultType="java.util.Map">
        select node_model_id as nodeModelId,COUNT(1) as total FROM es_cluster_node GROUP BY node_model_id;
    </select>
-->
</mapper>
