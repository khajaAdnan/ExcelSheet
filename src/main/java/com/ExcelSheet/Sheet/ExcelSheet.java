package com.ExcelSheet.Sheet;

import com.ExcelSheet.Model.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelSheet {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] Headers = { "StudentId", "StudentName", "mobileNumber", "email" };
    static String SHEET = "Students";


    public static ByteArrayResource studentsToExcel(List<Student> students) throws IOException {
     try (
    Workbook workbook = new XSSFWorkbook();
    ByteArrayOutputStream out = new ByteArrayOutputStream();) {
        Sheet sheet = workbook.createSheet(SHEET);

         // Header
         Row headerRow = sheet.createRow(0);

         for (int col = 0; col < Headers.length; col++) {
             Cell cell = headerRow.createCell(col);
             cell.setCellValue(Headers[col]);
         }

         int rowIdx = 1;
         for (Student student : students) {
             Row row = sheet.createRow(rowIdx++);

             row.createCell(0).setCellValue(student.getStudentId());
             row.createCell(1).setCellValue(student.getStudentName());
             row.createCell(2).setCellValue(student.getMobileNumber());
             row.createCell(3).setCellValue(student.getEmail());
         }
         workbook.write(out);

         return new ByteArrayResource(out.toByteArray());
     } catch (IOException e) {
         throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
     }
    }
}
