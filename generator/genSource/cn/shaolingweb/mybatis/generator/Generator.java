package cn.shaolingweb.mybatis.generator;

import cn.shaolingweb.mybatis.generator.util.*;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * 代码生成器核心引擎
 * 主要提供以下两个方法供外部使用
 * <br>generateBy(), 生成文件
 * <br>deleteBy(), 删除生成的文件
 *
 * @email shaoling@gmail.com
 */
public class Generator {
    private static Logger logger = Logger.getLogger(Generator.class);
    private static final String GENERATOR_INSERT_LOCATION = "generator-insert-location";
    private List<File> templateRootDirs = new ArrayList<File>();
    private String outRootDir;
    private boolean ignoreTemplateGenerateException = true;
    private String removeExtensions = System.getProperty("generator.removeExtensions", ".ftl,.vm");
    private boolean isCopyBinaryFile = true;

    private String includes = System.getProperty("generator.includes"); // 需要处理的模板，使用逗号分隔符,示例值:
    // java_src/**,java_test/**
    private String excludes = System.getProperty("generator.excludes"); // 不需要处理的模板，使用逗号分隔符,示例值:
    // java_src/**,java_test/**
    private String sourceEncoding = System.getProperty("generator.sourceEncoding", "UTF-8");
    private String outputEncoding = System.getProperty("generator.outputEncoding", "UTF-8");

    public void setTemplateRootDir(File templateRootDir) {
        setTemplateRootDirs(templateRootDir);
    }

    public void setTemplateRootDirs(File... templateRootDirs) {
        this.templateRootDirs = Arrays.asList(templateRootDirs);
    }

    public void addTemplateRootDir(File f) {
        templateRootDirs.add(f);
    }

    public boolean isIgnoreTemplateGenerateException() {
        return ignoreTemplateGenerateException;
    }

    public void setIgnoreTemplateGenerateException(boolean ignoreTemplateGenerateException) {
        this.ignoreTemplateGenerateException = ignoreTemplateGenerateException;
    }

    public boolean isCopyBinaryFile() {
        return isCopyBinaryFile;
    }

    public void setCopyBinaryFile(boolean isCopyBinaryFile) {
        this.isCopyBinaryFile = isCopyBinaryFile;
    }

    public String getSourceEncoding() {
        return sourceEncoding;
    }

