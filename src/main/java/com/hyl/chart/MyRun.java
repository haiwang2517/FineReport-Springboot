/**   
 *
 * @author HYL   
 * @date 2017年10月16日 下午7:28:52
 * @version 0.1.0
 * @since 0.1.0  
 */
package com.hyl.chart;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * @author HYL
 * @date 2017年10月16日 下午7:28:52
 * @version 0.1.0
 * @since 0.1.0
 */
@SpringBootApplication
public class MyRun extends SpringBootServletInitializer implements WebApplicationInitializer { // extends
															// WebMvcConfigurerAdapter

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyRun.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MyRun.class, args);
	}
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("启动容器啦");
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(servletContext);//将注册配置类和当前servletContext关联

        // 注册springMVC的DispatcherServlet
        Dynamic servlet =  servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }
	
}
