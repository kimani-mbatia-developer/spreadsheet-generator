package com.dev.spreadsheet.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dev.spreadsheet.generator.xssfObj;

import java.io.*;
import java.io.InputStreamReader;
import java.util.ArrayList;

@SpringBootApplication
public class SpreadsheetGeneratorApplication {

	public final xssfObj file;

	SpreadsheetGeneratorApplication( xssfObj f){
		this.file = f;
	}

	public static void main(String[] args) throws IOException {

		System.out.println("Please enter your document name");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String rdr = r.readLine();

		ArrayList<String> shts = new ArrayList<String>();
		shts.add("One");
		shts.add("Two");
		shts.add("Three");


		ArrayList<String> columnHeadsInput = new ArrayList<String>();
		columnHeadsInput.add("Company Name");
		columnHeadsInput.add("Subsidiary");
		columnHeadsInput.add("Full Name");
		columnHeadsInput.add("National ID");
		columnHeadsInput.add("Mobile No.");
		columnHeadsInput.add("Net Pay");
		columnHeadsInput.add("Employment Start Date");


		xssfObj file = new xssfObj(rdr,shts,columnHeadsInput);



//		SpringApplication.run(SpreadsheetGeneratorApplication.class, args);
	}


}
