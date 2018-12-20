package cn.shaolingweb.mybatis.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import cn.shaolingweb.mybatis.generator.Generator.GeneratorModel;
import cn.shaolingweb.mybatis.generator.provider.db.sql.model.Sql;
import cn.shaolingweb.mybatis.generator.provider.db.table.TableFactory;
import cn.shaolingweb.mybatis.generator.provider.db.table.model.Table;
import cn.shaolingweb.mybatis.generator.provider.java.model.JavaClass;
import cn.shaolingweb.mybatis.generator.util.BeanHelper;
import cn.shaolingweb.mybatis.generator.util.GeneratorException;
public class GeneratorFacade {
	private static  Logger logger=Logger.getLogger(GeneratorFacade.class);
	public static String GENTABNAME;
	public Generator g = new Generator();
	public GeneratorFacade() {
		File outRootDir = new File("output");
		String override = GeneratorProperties.getProperty("override");
		if (!outRootDir.exists() || Boolean.valueOf(override)) {// 如果此目录不存在，则创建该目录
			FileUtils.deleteQuietly(outRootDir);
			outRootDir.mkdirs();
		}
		g.setOutRootDir(outRootDir.getAbsolutePath());
	}
	
	public static void printAllTableNames() throws Exception {
		PrintUtils.printAllTableNames(TableFactory.getInstance().getAllTables());
	}
	
	public void deleteOutRootDir() throws IOException {
		g.deleteOutRootDir();
	}
	
	public void generateByMap(Map map, String templateRootDir) throws Exception {
		new ProcessUtils().processByMap(map, templateRootDir, false);
	}
	
	public void deleteByMap(Map map, String templateRootDir) throws Exception {
		new ProcessUtils().processByMap(map, templateRootDir, true);
	}
	
	public void generateByAllTable(String templateRootDir) throws Exception {
		new ProcessUtils().processByAllTable(templateRootDir, false);
	}
	
	public void deleteByAllTable(String templateRootDir) throws Exception {
		new ProcessUtils().processByAllTable(templateRootDir, true);
	}
	
	public void generateByTable(String tableName, String templateRootDir) throws Exception {
		GENTABNAME=tableName;
		new ProcessUtils().processByTable(tableName, templateRootDir, false);
	}
	
	public void deleteByTable(String tableName, String templateRootDir) throws Exception {
		new ProcessUtils().processByTable(tableName, templateRootDir, true);
	}
	
	public void generateByClass(Class clazz, String templateRootDir) throws Exception {
		new ProcessUtils().processByClass(clazz, templateRootDir, false);
	}
	
	public void deleteByClass(Class clazz, String templateRootDir) throws Exception {
		new ProcessUtils().processByClass(clazz, templateRootDir, true);
	}
	
	public void generateBySql(Sql sql, String templateRootDir) throws Exception {
		new ProcessUtils().processBySql(sql, templateRootDir, false);
	}
	
	public void deleteBySql(Sql sql, String templateRootDir) throws Exception {
		new ProcessUtils().processBySql(sql, templateRootDir, true);
	}
	
	private Generator getGenerator(String templateRootDir) {
		File file = new File(templateRootDir).getAbsoluteFile();
		g.setTemplateRootDir(file);
		return g;
	}
	
	/** 生成器的上下文，存放的变量将可以在模板中引用 */
	public static class GeneratorContext {
		static ThreadLocal<Map> context = new ThreadLocal<Map>();
		
		public static void clear() {
			Map m = context.get();
			if (m != null)
				m.clear();
		}
		
		public static Map getContext() {
			Map map = context.get();
			if (map == null) {
				setContext(new HashMap());
			}
			return context.get();
		}
		
		public static void setContext(Map map) {
			context.set(map);
		}
		
		public static void put(String key, Object value) {
			getContext().put(key, value);
		}
	}
	
	public class ProcessUtils {
		
		public void processByMap(Map params, String templateRootDir, boolean isDelete) throws Exception,
				FileNotFoundException {
			Generator g = getGenerator(templateRootDir);
			GeneratorModel m = GeneratorModelUtils.newFromMap(params);
			processByGeneratorModel(templateRootDir, isDelete, g, m);
		}
		
		public void processBySql(Sql sql, String templateRootDir, boolean isDelete) throws Exception {
			Generator g = getGenerator(templateRootDir);
			GeneratorModel m = GeneratorModelUtils.newFromSql(sql);
			PrintUtils.printBeginProcess("sql:" + sql.getSourceSql(), isDelete);
			processByGeneratorModel(templateRootDir, isDelete, g, m);
		}
		
		public void processByClass(Class clazz, String templateRootDir, boolean isDelete) throws Exception,
				FileNotFoundException {
			Generator g = getGenerator(templateRootDir);
			GeneratorModel m = GeneratorModelUtils.newFromClass(clazz);
			PrintUtils.printBeginProcess("JavaClass:" + clazz.getSimpleName(), isDelete);
			processByGeneratorModel(templateRootDir, isDelete, g, m);
		}
		
