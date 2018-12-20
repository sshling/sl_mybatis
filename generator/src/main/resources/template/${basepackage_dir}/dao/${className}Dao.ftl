<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   

package ${basepackage}.dao;
import ${basepackage}.model.${className};
import ${commnBase}.dao.BaseDao;

/**
 * 自动生成,请勿修改
 * @author   shaoling
 */
public interface ${className}Dao extends  BaseDao<${className}, ${table.idColumn.javaType}> {
	
}
