package com.micmiu.framework.web.v1.demo.vo;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;
import com.micmiu.framework.web.v1.demo.entity.Blog;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class BlogQuery extends AbstractPagedQuery<Blog> {

	private String title;

	@Override
	public String buildSQL(HashMap<String, Object> queryParams,
			boolean isQueryTotalCount) {
		StringBuilder sql = new StringBuilder();

		if (isQueryTotalCount) {// 查询总数
			sql.append("select count(*) from Blog t where (1=1)");
		} else {
			sql.append("select t from Blog t where (1=1)");
		}

		if (StringUtils.isNotBlank(title)) {
			queryParams.put("title", "%" + this.title + "%");
			sql.append(" and (t.title like:title)");
		}

		return sql.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



}
