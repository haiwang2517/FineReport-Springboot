/**   
 *
 * @author HYL   
 * @date 2017年10月16日 下午6:51:11
 * @version 0.1.0
 * @since 0.1.0  
 */
package com.hyl.chart.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.base.FRContext;

/**   
 * 
 * @author HYL
 * @date 2017年10月16日 下午6:51:11
 * @version 0.1.0
 * @since 0.1.0  
 */
@RestController
public class TestController {
	
	@RequestMapping("/one")
	public String show(){
		System.out.println(FRContext.getCurrentEnv().getPath());
		return "xx哈哈";
	}
}
