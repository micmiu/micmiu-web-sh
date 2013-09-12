package com.micmiu.demo.web.v1.test;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class TestClassPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(new Date());

		System.out.println(DateUtils.parseDate("Thu Mar 07 15:26:25 CST 2013",
				"EEE MMM dd HH:mm:ss zzz yyyy"));

		// ApplicationContext ctx = new ClassPathXmlApplicationContext(
		// "classpath:/applicationContext.xml");
		// System.out.println(ctx.getClassLoader().getResource("").getPath());

		// ResourcePatternResolver resolver = new
		// PathMatchingResourcePatternResolver();
		//
		// Resource[] resources =
		// resolver.getResources("classpath*:application.properties");
		// for(Resource res:resources){
		// System.out.println(res.getURI());
		// }

	}
}
