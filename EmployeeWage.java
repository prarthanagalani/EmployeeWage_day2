package EmployeeWage_day2;

import java.util.Random;
import java.util.Scanner;

public class EmployeeWage {

    // private static final int WAGE_PER_HOUR = 20;
    // private static final int FULL_DAY_HOURS = 8;
    // private static final int PART_TIME_HOURS = 4;
    // private static final int WORKING_DAYS_PER_MONTH = 20;
    // private static final int MAX_WORKING_HOURS = 100;

    public static int total_company;
    
    public static void main(String[] args) {

        // Use case 1: printing welcome msg
        System.out.println("Welcome to Employee Wage Computation Program");

        Scanner sc = new Scanner(System.in);

        // Use case 8: adding multiple companies
        System.out.println("Enter total number of companies: ");
        total_company = sc.nextInt();

        // 2d array to store company info
        int[][] company_info = new int[total_company][5];

        // adding all the info into company_info array
        // WAGE_PER_HOUR = 0
        // FULL_DAY_HOURS = 1
        // PART_TIME_HOURS = 2
        // WORKING_DAYS_PER_MONTH = 3
        // MAX_WORKING_HOURS = 4
        for (int i = 1; i <= total_company; i++) {

            System.err.println("Enter WAGE_PER_HOUR for company " + i);
            int WAGE_PER_HOUR = sc.nextInt();
            company_info[i - 1][0] = WAGE_PER_HOUR;

            System.err.println("Enter FULL_DAY_HOURS for company " + i);
            int FULL_DAY_HOURS = sc.nextInt();
            company_info[i - 1][1] = FULL_DAY_HOURS;

            System.err.println("Enter PART_TIME_HOURS for company " + i);
            int PART_TIME_HOURS = sc.nextInt();
            company_info[i - 1][2] = PART_TIME_HOURS;

            System.err.println("Enter WORKING_DAYS_PER_MONTH for company " + i);
            int WORKING_DAYS_PER_MONTH = sc.nextInt();
            company_info[i - 1][3] = WORKING_DAYS_PER_MONTH;

            System.err.println("Enter MAX_WORKING_HOURS for company " + i);
            int MAX_WORKING_HOURS = sc.nextInt();
            company_info[i - 1][4] = MAX_WORKING_HOURS;
        }

        System.out.println("Select an option:");
        System.out.println("1. Check Employee is Present or Absent");
        System.out.println("2. Calculate Daily Employee Wage");
        System.out.println("3. Add Part-time Employee & Wage");
        System.out.println("4. Calculate Wages for a Month");
        System.out.println("5. Calculate Wages till a condition is reached for a month");

        int option = sc.nextInt();

        // use case 6: switch-case
        switch (option) {
            case 1:
                int attendance = checkEmployeeAttendance();
                System.out.println("Employee is " + (attendance == 1 ? "Present" : "Absent"));
                break;
            case 2:
                if (checkEmployeeAttendance() == 1)
                    calculateDailyWage(company_info);
                else
                    System.out.println("Daily Employee Wage: 0");
                break;
            case 3:
                addPartTimeEmployee(company_info);
                break;
            case 4:
                calculateWagesForMonth(company_info);
                break;
            case 5:
                calculateWagesTillCondition(company_info);
                break;
            default:
                System.out.println("Invalid option");
        }

        sc.close();

    }

    // Use Case 1
    private static int checkEmployeeAttendance() {

        Random random = new Random();
        int attendance = random.nextInt(2); // 0 for absent, 1 for present
        return attendance;
    }

    // Use Case 2
    private static void calculateDailyWage(int[][] company_info) {
        Scanner sc = new Scanner(System.in);

        // asking for compnay name
        System.out.println("Enter company number between 1 to " + total_company + ") :");
        int company = sc.nextInt();

        // WAGE_PER_HOUR * FULL_DAY_HOURS;
        // Use case 8: getting company info and calculation of wage for that company
        int dailyWage = company_info[company - 1][0] * company_info[company - 1][1];
        System.out.println("Daily Employee Wage: " + dailyWage);

        sc.close();
    }

    // Use Case 3
    private static void addPartTimeEmployee(int[][] company_info) {
        Scanner sc = new Scanner(System.in);

        if (checkEmployeeAttendance() == 1) {
            System.out.println("Enter company number between 1 to " + total_company + ") :");
            int company = sc.nextInt();

            // WAGE_PER_HOUR * PART_TIME_HOURS;
            // Use case 8: getting company info and calculation of wage for that company
            int partTimeWage = company_info[company - 1][0] * company_info[company - 1][2];
            System.out.println("Part-time Employee Wage: " + partTimeWage);
        } else {
            System.out.println("Part-time Employee wage: 0");
        }

        sc.close();
    }

    // Use Case 4
    private static void calculateWagesForMonth(int[][] company_info) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter company number between 1 to " + total_company + ") :");
        int company = sc.nextInt();

        int monthlyWage = 0;
        int working_days = 1;

        // Use case 8: getting company info and calculation of wage for that company
        final int WORKING_DAYS_PER_MONTH = company_info[company - 1][3];
        final int WAGE_PER_HOUR = company_info[company - 1][0];
        final int PART_TIME_HOURS = company_info[company - 1][2];
        final int FULL_DAY_HOURS = company_info[company - 1][1];

        while (working_days <= WORKING_DAYS_PER_MONTH) {
            boolean is_fulltime = true;

            System.out.println("Enter 1 for full time and 0 for part time " + "for day " + working_days);
            int flag = sc.nextInt();
            // flag = 0 -> part-time employee
            if (flag == 0)
                is_fulltime = false;

            if (is_fulltime) {
                monthlyWage += WAGE_PER_HOUR * FULL_DAY_HOURS;
            }

            // part-time
            else {
                monthlyWage += WAGE_PER_HOUR * PART_TIME_HOURS;
            }

            working_days++;

        }

        System.out.println("Monthly Employee Wage: " + monthlyWage);

        sc.close();
    }

    // Use Case 5
    private static void calculateWagesTillCondition(int[][] company_info) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter company number between 1 to " + total_company + ") :");
        int company = sc.nextInt();

        int totalWorkingHours = 0;
        int totalWorkingDays = 0;

        // Use case 8: getting company info and calculation of wage for that company
        final int MAX_WORKING_HOURS = company_info[company - 1][4];
        final int WORKING_DAYS_PER_MONTH = company_info[company - 1][3];
        final int FULL_DAY_HOURS = company_info[company - 1][1];
        final int PART_TIME_HOURS = company_info[company - 1][2];
        final int WAGE_PER_HOUR = company_info[company - 1][0];

        while (totalWorkingHours < MAX_WORKING_HOURS && totalWorkingDays < WORKING_DAYS_PER_MONTH) {

            boolean is_fulltime = true;

            System.out.println("Enter 1 for full time and 0 for part time " + "for day " + totalWorkingDays);
            int flag = sc.nextInt();
            if (flag == 0)
                is_fulltime = false;

            if (is_fulltime) {
                totalWorkingHours += FULL_DAY_HOURS;

            } else {
                totalWorkingHours += PART_TIME_HOURS;
            }

            totalWorkingDays++;
        }

        int totalWage = WAGE_PER_HOUR * totalWorkingHours;
        System.out.println("Wages till condition: " + totalWage);

        sc.close();
    }

}