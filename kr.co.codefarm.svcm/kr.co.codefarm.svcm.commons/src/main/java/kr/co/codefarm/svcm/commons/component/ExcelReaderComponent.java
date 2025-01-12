package kr.co.codefarm.svcm.commons.component;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class ExcelReaderComponent {
	
	public List<Map<String, Object>> excelToList(MultipartFile file, List<String> keyMap) throws IOException {
		return this.excelToList(file, keyMap, true);
	}
	
	public List<Map<String, Object>> excelToList(MultipartFile file) throws IOException {
		return this.excelToList(file, null, false);
	}
	
	public List<Map<String, Object>> excelToList(MultipartFile file, boolean isHeaderInclude) throws IOException {
		return excelToList(file, null, isHeaderInclude);
	}
	
	public List<Map<String, Object>> excelToList(MultipartFile file, List<String> headerMap, boolean isHeaderInclude) throws IOException {
		final Workbook workbook = this.fileToWorkbook(file);
		final Sheet sheet       = workbook.getSheetAt(0);
		final int rowCount      = sheet.getPhysicalNumberOfRows();
		DataFormatter formatter = new DataFormatter();
		
		List<String> keyMap         = new ArrayList<>();
		List<Map<String, Object>> excelList = new ArrayList<>();
		
		if (headerMap != null) {
			keyMap.addAll(headerMap);
		}
		
		IntStream.range(0, rowCount).forEach(idx -> {
			Map<String, Object> rowMap = new HashMap<>();
			
			if (isHeaderInclude && headerMap == null && idx == 0) {
				sheet.getRow(idx).forEach(cell -> {
					keyMap.add(cell.getColumnIndex(), cell.getStringCellValue());
				});
			} else {
				try {
					sheet.getRow(idx).forEach(cell -> {
						String key   = this.getKey(keyMap, cell.getColumnIndex(), isHeaderInclude);
						String value = "";
	
						/*switch (cell.getCellType()) {
						case NUMERIC:
	//						value += new BigDecimal(cell.getNumericCellValue()).toPlainString();
							value += cell.getNumericCellValue();
							break;
						case BOOLEAN:
							value += cell.getBooleanCellValue();
							break;
						case FORMULA:
							value += cell.getCellFormula();
							break;
						case ERROR:
							value += cell.getErrorCellValue();
							break;
						case BLANK:
							value += "";
							break;
						default:
							value += cell.getStringCellValue();
						}*/
						value+= formatter.formatCellValue(cell);
						rowMap.put(key, value);
					});
					excelList.add(rowMap);
				}catch(NullPointerException e) {
					e.printStackTrace();
				}
			}
		});
		
		return excelList;
	}
	
	private String getKey(List<String> keyMap, int cellIndex, boolean isHeaderInclude) {
		String key = "";
		
		if (isHeaderInclude) {
			key = keyMap.get(cellIndex);
		} else {
			key = CellReference.convertNumToColString(cellIndex);
		}
		
		return key;
	}
	
	private Workbook fileToWorkbook(MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		Workbook workbook;
		if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
			workbook =  new XSSFWorkbook(inputStream);
		} else {
			workbook = new HSSFWorkbook(inputStream);
		}
		inputStream.close();
		return workbook;
	}

}
