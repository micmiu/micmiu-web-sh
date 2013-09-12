package com.micmiu.framework.web.v1.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micmiu.framework.web.v1.base.dao.BasicDao;
import com.micmiu.framework.web.v1.base.service.impl.BasicServiceImpl;
import com.micmiu.framework.web.v1.demo.dao.BlogDao;
import com.micmiu.framework.web.v1.demo.entity.Blog;
import com.micmiu.framework.web.v1.demo.service.BlogService;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Service("blogService")
public class BlogServiceImpl extends BasicServiceImpl<Blog, Long> implements
		BlogService {

	private BlogDao blogDao;

	@Autowired
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	@Override
	public BasicDao<Blog, Long> getBasicDao() {
		return this.blogDao;
	}

	@Override
	public int delBatch(String ids) {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			Blog blog = blogDao.findById(Long.parseLong(id));
			if (null != blog) {
				blogDao.delete(blog);
			}
		}
		return idArr.length;
	}

}
