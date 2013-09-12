package com.micmiu.modules.support.springmvc.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.micmiu.modules.utils.Reflections;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public class PoiExcelView extends AbstractExcelView {

	public static final String FILE_NAME = "fileName";
	public static final String SHEET_NAME = "sheetName";
	public static final String TITLE = "title";
	public static final String COLUMN_MAP = "columnMap";
	public static final String ROW_DATA = "rowData";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = null == model.get(FILE_NAME) ? "exportinfo.xls"
				: model.get(FILE_NAME) + "";
		if (!fileName.toLowerCase().endsWith(".xls")) {
			fileName += ".xls";
		}
		String sheetName = null == model.get(SHEET_NAME) ? "info" : model
				.get(SHEET_NAME) + "";
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO-8859-1"));

		Map<String, String> showMap = (LinkedHashMap<String, String>) model
				.get(COLUMN_MAP);

		// 产生Excel表头
		HSSFSheet sheet = workbook.createSheet(sheetName);
		int rowIndex = 0;
		HSSFRow header = sheet.createRow(rowIndex++); // 第0行
		// 产生标题列
		int columnIndex = 0;
		for (Map.Entry<String, String> entry : showMap.entrySet()) {
			header.createCell(columnIndex++).setCellValue(entry.getValue());
		}

		List<Object> dataList = (List<Object>) model.get(ROW_DATA);
		// 填充数据
		for (Object data : dataList) {
			HSSFRow row = sheet.createRow(rowIndex++);
			columnIndex = 0;
			for (Map.Entry<String, String> entry : showMap.entrySet()) {
				row.createCell(columnIndex++).setCellValue(
						Reflections.invokeGetter(data, entry.getKey()) + "");
			}
		}
	}

}
