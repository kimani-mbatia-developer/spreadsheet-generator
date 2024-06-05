package com.dev.spreadsheet.generator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class xssfObj {

    //public final thingRepo rep;
    private String bookName;

    private List<String> sheets;

    private List<String> columnHeads;


    public xssfObj(String bookName, List<String>sheets, List<String>columnHeads){
        //this.rep = thingRepo;
        this.bookName = bookName;
        this.sheets = sheets;
        this.columnHeads = columnHeads;
        createSheets();
        createFile();
    }

    XSSFWorkbook wkbk = new XSSFWorkbook();


    public void createSheets(){
        for(var item: sheets){
            XSSFSheet spsht = wkbk.createSheet(item);
            XSSFRow rw = spsht.createRow(0);

//            XSSFRow rw = spsht.createRow(columnHeads.size());

//          for(var col:columnHeads){
//                Cell cl = rw.createCell(col.indexOf(col));
//                cl.setCellValue(col.toString());
//            }

            Iterator<String> colNum = columnHeads.iterator();

            columnHeads.forEach((c)->{
                Cell cl = rw.createCell(columnHeads.indexOf(c));
                cl.setCellValue(c.toString());
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
