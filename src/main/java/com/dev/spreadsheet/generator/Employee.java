package com.dev.spreadsheet.generator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
public @Data class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    int staffNumber;
    String name;
    int mobile;
    String email;
    long IdNo;
    String Gender;
    int salary;

//    public Employee(int staffNumber, String name, int mobile, String email, long idno,
//                    String gender, int sal){
//         this.staffNumber = staffNumber;
//         this.name = name;
//         this.mobile= mobile;
//         this.email = email;
//         this.IdNo = idno;
//         this.Gender = gender;
//         this.salary = sal;
//    }

    public Employee(String name){
        this.name=name;
    }
}