    public void setSourceEncoding(String sourceEncoding) {
        this.sourceEncoding = sourceEncoding;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    /**
     * 设置不处理的模板路径,可以使用ant类似的值,使用逗号分隔，示例值： **\*.ignore
     */
    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public void setOutRootDir(String outRootDir) {
        this.outRootDir = outRootDir;
        new File(outRootDir).mkdirs();
    }

    public String getOutRootDir() {
        return outRootDir;
    }

    public void setRemoveExtensions(String removeExtensions) {
        this.removeExtensions = removeExtensions;
    }

    public void deleteOutRootDir() throws IOException {
        logger.info("[delete dir]    " + getOutRootDir());
        FileUtils.deleteDirectory(new File(getOutRootDir()));
    }

    /**
     * 生成文件
     *
     * @param templateModel 生成器模板可以引用的变量
     * @param filePathModel 文件路径可以引用的变量
     * @throws Exception
     */
    public Generator generateBy(Map<String, String> templateModel, Map<String, String> filePathModel) throws Exception {
        for (Map.Entry<String, String> entry : templateModel.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("java_typemapping")) {
                logger.info("[模板]" + key + " -> " + entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry : filePathModel.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("java_typemapping")) {
                logger.info("[文件]" + key + " -> " + entry.getValue());
            }
        }
        processTemplateRootDirs(templateModel, filePathModel, false);
        return this;
    }

    /**
     * 删除生成的文件
     *
     * @param templateModel 生成器模板可以引用的变量
     * @param filePathModel 文件路径可以引用的变量
     * @return
     * @throws Exception
     */
    public Generator deleteBy(Map templateModel, Map filePathModel) throws Exception {
        processTemplateRootDirs(templateModel, filePathModel, true);
        return this;
    }

    private void processTemplateRootDirs(Map templateModel, Map filePathModel, boolean isDelete) throws Exception {
        if (templateRootDirs.size() == 0)
            throw new IllegalStateException("'templateRootDirs' cannot empty");
        GeneratorException ge = new GeneratorException("generator occer error, Generator BeanInfo:"
                + BeanHelper.describe(this));
        for (int i = 0; i < this.templateRootDirs.size(); i++) {
            File templateRootDir = (File) templateRootDirs.get(i);
            logger.info("template dir:" + templateRootDir.getName());
            List<Exception> exceptions = scanTemplatesAndProcess(templateRootDir, templateModel,
                    filePathModel, isDelete);
            ge.addAll(exceptions);
        }
        if (!ge.exceptions.isEmpty())
            throw ge;
    }

    private List<Exception> scanTemplatesAndProcess(File templateRootDir, Map templateModel, Map filePathModel,
                                                    boolean isDelete) throws Exception {
        if (templateRootDir == null)
            throw new IllegalStateException("'templateRootDir' must be not null");
        logger.info("load template from dir [" + templateRootDir.getAbsolutePath() + ",outRootDir[" + new File(outRootDir).getAbsolutePath());
        List<File> srcFiles = new ArrayList<File>();
        String[] extensions = {"ftl", "xml", "java"};
        logger.info("处理文件的后缀:" + Arrays.toString(extensions));
        Collection<File> col = FileUtils.listFiles(templateRootDir, extensions, true);
        for (File file : col) {
            logger.info("发现源模板文件:" + file.getName());
            srcFiles.add(file);
        }
        List<Exception> exceptions = new ArrayList<>();

        for (int i = 0; i < srcFiles.size(); i++) {
            File srcFile = srcFiles.get(i);
            logger.info("src template:" + srcFile.getName() + " ,isDelete=" + isDelete);
            try {
                if (isDelete) {
                    new TemplateProcessor().executeDelete(templateRootDir, templateModel, filePathModel, srcFile);
                } else {
                    new TemplateProcessor().executeGenerate(templateRootDir, templateModel, filePathModel, srcFile);
                }
            } catch (Exception e) {
                if (ignoreTemplateGenerateException) {
                    logger.warn("iggnore generate error,template is:" + srcFile + " cause:" + e);
                    exceptions.add(e);
                } else {
                    throw e;
                }
            }
        }
        return exceptions;
    }

    private class TemplateProcessor {
        private GeneratorControl gg = new GeneratorControl();

        private void executeGenerate(File templateRootDir, Map templateModel, Map filePathModel, File srcFile)
                throws IOException {
            String templateFile = FileHelper.getRelativePath(templateRootDir, srcFile);
            if (GeneratorHelper.isIgnoreTemplateProcess(srcFile, templateFile, includes, excludes)) {
                logger.info("ignore template file:" + srcFile.getAbsolutePath());
                return;
            }
            if (isCopyBinaryFile && FileHelper.isBinaryFile(srcFile)) {
                String outputFilepath = proceeForOutputFilepath(filePathModel, templateFile);
                logger.info("[copy binary file by extention] from:" + srcFile + " => " + new File(getOutRootDir(), outputFilepath));
                IOHelper.copyAndClose(new FileInputStream(srcFile), new FileOutputStream(new File(getOutRootDir(), outputFilepath)));
                return;
            }

            try {
                String outputFilepath = proceeForOutputFilepath(filePathModel, templateFile);
                initGeneratorControlProperties(srcFile, outputFilepath);
                processTemplateForGeneratorControl(templateModel, templateFile);
                if (gg.isIgnoreOutput()) {
                    logger.info("[not generate] by gg.isIgnoreOutput()=true on template:" + templateFile);
                    return;
                }
                if (StringHelper.isNotBlank(gg.getOutputFile())) {
                    generateNewFileOrInsertIntoFile(templateFile, gg.getOutputFile(), templateModel);
                }
            } catch (Exception e) {
                throw new RuntimeException("generate oucur error,templateFile is:" + templateFile
                        + " => " + gg.getOutputFile() + " cause:" + e, e);
            }
        }

        private void executeDelete(File templateRootDir, Map templateModel, Map filePathModel, File srcFile)
                throws SQLException, IOException, TemplateException {
            String templateFile = FileHelper.getRelativePath(templateRootDir, srcFile);
            if (GeneratorHelper.isIgnoreTemplateProcess(srcFile, templateFile, includes, excludes)) {
                logger.info(String.format("忽略 srcFile[%s],templateFile[%s]", srcFile, templateFile));
                return;
            }
            String outputFilepath = proceeForOutputFilepath(filePathModel, templateFile);
            initGeneratorControlProperties(srcFile, outputFilepath);
            gg.deleteGeneratedFile = true;
            processTemplateForGeneratorControl(templateModel, templateFile);
            logger.info("[delete file] file:" + new File(gg.getOutputFile()).getAbsolutePath());
            new File(gg.getOutputFile()).delete();
        }

        private void initGeneratorControlProperties(File srcFile, String outputFile) throws SQLException {
            gg.setSourceFile(srcFile.getAbsolutePath());
            gg.setSourceFileName(srcFile.getName());
            gg.setSourceDir(srcFile.getParent());
            gg.setOutRoot(getOutRootDir());
            gg.setOutputEncoding(outputEncoding);
            gg.setSourceEncoding(sourceEncoding);
            gg.setMergeLocation(GENERATOR_INSERT_LOCATION);
            gg.setOutputFile(outputFile);
        }

        private void processTemplateForGeneratorControl(Map templateModel, String templateFile)
                throws IOException, TemplateException {
            templateModel.put("gg", gg);
            Template template = getFreeMarkerTemplate(templateFile);
            template.process(templateModel, IOHelper.NULL_WRITER);
        }

        /**
         * 处理文件路径的变量变成输出路径
         */
        private String proceeForOutputFilepath(Map filePathModel, String templateFile) throws IOException {
            String outputFilePath = templateFile;
            // TODO 删除兼容性的@testExpression
            int testExpressionIndex = -1;
            if ((testExpressionIndex = templateFile.indexOf('@')) != -1) {
                outputFilePath = templateFile.substring(0, testExpressionIndex);
                String testExpressionKey = templateFile.substring(testExpressionIndex + 1);
                Object expressionValue = filePathModel.get(testExpressionKey);
                if (expressionValue == null) {
                    System.err.println("[not-generate] WARN: test expression is null by key:["
                            + testExpressionKey + "] on template:[" + templateFile + "]");
                    return null;
                }
                if (!"true".equals(String.valueOf(expressionValue))) {
                    logger.info("[not-generate]\t test expression '@" + testExpressionKey
                            + "' is false,template:" + templateFile);
                    return null;
                }
            }
            for (String removeExtension : removeExtensions.split(",")) {
                if (outputFilePath.endsWith(removeExtension)) {
                    outputFilePath = outputFilePath.substring(0, outputFilePath.length()
                            - removeExtension.length());
                    if (outputFilePath.endsWith("Mapper")) {
                        outputFilePath += ".xml";
                        logger.info("remove file extensions:" + removeExtension + " ,add .xml sufix -> " + outputFilePath);
                    } else {
                        outputFilePath += ".java";
                        logger.info("remove file extensions:" + removeExtension + " ,add .xml sufix -> " + outputFilePath);
                    }
                    break;
                }
            }
            Configuration conf = GeneratorHelper.newFreeMarkerConfiguration(templateRootDirs,
                    sourceEncoding, "/filepath/processor/");
            return FreemarkerHelper.processTemplateString(outputFilePath, filePathModel, conf);
        }

        private Template getFreeMarkerTemplate(String templateName) throws IOException {
            return GeneratorHelper.newFreeMarkerConfiguration(templateRootDirs, sourceEncoding,
                    templateName).getTemplate(templateName);
        }

        private void generateNewFileOrInsertIntoFile(String templateFile, String outputFilepath,
                                                     Map templateModel) throws Exception {
            Template template = getFreeMarkerTemplate(templateFile);
            template.setOutputEncoding(gg.getOutputEncoding());

            File absoluteOutputFilePath = FileHelper.parentMkdir(outputFilepath);
            if (absoluteOutputFilePath.exists()) {
                StringWriter newFileContentCollector = new StringWriter();
                if (GeneratorHelper.isFoundInsertLocation(gg, template, templateModel,
                        absoluteOutputFilePath, newFileContentCollector)) {
                    logger.info("todo --> [insert]\t generate content into:" + outputFilepath);
                    IOHelper.saveFile(absoluteOutputFilePath, newFileContentCollector.toString(),
                            gg.getOutputEncoding());
                    return;
                }
            }

            if (absoluteOutputFilePath.exists() && !gg.isOverride()) {
                logger.info("[not generate]\t by gg.isOverride()=false and outputFile exist:"
                        + outputFilepath);
                return;
            }

            logger.info("[generate]" + templateFile + " ==> " + outputFilepath);
            FreemarkerHelper.processTemplate(template, templateModel, absoluteOutputFilePath,
                    gg.getOutputEncoding());
        }
    }

    static class GeneratorHelper {
        public static boolean isIgnoreTemplateProcess(File srcFile, String templateFile, String includes,
                                                      String excludes) {
            if (srcFile.isDirectory() || srcFile.isHidden())
                return true;
            if (templateFile.trim().equals(""))
                return true;
            if (srcFile.getName().toLowerCase().endsWith(".include")) {
                logger.info("[skip]\t\t endsWith '.include' template:" + templateFile);
                return true;
            }
            templateFile = templateFile.replace('\\', '/');
            for (String exclude : StringHelper.tokenizeToStringArray(excludes, ",")) {
                if (new AntPathMatcher().match(exclude.replace('\\', '/'), templateFile))
                    return true;
            }
            if (includes == null)
                return false;
            for (String include : StringHelper.tokenizeToStringArray(includes, ",")) {
                if (new AntPathMatcher().match(include.replace('\\', '/'), templateFile))
                    return false;
            }
            return true;
        }

        private static boolean isFoundInsertLocation(GeneratorControl gg, Template template, Map model,
                                                     File outputFile, StringWriter newFileContent) throws IOException, TemplateException {
            LineNumberReader reader = new LineNumberReader(new FileReader(outputFile));
            String line = null;
            boolean isFoundInsertLocation = false;
            // FIXME 持续性的重复生成会导致out of memory
            PrintWriter writer = new PrintWriter(newFileContent);
            while ((line = reader.readLine()) != null) {
                writer.println(line);
                // only insert once
                if (!isFoundInsertLocation && line.indexOf(gg.getMergeLocation()) >= 0) {
                    template.process(model, writer);
                    writer.println();
                    isFoundInsertLocation = true;
                }
            }

            writer.close();
            reader.close();
            return isFoundInsertLocation;
        }

        public static Configuration newFreeMarkerConfiguration(List<File> templateRootDirs,
                                                               String defaultEncoding, String templateName) throws IOException {
            Configuration conf = new Configuration();

            FileTemplateLoader[] templateLoaders = new FileTemplateLoader[templateRootDirs.size()];
            for (int i = 0; i < templateRootDirs.size(); i++) {
                templateLoaders[i] = new FileTemplateLoader((File) templateRootDirs.get(i));
            }
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);
            conf.setTemplateLoader(multiTemplateLoader);
            conf.setNumberFormat("###############");
            conf.setBooleanFormat("true,false");
            conf.setDefaultEncoding(defaultEncoding);
            List<String> autoIncludes = getParentPaths(templateName, "macro.include");
            List<String> availableAutoInclude = FreemarkerHelper.getAvailableAutoInclude(conf, autoIncludes);
            conf.setAutoIncludes(availableAutoInclude);
            logger.debug("set Freemarker.autoIncludes:" + availableAutoInclude + " for templateName:"
                    + templateName + " autoIncludes:" + autoIncludes);
            return conf;
        }

        public static List<String> getParentPaths(String templateName, String suffix) {
            String array[] = StringHelper.tokenizeToStringArray(templateName, "\\/");
            List<String> list = new ArrayList<String>();
            list.add(suffix);
            list.add(File.separator + suffix);
            String path = "";
            for (int i = 0; i < array.length; i++) {
                path = path + File.separator + array[i];
                list.add(path + File.separator + suffix);
            }
            return list;
        }
    }

    public static class GeneratorModel implements java.io.Serializable {
        public Map filePathModel;
        public Map templateModel;

        public GeneratorModel(Map templateModel, Map filePathModel) {
            this.templateModel = templateModel;
            this.filePathModel = filePathModel;
        }
    }
}
