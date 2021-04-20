package com.viettel.demo.common;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@Log4j2
public class ExportUtils {

    // Get list of property of class
    public static List<String> getProperties(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNames  = new ArrayList<>();
        for(int i = 0; i < fields.length; i++) {
            fieldNames.add(fields[i].getName());
            log.info(fieldNames.get(i));
        }
        return fieldNames;
    }

    // Convert camelCase to Title Case
    // "bookStore" => "Book Store"
    public static String toTitleCase(String s) {
        return StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(s), StringUtils.SPACE));
    }

    public static File writeExcel(Class clazz, List<?> data, String sheetName) throws IOException {
        // XSSFWorkBook == .xlsx
        Workbook workbook = new XSSFWorkbook();

        try {
            // Create sheet
            Sheet sheet = workbook.createSheet(sheetName);
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 4000);

            // Style for header
            CellStyle headerStyle = workbook.createCellStyle();

            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);

            // Create header
            Row header = sheet.createRow(0);
            List<String> headerContents = getProperties(clazz);
            for(int i = 0; i < headerContents.size(); i++) {
                String headerContent = toTitleCase(headerContents.get(i));
                Cell headerCell = header.createCell(i);
                headerCell.setCellValue(headerContent);
                headerCell.setCellStyle(headerStyle);
            }

            // Style for rows
            CellStyle style = workbook.createCellStyle();
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setWrapText(true);

            // Create rows
            for(int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                for(int j = 0; j < headerContents.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(BeanUtils.getProperty(data.get(i), headerContents.get(j)));
                    cell.setCellStyle(style);
                }
            }

            // Write to file
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            return new File(fileLocation);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            log.info("end writeExcel");
        }
        return null;
    }
}
