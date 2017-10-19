/**   
 *
 * @author HYL   
 * @date 2017年10月18日 上午11:14:58
 * @version 0.1.0
 * @since 0.1.0  
 */
package com.hyl.chart.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fr.web.ReportServlet;

/**   
 * 
 * @author HYL
 * @date 2017年10月18日 上午11:14:58
 * @version 0.1.0
 * @since 0.1.0  
 */
//@Configuration
public class ReportServletConfig {

	//@Bean
	public ServletRegistrationBean ServletRegistrationBean() {
		// 报表组件 
		System.out.println("****************/ReportServer");
		return new ServletRegistrationBean(new ReportServlet(), "/ReportServer");
	}
	
}
