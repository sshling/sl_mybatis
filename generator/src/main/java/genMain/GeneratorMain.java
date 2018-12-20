package genMain;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import cn.shaolingweb.mybatis.generator.GeneratorFacade;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
  基于FreeMaker的生成技术
 */
public class GeneratorMain {
	private static  Logger logger=Logger.getLogger(GeneratorMain.class);
	private static Properties prop = new Properties();
	static {
		Resource r=new ClassPathResource("generator.properties");
		try {
			prop.load(r.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
// 		g.generateByAllTable(getTplPath());	//不建议使用：自动搜索数据库中的所有表并生成文件,template为模板的根目录
 		g.generateByTable("es_cluster_node", getTplPath());//名称如果是*,则所有表
// 		g.generateByTable("sales_ko_product_statics", getTplPath());//名称如果是*,则所有表
// 		g.generateByTable("jc_user", getTplPath());//名称如果是*,则所有表
//		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot")+"/ibatis3/template/java_src");
//		Runtime.getRuntime().exec("cmd.exe /c start "+".");
		logger.info("生成完毕!");
		copyToTest();
	}
	
	/**基于spring-core，在当前类路径下查找资源
	 * @return
	 */
	private static String getTplPath(){
			Resource res=new ClassPathResource("template");
			File result=null;
			try {
				 result=res.getFile();
				return result.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	private static void copyToTest() throws Exception{
		File srcDir=new File("output");
		File destDir=new File(prop.getProperty("targetModule")
				+"/src/test/resources/mapper");
		FileUtils.copyDirectory(srcDir,destDir);
		logger.info("拷贝完成!");
	}
}
