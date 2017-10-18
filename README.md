> 非常感谢帆软的客服,很耐心的帮忙解决问题.     
> 这里使用的是SpringBoot + FineReport8.0进行整合, 提供给大家进行参考。

整合报表工具

## 整合步骤
#### 1、 依赖包导入 lib 也可以把依赖包放到自己的仓库中进行引用   

	
#### 2、 引入报表工作需要的配置文件以及工作空间     
	src/main/webapp/WEB-INF 引入 finedb、plugins 、 reportlets 报表文件、 resources 报表需要的资源文件
	
	resources : config.xml 配置访问路径   datasource.xml 配置数据源连接地址和转码信息
	

#### 3、 注意报表的数据源使用的自己配置的数据源，不和程序共用；

