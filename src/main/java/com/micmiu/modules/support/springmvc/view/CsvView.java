package com.micmiu.modules.support.springmvc.view;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.micmiu.modules.utils.Reflections;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public class CsvView extends AbstractView {

	public static final String FILE_NAME = "fileName";
	public static final String COLUMN_MAP = "columnMap";
	public static final String ROW_DATA = "rowData";

	private final static String DEF_DELIMITER = ",";

	private final static String DEF_ENTER = "\r\n";

	private final static byte[] CVSHEAD = { (byte) 0xEF, (byte) 0xBB,
			(byte) 0xBF };

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = null == model.get(FILE_NAME) ? "exportinfo.csv"
				: model.get(FILE_NAME) + "";
		if (!fileName.toLowerCase().endsWith(".csv")) {
			fileName += ".csv";
		}
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
		response.setContentType("text/csv");
		response.setCharacterEncoding("UTF-8");

		Map<String, String> showMap = (LinkedHashMap<String, String>) model
				.get(COLUMN_MAP);

		OutputStream out;
		out = response.getOutputStream();
		out.write(CVSHEAD);
		for (Map.Entry<String, String> entry : showMap.entrySet()) {
			out.write(entry.getValue().getBytes("UTF-8"));
			out.write(DEF_DELIMITER.getBytes("UTF-8"));
		}
		out.write(DEF_ENTER.getBytes());

		List<Object> dataList = (List<Object>) model.get(ROW_DATA);
		for (Object data : dataList) {
			for (Map.Entry<String, String> entry : showMap.entrySet()) {
				out.write((Reflections.invokeGetter(data, entry.getKey()) + "")
						.getBytes("UTF-8"));
				out.write(DEF_DELIMITER.getBytes("UTF-8"));
			}
			out.write(DEF_ENTER.getBytes());
		}
		out.flush();

	}

}
