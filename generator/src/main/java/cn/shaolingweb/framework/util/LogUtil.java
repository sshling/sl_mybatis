///*
// * Copyright Bruce Liang (ldcsaa@gmail.com)
// *
// * Version	: JessMA 3.4.1
// * Author	: Bruce Liang
// * Website	: http://www.jessma.org
// * Project	: http://www.oschina.net/p/portal-basic
// * Blog		: http://www.cnblogs.com/ldcsaa
// * WeiBo	: http://weibo.com/u/1402935851
// * QQ Group	: 75375912
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package cn.shaolingweb.framework.util;
//
//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.Configurator;
//
///**
// * 
// * 日志记录器（用 log4j 实现）
// *
// */
//public class LogUtil
//{
//	/** 默认日志上下文名称 */
//	public static final	String DEFAULT_CONTEXT_NAME		= "JessMA Context";
//	/** 默认配置文件 */
//	public static final	String DEFAULT_CONFIG_FILE_NAME	= "log4j2.xml";
//	
//	private static Logger defaultLogger;
//	private static LoggerContext context;
//	
//	/** 设置应用程序默认日志记录器名称 */
//	public static final void setDefaultLoggerName(Class<?> clazz)
//	{
//		defaultLogger = LogManager.getLogger(clazz);
//	}
//	
//	/** 设置应用程序默认日志记录器名称 */
//	public static final void setDefaultLoggerName(String name)
//	{
//		defaultLogger = LogManager.getLogger(name);
//	}
//	
//	/** 获取应用程序默认日志记录器对象 */
//	public static final Logger getDefaultLogger()
//	{
//		return defaultLogger;
//	}
//	
//	/** 初始化日志上下文 */
//	public static final void initialize()
//	{
//		String file = GeneralHelper.getClassResourcePath(LogUtil.class, DEFAULT_CONFIG_FILE_NAME);
//		initialize(DEFAULT_CONTEXT_NAME, file);
//	}
//	
//	/** 初始化日志上下文 */
//	public static final void initialize(String file)
//	{
//		initialize(DEFAULT_CONTEXT_NAME, file);
//	}
//	
//	/** 初始化日志上下文 */
//	public static final void initialize(String contextName, String file)
//	{
//		context = Configurator.initialize(contextName, file);
//	}
//	
//	/** 关闭日志上下文 */
//	public static final void shutdown()
//	{
//		if(context != null)
//		{
//			Configurator.shutdown(context);
//			
//			context			= null;
//			defaultLogger	= null;
//		}
//	}
//	
//	/** 检查日志系统是否可用 */
//	public static final boolean isValid()
//	{
//		return context != null;
//	}
//	
//	/** 检查默认日志记录器是否可用 */
//	public static final boolean isDefaultLoggerValid()
//	{
//		return defaultLogger != null;
//	}
//	
//	/** 记录操作异常日志 */
//	public static final void exception(Throwable e, Object action, Level level,boolean printStack)
//	{
//		StringBuilder msg = new StringBuilder("Execption occur on ");
//		msg.append(action);
//		msg.append(" --> ");
//		msg.append(e.toString());
//		String error = msg.toString();
//
//		if(printStack)
//			defaultLogger.log(level, error, e);
//		else
//			defaultLogger.log(level, error);
//	}
//	
//	/** 记录操作失败日志 */
//	public static final void fail(Object action, Object module, boolean printStack)
//	{
//		StringBuilder msg = new StringBuilder("Operation fail on ");
//		msg.append(action);
//		msg.append(" --> ");
//		msg.append(module);
//
//		defaultLogger.error(msg.toString());
//	}
//	
//	/** 记录服务器启动日志 */
//	public static final void logServerStartup(Object o)
//	{
//		defaultLogger.info("->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->");
//		defaultLogger.info("starting: {}", o);
//	}
//	
//	/** 记录服务器关闭日志 */
//	public static final void logServerShutdown(Object o)
//	{
//		defaultLogger.info("stoping: {}", o);
//		defaultLogger.info("<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-");
//	}
//}
