package com.dev.spreadsheet.generator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//I've created a program that autogenerates data for upload. I will need some of the validators disabled so that I can stress-test the upload validators. Ill provide a frontend with settings for anyone to put in data.

@SpringBootApplication
public class SpreadsheetGeneratorApplication implements CommandLineRunner {

	public  EmployeeRepo employeeRepo;

	public final xssfObj file;

	ArrayList<String>splitNames = new ArrayList<>();

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
			//BufferedReader r =new BufferedReader(new FileReader(myObj));
			//String firstLine = r.readLine();
			//String [] s = firstLine.split(" ");
			//for(String i: s){splitNames.add(i);};

			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()){
				String fullName = myReader.nextLine();
				String [] split = fullName.split(" ");
				for(String i: split){
					splitNames.add(i);
					//System.out.println(i);
				}
				names.add(myReader.nextLine());
			}

		}catch(FileNotFoundException e){
				System.out.println(e);
			}

		//File fl = new File("New.txt");
		FileWriter fl = new FileWriter("./output.txt");
		BufferedWriter newNamesList = new BufferedWriter(fl);
		for(String n: splitNames){
			try{
				newNamesList.write(n);
				newNamesList.newLine();
				//System.out.println(n + " Has been added to the file");
			}catch (IOException e){
				System.out.println(e);
			}
			//System.out.println(n + " Has been added to the file");
		}
		newNamesList.close();

		ArrayList<Employee>employeeList= new ArrayList<>();

		//


		for(int i=0;i<9000;i++){
			Random rx = new Random();
			int rng = 100;
			int randFig1 = rx.nextInt(rng);
			int randFig2 = rx.nextInt(rng);

			int rng2 = 10000000;

			Employee newEmployee = new Employee(splitNames.get(randFig1)+" "+splitNames.get(randFig2));
			newEmployee.setStaffNumber(splitNames.indexOf(i));
			newEmployee.setMobile(07 + 92053976);
			newEmployee.setEmail(newEmployee.getName() + "@test.com");
			newEmployee.setGender("M");
			newEmployee.setIdNo(123456);
			Random rnd = new Random();
			int upperSal = 80000;
			int random_salary = rnd.nextInt(upperSal);
			newEmployee.setSalary(random_salary);
			employeeList.add(newEmployee);
		}


//		names.forEach((i)->{
//			//System.out.println(i);
//			Employee newEmployee = new Employee(i);
//			newEmployee.setStaffNumber(i.indexOf(i));
//			newEmployee.setMobile(792053976);
//			newEmployee.setEmail(i + "@test.com");
//			newEmployee.setGender("M");
//			newEmployee.setIdNo(123456);
//			Random rnd = new Random();
//			int upperSal = 80000;
//			int random_salary = rnd.nextInt(upperSal);
//			newEmployee.setSalary(random_salary);
//			employeeList.add(newEmployee);
//		});

		xssfObj file = new xssfObj(rdr,shts,columnHeadsInput,employeeList);

//		Random rnd = new Random();
//		int n1 = rnd.nextInt(1);



		employeeRepo.saveAll(employeeList);
	}
}
