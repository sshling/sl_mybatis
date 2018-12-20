# fm注释，#号代替!
	<#-- fm注释：用来生成${value} -->
# 插值 ${...} 或 #{...}
- 通用插值`$`,数字格式化插值用`#`
- 通用插值4种：
  1. 字符串值，直接输出
  2. 数字值，根据默认格式(#setting指令设置)或内建字符串函数格式化输出

			<#settion number_format="currency"/>
			<#assign answer=42/>
			${answer?string} <#-- the same as ${answer} -->
			${answer?string.number}    
	
  3. 日期值，类似数字值    
			${lastUpdated?string("yyyy-MM-dd HH:mm:ss")}

  4. 布尔值
			<#assign foo=true/>
			${foo?string("yes", "no")}

- 数字格式化插值`#{expr;format}`
	format可以是:mX:小数部分最小X位，MX:小数部分最大X位
		<#assign x=2.582/>
		<#assign y=4/>
		#{x; M2} <#-- 输出2.58 -->
		#{y; M2} <#-- 输出4 -->
		#{x; m2} <#-- 输出2.6 -->
		#{y; m2} <#-- 输出4.0 -->
		#{x; m1M2} <#-- 输出2.58 -->
# FTL指令，类HTML，以"#"区分于HTML的"/"
- `<`、`/>`和指令间不允许有空格
- `@`而不是`#`   <--  用户指令

# r标记后的内容将直接输出
		${r"${foo}"}
		${r"C:\foo\bar"}
		输出结果是:
		${foo}
		C:\foo\bar

参考 http://blog.csdn.net/jbjwpzyl3611421/article/details/18652595

# 宏
- macro指令定义，调用使用`@`而不是默认的`#`

		<#macro greet person>     
		    <font size="+2">Hello ${person}!</font>     
		</#macro>   
		
		<@greet person="Fred"/> 等价于 <@greet "Fred"/> 

- 空白处理





- 参考
1. http://paskaa.iteye.com/blog/1508351
2.  