		private void processByGeneratorModel(String templateRootDir, boolean isDelete, Generator g,
				GeneratorModel m) throws Exception, FileNotFoundException {
			try {
				if (isDelete)
					g.deleteBy(m.templateModel, m.filePathModel);
				else
					g.generateBy(m.templateModel, m.filePathModel);
			} catch (GeneratorException ge) {
				PrintUtils.printExceptionsSumary(ge.getMessage(), getGenerator(templateRootDir)
						.getOutRootDir(), ge.getExceptions());
			}
		}
		
		public void processByTable(String tableName, String templateRootDir, boolean isDelete) throws Exception {
			if ("*".equals(tableName)) {
				if (isDelete)
					deleteByAllTable(templateRootDir);
				else
					generateByAllTable(templateRootDir);
				return;
			}
			Generator g = getGenerator(templateRootDir);
			Table table = TableFactory.getInstance().getTable(tableName);
			try {
				processByTable(g, table, isDelete);
			} catch (GeneratorException ge) {
				PrintUtils.printExceptionsSumary(ge.getMessage(), getGenerator(templateRootDir)
						.getOutRootDir(), ge.getExceptions());
			}
		}
		
		public void processByAllTable(String templateRootDir, boolean isDelete) throws Exception {
			List<Table> tables = TableFactory.getInstance().getAllTables();
			List exceptions = new ArrayList();
			for (int i = 0; i < tables.size(); i++) {
				try {
					processByTable(getGenerator(templateRootDir), tables.get(i), isDelete);
				} catch (GeneratorException ge) {
					exceptions.addAll(ge.getExceptions());
				}
			}
			PrintUtils.printExceptionsSumary("", getGenerator(templateRootDir).getOutRootDir(), exceptions);
		}
		
		public void processByTable(Generator g, Table table, boolean isDelete) throws Exception {
			GeneratorModel m = GeneratorModelUtils.newFromTable(table);
			PrintUtils.printBeginProcess(table.getSqlName() + " => " + table.getClassName(), isDelete);
			if (isDelete)
				g.deleteBy(m.templateModel, m.filePathModel);
			else
				g.generateBy(m.templateModel, m.filePathModel);
		}
	}
	
	@SuppressWarnings("all")
	public static class GeneratorModelUtils {
		
		public static GeneratorModel newFromTable(Table table) {
			Map templateModel = new HashMap();
			templateModel.put("table", table);
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(BeanHelper.describe(table));
			return new GeneratorModel(templateModel, filePathModel);
		}
		
		public static GeneratorModel newFromSql(Sql sql) throws Exception {
			Map templateModel = new HashMap();
			templateModel.put("sql", sql);
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(BeanHelper.describe(sql));
			return new GeneratorModel(templateModel, filePathModel);
		}
		
		public static GeneratorModel newFromClass(Class clazz) {
			Map templateModel = new HashMap();
			templateModel.put("clazz", new JavaClass(clazz));
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(BeanHelper.describe(new JavaClass(clazz)));
			return new GeneratorModel(templateModel, filePathModel);
		}
		
		public static GeneratorModel newFromMap(Map params) {
			Map templateModel = new HashMap();
			templateModel.putAll(params);
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(params);
			return new GeneratorModel(templateModel, filePathModel);
		}
		
		public static void setShareVars(Map templateModel) {
			templateModel.putAll(GeneratorProperties.getProperties());
			templateModel.putAll(System.getProperties());
			templateModel.put("env", System.getenv());
			templateModel.put("now", new Date());
			templateModel.putAll(GeneratorContext.getContext());
		}
		
	}
	
	private static class PrintUtils {
		
		private static void printExceptionsSumary(String msg, String outRoot, List<Exception> exceptions)
				throws FileNotFoundException {
			File errorFile = new File(outRoot, "generator_error.log");
			if (exceptions != null && exceptions.size() > 0) {
				System.err.println("[Generate Error Summary] : " + msg);
				PrintStream output = new PrintStream(new FileOutputStream(errorFile));
				for (int i = 0; i < exceptions.size(); i++) {
					Exception e = exceptions.get(i);
					System.err.println("[GENERATE ERROR]:" + e);
					if (i == 0)
						e.printStackTrace();
					e.printStackTrace(output);
				}
				output.close();
				System.err.println(" 输出目录已经生成generator_error.log用于查看错误 ");
			}
		}
		
		private static void printBeginProcess(String displayText, boolean isDatele) {
			logger.info("* BEGIN " + (isDatele ? " delete by " : " generate by ") + displayText);
		}
		
		public static void printAllTableNames(List<Table> tables) throws Exception {
			for (int i = 0; i < tables.size(); i++) {
				String sqlName = ((Table) tables.get(i)).getSqlName();
				logger.info("table(\"" + sqlName + "\");");
			}
		}
	}
	
}
