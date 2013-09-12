package com.micmiu.framework.web.v1.demo.service;

import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.demo.entity.Blog;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public interface BlogService extends BasicService<Blog, Long> {
	int delBatch(String ids);
}
