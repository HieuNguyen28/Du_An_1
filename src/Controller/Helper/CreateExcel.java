/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helper;

import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author LENOVO
 */
public class CreateExcel {

    public static Workbook ExportToExcel(JTable table, String titleSheet) {
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelFile = null;
        JFileChooser excelfileChooser = new JFileChooser();
        try {
            FileNameExtensionFilter excelExtensionFilter = new FileNameExtensionFilter("Excel file", "xls", "xlsx", "xlsm");
            excelfileChooser.setFileFilter(excelExtensionFilter);
            excelfileChooser.setMultiSelectionEnabled(false);
            int user = excelfileChooser.showDialog(null, "Create File");
            if (user == excelfileChooser.APPROVE_OPTION) {
                excelFile = new XSSFWorkbook();
                XSSFSheet excelSheet = excelFile.createSheet(titleSheet);
                XSSFRow excelRowTitle = excelSheet.createRow(0);

                for (int headings = 0; headings < table.getColumnCount(); headings++) {
                    excelRowTitle.createCell(headings).setCellValue(table.getColumnName(headings));
                }

                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    XSSFRow rowsTable = excelSheet.createRow(rows + 1);
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        rowsTable.createCell(cols).setCellValue(table.getModel().getValueAt(rows, cols).toString());
                    }
                }
                excelFOU = new FileOutputStream(excelfileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelFile.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Exported file successfully!");
            }
        } catch (HeadlessException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (excelBOU != null) {
                    excelBOU.close();
                }
                if (excelFOU != null) {
                    excelFOU.close();
                }
                if (excelFile != null) {
                    excelFile.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return excelFile;
    }
}
