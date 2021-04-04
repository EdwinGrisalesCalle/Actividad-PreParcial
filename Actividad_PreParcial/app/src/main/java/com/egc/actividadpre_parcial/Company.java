package com.egc.actividadpre_parcial;

import android.graphics.Typeface;
import android.icu.text.UFormat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company implements Serializable {

    String name;
    String nit;
    List<String> listWorkstation = new ArrayList<String>();
    List<Employee> listEmployees = new ArrayList<Employee>();

    public Company(String name, String nit, List<String> listWorkstation, List<Employee> listEmployees) {
        this.name = name;
        this.nit = nit;
        this.listWorkstation = listWorkstation;
        this.listEmployees = listEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public List<String> getListWorkstation() {
        return listWorkstation;
    }

    public void setListWorkstation(List<String> listWorkstation) {
        this.listWorkstation = listWorkstation;
    }

    public List<Employee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

 //   public void addEmploye(Employee employee){
   //     listEmployees.add(employee);
   // }
    public List<Employee> youngestEmployes() {
         List<Employee> emp = new ArrayList<>();

         Employee zero = listEmployees.get(0);
         int agemin = zero.age;
        for (Employee Listemp: listEmployees) {
            if (Listemp.age <= agemin){
             agemin = Listemp.age;
            }
        }
        emp = SearchEmp(agemin);
     return emp;
    }

    public List<Employee> OldestEmployes() {
        List<Employee> emp = new ArrayList<>();
        Employee emp1 = listEmployees.get(0);
        int agemax = emp1.age;
        for (Employee Listemp: listEmployees) {
            if (Listemp.age >= agemax){
                agemax = Listemp.age;
            }
        }
        emp = SearchEmp(agemax);
        return emp;
    }

    public List<Employee> salaryMin() {
        List<Employee> emp = new ArrayList<>();

        Employee zero = listEmployees.get(0);
        double agemin = zero.salary;
        for (Employee Listemp: listEmployees) {
            if (Listemp.salary <= agemin){
                agemin = Listemp.salary;
            }
        }
        emp = SearchEmp2(agemin);
        return emp;
    }

    public List<Employee> salaryMax() {
        List<Employee> emp = new ArrayList<>();
        Employee emp1 = listEmployees.get(0);
        double agemax = emp1.salary;
        for (Employee Listemp: listEmployees) {
            if (Listemp.salary >= agemax){
                agemax = Listemp.salary;
            }
        }
        emp = SearchEmp2(agemax);
        return emp;
    }



    public List<Employee> SearchEmp(int valueShearch) {
        List<Employee> empList = new ArrayList<>();
           for (Employee employee : listEmployees) {
                if (employee.age == valueShearch){
                empList.add(employee);
            }
        }
        return empList;
    }

    public List<Employee> SearchEmp2(Double valueShearch) {
        List<Employee> empList = new ArrayList<>();
        for (Employee employee : listEmployees) {
            if (employee.salary == valueShearch){
                empList.add(employee);
            }
        }
        return empList;
    }

    public Double SalaryProm(){
        int totalElements = listEmployees.size();
        Double sumSalary=0.0;
        Double result = 0.0;
        for (int i = 0; i <= listEmployees.size() - 1; i++) {
            sumSalary= (sumSalary) + (listEmployees.get(i).salary);
        }
        result = (sumSalary)/(totalElements);
        return result;
    }

    public String Count(){
            String resumen = "";
            int count = 0;
            double sumSalary = 0.0;
        DecimalFormat df = new DecimalFormat("###.##");

        double salaryPro;

        for (int i = 0; i <= listWorkstation.size() - 1; i++) {
            for (int j = 0; j <= listEmployees.size() - 1; j++) {
                if (listWorkstation.get(i).equals(listEmployees.get(j).workPosition)){//vigilante
                    count = count + 1;
                    sumSalary= sumSalary + listEmployees.get(j).salary;
                }else{
                    count =count + 0;
                    sumSalary = sumSalary + 0.0;
                }

            }
            if (count==0){
                salaryPro=0.0;
            }else{
                salaryPro = (sumSalary)/(count);
            }
            resumen= resumen + "\n" + "Cargo: " + listWorkstation.get(i) + " # personas en el cargo: " + count + "\n"
                    +"             Salario Promedio: " + "$"+ df.format(salaryPro)  ;
            count=0;
            sumSalary=0.0;
            //salaryPro = 0.0;
        }
        return resumen;
    }
}
