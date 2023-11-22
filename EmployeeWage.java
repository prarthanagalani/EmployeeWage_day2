package EmployeeWage_day2;

import java.util.Random;
import java.util.Scanner;

public class EmployeeWage{

    private static final int WAGE_PER_HOUR = 20;
    private static final int FULL_DAY_HOURS = 8;
    private static final int PART_TIME_HOURS = 4;
    private static final int WORKING_DAYS_PER_MONTH = 20;
    private static final int MAX_WORKING_HOURS = 100;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program");

        int attendance = checkEmployeeAttendance();
        System.out.println("Employee is " + (attendance == 1 ? "Present" : "Absent"));

        if(checkEmployeeAttendance() == 1)
            calculateDailyWage();
        else
            System.out.println("Daily Employee Wage: 0");

        //use case 3
        addPartTimeEmployee();

        //use case 4
        calculateWagesForMonth();
    }

    private static int checkEmployeeAttendance() {
        Random random = new Random();
        int attendance = random.nextInt(2); // 0 for absent, 1 for present
        return attendance;
    }

    // Use Case 2
    private static void calculateDailyWage() {
        int dailyWage = WAGE_PER_HOUR * FULL_DAY_HOURS;
        System.out.println("Daily Employee Wage: " + dailyWage);
    }

    // Use Case 3
    private static void addPartTimeEmployee() {
        if(checkEmployeeAttendance() == 1)
        {
        int partTimeWage = WAGE_PER_HOUR * PART_TIME_HOURS;
        System.out.println("Part-time Employee Wage: " + partTimeWage);
        }
        else{
            System.out.println("Part-time Employee wage: 0");
        }
    }

     // Use Case 4
    private static void calculateWagesForMonth() {

       Scanner sc = new Scanner(System.in);
       int monthlyWage = 0;
       int working_days=1;

       while(working_days <= WORKING_DAYS_PER_MONTH)
       {
          boolean is_fulltime = true;

          System.out.println("Enter 1 for full time and 0 for part time "+"for day "+working_days);
          int flag = sc.nextInt();
          if(flag == 0)
          is_fulltime = false;

          if(is_fulltime)
          {
            monthlyWage += WAGE_PER_HOUR * PART_TIME_HOURS; 
          }

         

          working_days++;



       }

       System.out.println("Monthly Employee Wage: " + monthlyWage);

       sc.close();
    }

}