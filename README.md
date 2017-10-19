> 非常感谢帆软的客服,很耐心的帮忙解决问题.     
> 这里使用的是SpringBoot + FineReport8.0进行整合, 提供给大家进行参考。

整合报表工具

## 整合步骤
#### 1、 依赖包导入 lib 也可以把依赖包放到自己的仓库中进行引用   

	
#### 2、 引入报表工作需要的配置文件以及工作空间     
	src/main/webapp/WEB-INF 引入 finedb、plugins 、 reportlets 报表文件、 resources 报表需要的资源文件
	
	resources : config.xml 配置访问路径   datasource.xml 配置数据源连接地址和转码信息
	

#### 3、 注意报表的数据源使用的自己配置的数据源，不和程序共用；



#### Tomcat 部署 和 WebLogic 部署方式

> 支持weblogic 需要移除冲突包   添加  javax.servlet:javax.servlet-api:3.0.1 依赖包      
>  1: spring-boot-starter-web 中的 tomcat依赖  exclude group:'org.springframework.boot', module:'spring-boot-starter-tomcat'     
>  2: org.springframework.boot:spring-boot-starter-tomcat  不要引用      
>  3: org.apache.tomcat.embed:tomcat-embed-jasper  不要引用     
	
	//////////////////////////////////////////////////  weblogic 需要引用的依赖   ////////////////////////////////////////////////////
	compile("org.springframework.boot:spring-boot-starter-web"){
		exclude group:'org.springframework.boot', module:'spring-boot-starter-tomcat'
	}
	//// 手动依赖 javax.servlet:javax.servlet-api:3.0.1
	compile('javax.servlet:javax.servlet-api:3.0.1')
	//////////////////////////////////////////////////  weblogic 需要引用的依赖   ////////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////  Tomcat 需要引用的依赖   ////////////////////////////////////////////////////
	//compile("org.springframework.boot:spring-boot-starter-web")
	//compile('org.apache.tomcat.embed:tomcat-embed-jasper')
	//compile('org.springframework.boot:spring-boot-starter-tomcat')
	//////////////////////////////////////////////////  Tomcat 需要引用的依赖   ////////////////////////////////////////////////////
	
	compile('org.springframework.boot:spring-boot-starter')
	testCompile('org.springframework.boot:spring-boot-starter-test')
	compile('org.springframework.boot:spring-boot-legacy:1.1.0.RELEASE')
	
	compile('fr:chart:8.0')
	compile('fr:code:8.0')
	compile('fr:performance:8.0')
	compile('fr:platform:8.0')
	compile('fr:report:8.0')
	compile('fr:third:8.0')
	compile('fr:plugin-com.fr.plugin.reportfit:0')
	compile('fr:plugin-com.fr.plugin.chart.vancharts-0:0')
	compile('fr:plugin-com.fr.plugin.chart.vancharts:1')
	
	compile('com.oracle:ojdbc6:11.2.0.3')
