package com.pnmp.common.util;

import java.io.*;
import java.text.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.*;
import org.apache.poi.ss.usermodel.*;

import com.pnmp.common.exception.*;

/**
 * Excel 帮助类
 * 
 * @author WangJinpeng
 * 
 */
public class ExcelHelper {
	/** 初始列 */
	private int initColIndex;
	/** 当前行 */
	private int curRowIndex;
	/** 当前列 */
	private int curColIndex;
	/** 表格 */
	private Sheet sheet = null;
	/** 当前行 */
	private Row curRow = null;
	/** 工作薄 */
	private Workbook workbook = null;
	/** 使用单例模式 */
	private static final ExcelHelper single = new ExcelHelper();

	public static ExcelHelper getInstance() {
		return single;
	}

	/**
	 * 解析Excel
	 * 
	 * @param filePath 文件路径
	 * @throws PnmpException CRM异常
	 */
	public ExcelHelper parse(String filePath) throws PnmpException {
		try {
			workbook = WorkbookFactory.create(new File(filePath));
		}
		catch (InvalidFormatException e) {
			throw new PnmpException("Excel文件格式错误 : " + e.getMessage());
		}
		catch (FileNotFoundException e) {
			throw new PnmpException("找不到文件 : " + e.getMessage());
		}
		catch (IOException e) {
			throw new PnmpException("Excel文件读取失败 : " + e.getMessage());
		}

		// 返回数据
		return this;
	}

	/**
	 * 保存Excel
	 * 
	 * @param filePath 路径
	 * @param workbook Workbook对象
	 * @throws PnmpException CRM异常
	 */
	public void save(String filePath) throws PnmpException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			workbook.write(out);
			out.close();
		}
		catch (FileNotFoundException e) {
			throw new PnmpException("文件输出异常:" + e.getMessage());
		}
		catch (IOException e) {
			throw new PnmpException("文件输出异常:" + e.getMessage());
		}
		finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			}
			catch (IOException e) {
				throw new PnmpException("文件输出异常:" + e.getMessage());
			}
		}
	}

	/**
	 * 转换Cell值
	 * 
	 * @param cell
	 * @return 返回String类型值
	 */
	public String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		String ret = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				ret = null;
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				ret = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				ret = null;
				break;
			case Cell.CELL_TYPE_FORMULA:
				Workbook wb = cell.getSheet().getWorkbook();
				CreationHelper crateHelper = wb.getCreationHelper();
				FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
				ret = getCellValue(evaluator.evaluateInCell(cell));
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					ret = new SimpleDateFormat(TimeHelper.TIMESTAMP_PATTERN).format(cell.getDateCellValue());
				}
				else {
					ret = new DecimalFormat("#").format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				ret = cell.getRichStringCellValue().getString();
				break;
			default:
				ret = null;
		}
		return ret == null ? null : ret.trim();
	}

	/**
	 * 创建新的一行
	 */
	public void creatNewRow() {
		curRow = sheet.createRow(curRowIndex);
		curRowIndex++;
		curColIndex = initColIndex;
	}

	/**
	 * 创建 String型数据
	 * 
	 * @param value 数据值
	 */
	public void createNewCol(String value) {
		Cell cell = curRow.createCell(curColIndex);
		setStyle(cell);
		cell.setCellValue(value);
		curColIndex++;
	}

	/**
	 * 创建 double型数据
	 * 
	 * @param value 数据值
	 */
	public void createNewCol(double value) {
		Cell cell = curRow.createCell(curColIndex);
		setStyle(cell);
		cell.setCellValue(value);
		curColIndex++;
	}

	/**
	 * 创建 boolean型数据
	 * 
	 * @param value 数据值
	 */
	public void createNewCol(boolean value) {
		Cell cell = curRow.createCell(curColIndex);
		setStyle(cell);
		cell.setCellValue(value);
		curColIndex++;
	}

	/**
	 * 创建 Date型数据
	 * 
	 * @param value 数据值
	 */
	public void createNewCol(Date value) {
		Cell cell = curRow.createCell(curColIndex);
		setStyle(cell);
		cell.setCellValue(value);
		curColIndex++;
	}

	/**
	 * 创建 Long型数据
	 * 
	 * @param value 数据值
	 */
	public void createNewCol(RichTextString value) {
		Cell cell = curRow.createCell(curColIndex);
		setStyle(cell);
		cell.setCellValue(value);
		curColIndex++;
	}

	/**
	 * 创建 Long型数据
	 * 
	 * @param value 数据值
	 */
	public void createNewCol(Long value) {
		Cell cell = curRow.createCell(curColIndex);
		setStyle(cell);
		cell.setCellValue(value);
		curColIndex++;
	}

	/**
	 * 创建 空数据
	 */
	public void createNewCol() {
		curColIndex++;
	}

	/**
	 * 设置样式
	 * 
	 * @param cell Cell
	 */
	private void setStyle(Cell cell) {
		// 初始化
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();

		// 居中
		style.setAlignment(CellStyle.ALIGN_CENTER);
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);

		// 设置样式
		cell.setCellStyle(style);
	}

	/**
	 * 获取初始列
	 * 
	 * @return 初始列
	 */
	public int getInitColIndex() {
		return initColIndex;
	}

	/**
	 * 设置出始列
	 * 
	 * @param initColIndex 初始列
	 */
	public void setInitColIndex(int initColIndex) {
		this.initColIndex = initColIndex;
	}

	/**
	 * 获取当前行
	 * 
	 * @return 当前行
	 */
	public int getCurRowIndex() {
		return curRowIndex;
	}

	/**
	 * 设置当前行
	 * 
	 * @param curRowIndex 当前行
	 */
	public void setCurRowIndex(int curRowIndex) {
		this.curRowIndex = curRowIndex;
	}

	/**
	 * 获取当前列
	 * 
	 * @return 当前列
	 */
	public int getCurColIndex() {
		return curColIndex;
	}

	/**
	 * 设置当前列
	 * 
	 * @param curColIndex 当前列
	 */
	public void setCurColIndex(int curColIndex) {
		this.curColIndex = curColIndex;
	}

	/**
	 * 获取表格
	 * 
	 * @return 表格
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * 设置表格
	 * 
	 * @param sheet 表格
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * 获取当前行
	 * 
	 * @return 当前行
	 */
	public Row getCurRow() {
		return curRow;
	}

	/**
	 * 设置当前行
	 * 
	 * @param curRow 当前行
	 */
	public void setCurRow(Row curRow) {
		this.curRow = curRow;
	}

	/**
	 * 获取工作薄
	 * 
	 * @return 工作薄
	 */
	public Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * 设置工作薄
	 * 
	 * @param workbook 工作薄
	 */
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
}