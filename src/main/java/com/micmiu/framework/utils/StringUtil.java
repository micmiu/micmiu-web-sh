package com.micmiu.framework.utils;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public class StringUtil {

	/**
	 * 把ids 转化为Long[]
	 * 
	 * @param ids
	 * @return
	 */
	public static Long[] parseIdstr(String ids) {
		if (null == ids || "".equals(ids)) {
			return new Long[0];
		}
		String[] idstrArr = ids.split(",");
		Long[] idArr = new Long[idstrArr.length];
		for (int i = 0; i < idstrArr.length; i++) {
			idArr[i] = Long.parseLong(idstrArr[i]);
		}
		return idArr;
	}

}
