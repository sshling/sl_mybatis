<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   

package ${basepackage}.dao.impl;

import org.springframework.stereotype.Repository;
import ${commnBase}.dao.MyBatisBaseDaoImpl;
import ${basepackage}.model.${className};
import ${basepackage}.dao.${className}Dao;

@Repository
public class ${className}DaoImpl extends MyBatisBaseDaoImpl<${className},${table.idColumn.javaType}> implements  ${className}Dao{
}
