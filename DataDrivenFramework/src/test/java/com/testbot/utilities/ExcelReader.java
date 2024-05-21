package com.testbot.utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx";
    private static final String SHEET_NAME = "addCustomer";

    public Object[][] getData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(SHEET_NAME);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows - 1][cols];
        for (int rowNum = 1; rowNum < rows; rowNum++) {
            for (int colNum = 0; colNum < cols; colNum++) {
                data[rowNum - 1][colNum] = sheet.getRow(rowNum).getCell(colNum).toString();
            }
        }

        workbook.close();
        return data;
    }
}
