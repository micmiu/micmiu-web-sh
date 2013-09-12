package com.micmiu.framework.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ShowParam {

	String value();

	int width() default 80;

	boolean sortable() default false;

	String align() default "left";

}
