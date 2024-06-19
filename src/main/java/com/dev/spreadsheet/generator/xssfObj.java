package com.dev.spreadsheet.generator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.String;

@Component
public class xssfObj {

    //public final thingRepo rep;
    private String bookName;

    private List<String> sheets;

    private List<String> columnHeads;

    private ArrayList<Employee> data;

    public xssfObj(){}


    public xssfObj(String bookName, List<String>sheets, List<String>columnHeads, ArrayList<Employee>dt){
        //this.rep = thingRepo;
        this.bookName = bookName;
        this.sheets = sheets;
        this.columnHeads = columnHeads;
        this.data = dt;
        createSheets();
        createFile();

    }

    XSSFWorkbook wkbk = new XSSFWorkbook();


    public void createSheets(){
        for(var item: sheets){
            XSSFSheet spsht = wkbk.createSheet(item);
            XSSFRow HeaderRw = spsht.createRow(0);

//            XSSFRow rw = spsht.createRow(columnHeads.size());

//          for(var col:columnHeads){
//                Cell cl = rw.createCell(col.indexOf(col));
//                cl.setCellValue(col.toString());
//            }

            Iterator<String> colNum = columnHeads.iterator();

            columnHeads.forEach((c)->{
                Cell cl = HeaderRw.createCell(columnHeads.indexOf(c));
                cl.setCellValue(c.toString());
            });

            data.forEach((d)->{
                XSSFRow dataRw = spsht.createRow((data.indexOf(d)+1));
                Cell EmployeeStaffNo = dataRw.createCell(0);
                EmployeeStaffNo.setCellValue(d.getStaffNumber());
                Cell EmployeeName = dataRw.createCell(1);
                EmployeeName.setCellValue(d.getName());
                Cell EmployeeMobile = dataRw.createCell(2);
                EmployeeMobile.setCellValue(d.getMobile());
                Cell EmployeeEmail = dataRw.createCell(3);
                EmployeeEmail.setCellValue(d.getEmail());
                Cell EmployeeIDNO = dataRw.createCell(4);
                EmployeeIDNO.setCellValue(d.getIdNo());
                Cell EmployeeGender = dataRw.createCell(5);
                EmployeeGender.setCellValue(d.getGender());
                Cell Salary = dataRw.createCell(6);
                Salary.setCellValue(d.getSalary());

            });



        }
    }

    private void createCellStyle(){
        CellStyle style = wkbk.createCellStyle();
        //style.setDataFormat();
//        style.setFillBackgroundColor("yellow");
    }

    public void createMasterRow(){

        //cl.setCellValue();
    }

    public void createFile(){
        try {
            FileOutputStream outF = new FileOutputStream (bookName +".xlsx");
            wkbk.write(outF);
            outF.close();
            System.out.println("Excel sheet created");

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }




















}
