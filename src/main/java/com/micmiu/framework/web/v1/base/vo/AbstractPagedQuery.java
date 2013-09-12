package com.micmiu.framework.web.v1.base.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 分页查询对象
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * @param <T>
 */
public abstract class AbstractPagedQuery<T> {

	/**
	 * 查询条件:查询起始纪录位置.
	 */
	private int page = 1;
	/**
	 * 分页条件:一页的记录条数.
	 */
	private int rows = DEFAULT_ITEMS_PERPAGE;

	/**
	 * 排序字段
	 */
	private String sortName;

	/**
	 * 默认一页显示的记录数.
	 */
	private static final int DEFAULT_ITEMS_PERPAGE = 15;

	/**
	 * 查询的结果集合.
	 */
	private List<T> queryResults;

	/**
	 * 查询结果,总共条目个数.
	 */
	private long totalCount = -1;

	/**
	 * @return the rp
	 */
	public int getRp() {
		return rows;
	}

	/**
	 * @param rp
	 *            the rp to set
	 */
	public void setRows(int rp) {
		this.rows = rp;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the start 起始记录
	 */
	public int getStart() {
		return (page - 1) * rows;
	}

	public int getEnd() {
		return page * rows;
	}

	/**
	 * @return Returns the queryResults.
	 */
	public List<T> getQueryResults() {
		return queryResults;
	}

	/**
	 * @param pQueryResults
	 *            The queryResults to set.
	 */
	public void setQueryResults(List<T> pQueryResults) {
		this.queryResults = pQueryResults;
	}

	/**
	 * @return Returns the totalCount.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param pTotalCount
	 *            The totalCount to set.
	 */
	public void setTotalCount(long pTotalCount) {
		this.totalCount = pTotalCount;
	}

	public Query buildQuery(Session session, boolean isQueryTotalCount) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(buildSQL(queryParams,
				isQueryTotalCount));

		Query query = session.createQuery(sql.toString());
		// 设置查询参数
		for (Entry<String, Object> entry : queryParams.entrySet()) {
			query.setParameter(entry.getKey(), queryParams.get(entry.getKey()));
		}
		return query;

	}

	/**
	 * 构造分页查询语句
	 * 
	 * @param queryParams
	 * @param isQueryTotalCount
	 * @return
	 */
	public abstract String buildSQL(HashMap<String, Object> queryParams,
			boolean isQueryTotalCount);

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * 根据pageSize与rp计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / rows;
		if (totalCount % rows > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (page + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return page + 1;
		} else {
			return page;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (page - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return page - 1;
		} else {
			return page;
		}
	}

}
