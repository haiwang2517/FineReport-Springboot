/**   
 *
 * @author HYL   
 * @date 2017年10月16日 下午7:28:52
 * @version 0.1.0
 * @since 0.1.0  
 */
package com.hyl.chart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fr.web.ReportServlet;

/**
 * 
 * @author HYL
 * @date 2017年10月16日 下午7:28:52
 * @version 0.1.0
 * @since 0.1.0
 */
@SpringBootApplication
public class MyRun extends WebMvcConfigurerAdapter {

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MyRun.class, args);
	}

	@Bean
	public ServletRegistrationBean ServletRegistrationBean() {
		// 报表组件 
		return new ServletRegistrationBean(new ReportServlet(), "/ReportServer");
	}

}
