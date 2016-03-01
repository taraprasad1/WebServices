package com.extpricechange.rest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ApachePoiExecUtil {

	static void iteratatefirstSheet(Workbook Iworkbook,
			StagePriceChangeValueObject Vo) {
		Sheet firstSheet = Iworkbook.getSheetAt(0);
		Iterator<Row> iteratorfirstSheet = firstSheet.iterator();
		iteratorfirstSheet.next();
		while (iteratorfirstSheet.hasNext()) {
			Row nextRowfs = iteratorfirstSheet.next();
			Iterator<Cell> cellIteratorfs = nextRowfs.cellIterator();

			while (cellIteratorfs.hasNext()) {
				Cell cellfs = cellIteratorfs.next();
				if (cellfs.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					System.out.print(cellfs.getNumericCellValue());
					Vo.setREQUESTER_ID((int) cellfs.getNumericCellValue());
				}
				System.out.print(" - ");
			}
			System.out.println();
		}

	}

	private static java.sql.Date getConEffectiveDate(java.util.Date effective) {
	
		return new java.sql.Date(effective.getTime());
	}

	static void iteratateSecondSheetCells(Iterator<Cell> cellIterator,
			StagePriceChangeValueObject Vo) {
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (cell.getColumnIndex() == AllConstants.col_zero) {
					Vo.setRPM_STAGE_PRICE_CHANGE_ID((int) cell
							.getNumericCellValue());// check here
				}

				if (cell.getColumnIndex() == AllConstants.col_one) {
					Vo.setPRICE_CHANGE_ID((int) cell.getNumericCellValue());
				}

				if (cell.getColumnIndex() == AllConstants.col_two) {
					Vo.setREASON_CODE((int) cell.getNumericCellValue());
				}

				if (cell.getColumnIndex() == AllConstants.col_three) {
					Vo.setITEM((int) cell.getNumericCellValue());
				}

				if (cell.getColumnIndex() == AllConstants.col_four) {
					Vo.setSKULIST((int) cell.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_five) {
					Vo.setDIFF_ID((int) cell.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_six) {
					Vo.setZONE_GROUP_DISPLAY_ID((int) cell
							.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_seven) {
					Vo.setZN_LOC_ID((int) cell.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_nine) {
					Vo.setZONE_NODE_TYPE((int) cell.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_ten) {
					Vo.setLINK_CODE((int) cell.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_eleven) {
					Vo.setCHANGE_TYPE((int) cell.getNumericCellValue());
				}
				if (cell.getColumnIndex() == AllConstants.col_twelve) {
					Vo.setCHANGE_AMOUNT((int) cell.getNumericCellValue());
				}

				if (cell.getColumnIndex() == 14) {
					Vo.setPRCG_STAT_CDE((int) cell.getNumericCellValue());

				}

				if (cell.getColumnIndex() == 16) {
					Vo.setLAST_UPD_USR_ID((int) cell.getNumericCellValue());
				}

				if (cell.getColumnIndex() == 18) {
					Vo.setEXTR_CDE((int) cell.getNumericCellValue());
				}

			}// end

			if (cell.getColumnIndex() == 10) {
				Vo.setEFFECTIVE_DATE(getConEffectiveDate(cell
						.getDateCellValue()));// check
			}

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

				if (cell.getColumnIndex() == 13) {
					Vo.setCHANGE_CURRENCY(cell.getStringCellValue());
				}

				if (cell.getColumnIndex() == 15) {
					Vo.setLAST_UPD_TMST(getConEffectiveDate(cell
							.getDateCellValue()));// Exception
				}

				if (cell.getColumnIndex() == 17) {
					Vo.setIPUT_FIL_NM(cell.getStringCellValue());
				}

			}

			// Test Code To be deletd
			cell.getColumnIndex();
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				System.out.print(cell.getNumericCellValue());
			}

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				System.out.print(cell.getStringCellValue());
			}
			// */

			System.out.print(" - ");
		}

		System.out.println();
	}

	static StagePriceChangeValueObject getPriceChangeNewVoRow() {

		return new StagePriceChangeValueObject();
	}

	static List GetInputPriceChangeReqVoList(Workbook workbook,
			Iterator<Row> iteratorsecondSheetrow,
			List<StagePriceChangeValueObject> listPriceChangeVo) {

		List listofVo = null;
		while (iteratorsecondSheetrow.hasNext()) {

			StagePriceChangeValueObject newrowVo;
			newrowVo = getPriceChangeNewVoRow();
			iteratatefirstSheet(workbook, newrowVo);
			Row nextRow = iteratorsecondSheetrow.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			iteratateSecondSheetCells(cellIterator, newrowVo);
			listPriceChangeVo.add(newrowVo);

		}

		listofVo = listPriceChangeVo;
		return listofVo;
	}

	static List prepareReturnInputPriceChangeList() throws IOException {
		List Inputlist = null;

		String excelFilePath = "Books.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(
				excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet secondSheet = workbook.getSheetAt(1);

		Iterator<Row> iteratorsecondSheetrow = secondSheet.iterator();
		iteratorsecondSheetrow.next();

		List<StagePriceChangeValueObject> listPriceChangeVo = new ArrayList<StagePriceChangeValueObject>();

		listPriceChangeVo = GetInputPriceChangeReqVoList(workbook,iteratorsecondSheetrow, listPriceChangeVo);
		Inputlist = listPriceChangeVo;
		workbook.close();
		inputStream.close();

		return Inputlist;
	}

	public static void main(String[] args) throws IOException {

		List inplist = prepareReturnInputPriceChangeList();	// Test Code To be deletd

		System.out.println(inplist);

	}

}
