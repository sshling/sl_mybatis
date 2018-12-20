package cn.shaolingweb.mybatis.generator;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import cn.shaolingweb.mybatis.generator.util.PropertiesHelper;
import cn.shaolingweb.mybatis.generator.util.PropertyPlaceholderHelper;
import cn.shaolingweb.mybatis.generator.util.PropertyPlaceholderHelper.PropertyPlaceholderConfigurerResolver;
import cn.shaolingweb.mybatis.generator.util.typemapping.DatabaseTypeUtils;
/**
 * 生成器配置类
 * 用于装载generator.properties,generator.xml文件
 */
public class GeneratorProperties {
	private static  Logger logger=Logger.getLogger(GeneratorProperties.class);
	static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", false);
	
	private static final String propertiesFileNames[] = new String[]{"generator.properties","generator.xml"};
	
	static PropertiesHelper props = new PropertiesHelper(new Properties(),true);
	private GeneratorProperties(){}
	static {
		reload();
	}
	
	public static void reload() {
		try {
			logger.info("Start Load GeneratorPropeties from classpath:"+Arrays.toString(propertiesFileNames));
			Properties properties = new Properties();
			String[] loadedFiles = PropertiesHelper.loadAllPropertiesFromClassLoader(properties,propertiesFileNames);
			logger.info("GeneratorPropeties Load Success,files:"+Arrays.toString(loadedFiles));
			setSepicalProperties(properties, loadedFiles);
			
			setProperties(properties);
		}catch(IOException e) {
			throw new RuntimeException("Load "+propertiesFileNames+" error",e);
		}
	}

	private static void setSepicalProperties(Properties properties, String[] loadedFiles) {
		properties.put("databaseType", getDatabaseType(properties,"databaseType"));
		if(loadedFiles != null && loadedFiles.length > 0) {
			String basedir = properties.getProperty("basedir");
			if(basedir != null && basedir.startsWith(".")) {
				properties.setProperty("basedir", new File(new File(loadedFiles[0]).getParent(),basedir).getAbsolutePath());
			}
		}
	}
	
	private static String getDatabaseType(Properties p,String key) {
		String databaseType = DatabaseTypeUtils.getDatabaseTypeByJdbcDriver(p.getProperty("jdbc.driver"));
		return p.getProperty(key,databaseType == null ? "" : databaseType);
	}
	
	// 自动替换所有value从 com.company 替换为 com/company,并设置key = key+"_dir"后缀
	private static Properties autoReplacePropertiesValue2DirValue(Properties props) {
        Properties autoReplaceProperties = new Properties();
        for(Object key : getProperties().keySet()) {
            String dir_key = key.toString()+"_dir";
            String value = props.getProperty(key.toString());
            String dir_value = value.toString().replace('.', '/');
            autoReplaceProperties.put(dir_key, dir_value);           
        }
        return autoReplaceProperties;
    }
	public static Properties getProperties() {
		return getHelper().getProperties();
	}
	
	private static PropertiesHelper getHelper() {
		return props;
	}
	
	public static String getProperty(String key, String defaultValue) {
		return getHelper().getProperty(key, defaultValue);
	}
	
	public static String getProperty(String key) {
		return getHelper().getProperty(key);
	}
	
	public static String getRequiredProperty(String key) {
		return getHelper().getRequiredProperty(key);
	}
	
	public static int getRequiredInt(String key) {
		return getHelper().getRequiredInt(key);
	}
	
	public static boolean getRequiredBoolean(String key) {
		return getHelper().getRequiredBoolean(key);
	}
	
	public static String getNullIfBlank(String key) {
		return getHelper().getNullIfBlank(key);
	}
	
	public static void setProperty(String key,String value) {
		value = resolveProperty(value,getProperties());
		key = resolveProperty(key,getProperties());
	    logger.info("[setProperty()] "+key+"="+value);
		getHelper().setProperty(key, value);
		String dir_value = value.toString().replace('.', '/');
		getHelper().getProperties().put(key+"_dir", dir_value);
	}

	private static Properties resolveProperties(Properties props) {
		Properties result = new Properties();
		for(Object s : props.keySet()) {
			String sourceKey = s.toString();
			String key  = resolveProperty(sourceKey,props);
			String value = resolveProperty(props.getProperty(sourceKey),props);
			result.setProperty(key, value);
		}
		return result;
	}
	
	private static String resolveProperty(String v,Properties props) {
		PropertyPlaceholderConfigurerResolver propertyPlaceholderConfigurerResolver = new PropertyPlaceholderConfigurerResolver(props);
		return helper.replacePlaceholders(v, propertyPlaceholderConfigurerResolver);
	}
	
	public static void setProperties(Properties inputProps) {
		props = new PropertiesHelper(resolveProperties(inputProps),true);
        for(Iterator it = props.entrySet().iterator();it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            logger.debug("[Property] "+entry.getKey()+"="+entry.getValue());
        }
        
        logger.info("[Auto Replace] [.] => [/] on generator.properties, key=source_key+'_dir', For example: pkg=com.company ==> pkg_dir=com/company  \n");
        Properties dirProperties = autoReplacePropertiesValue2DirValue(props.getProperties());
        props.getProperties().putAll(dirProperties);
	}

}
