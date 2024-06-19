package com.dev.spreadsheet.generator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class SpreadsheetGeneratorApplication implements CommandLineRunner {

	public  EmployeeRepo employeeRepo;

	public final xssfObj file;

	SpreadsheetGeneratorApplication(EmployeeRepo employeeRepo, xssfObj f){
		this.employeeRepo = employeeRepo;
		this.file = f;
	}

	public static void main(String[] args) throws IOException {




		SpringApplication.run(SpreadsheetGeneratorApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//SpringApplication.run(SpreadsheetGeneratorApplication.class, args);


		//Use this to name the document
		//System.out.println("Please enter your document name");
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//String rdr = r.readLine();
		String rdr = "Testdata";

		ArrayList<String> shts = new ArrayList<String>();
		shts.add("One");

		ArrayList<String> columnHeadsInput = new ArrayList<String>();

		String[]colItmes= {"Employee Staff No.","Employee Name","Employee Mobile","Employee email","Employee ID NO.","Employee Gender","Salary" };

		for(String i: colItmes){
			columnHeadsInput.add(i);
		}



		ArrayList<String>names= new ArrayList<>();

		try{
			File myObj = new File("C:\\Projects\\spreadsheet-generator\\src\\main\\resources\\RandomNames.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()){
				names.add(myReader.nextLine());
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}

		ArrayList<Employee>employeeList= new ArrayList<>();

		names.forEach((i)->{
			System.out.println(i);
			Employee newEmployee = new Employee(i);
			newEmployee.setStaffNumber(i.indexOf(i));
			newEmployee.setMobile(792053976);
			newEmployee.setEmail(i + "@test.com");
			newEmployee.setGender("M");
			newEmployee.setIdNo(123456);
			Random rnd = new Random();
			int upperSal = 80000;
			int random_salary = rnd.nextInt(upperSal);
			newEmployee.setSalary(random_salary);
			employeeList.add(newEmployee);
		});

		xssfObj file = new xssfObj(rdr,shts,columnHeadsInput,employeeList);

//		Random rnd = new Random();
//		int n1 = rnd.nextInt(1);



		employeeRepo.saveAll(employeeList);
	}
}
