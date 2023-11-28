package EmployeeWage_day2;

import java.util.Random;
import java.util.Scanner;


class CompanyEmpWage {
    public final String companyName;
    public final int WAGE_PER_HOUR;
    public final int FULL_DAY_HOURS;
    public final int PART_TIME_HOURS;
    public final int WORKING_DAYS_PER_MONTH;
    public final int MAX_WORKING_HOURS;

    public int totalWage;

    public CompanyEmpWage(String companyName, int wagePerHour, int fullDayHours, int partTimeHours, int workingDaysPerMonth, int maxWorkingHours) {
        this.companyName = companyName;
        this.WAGE_PER_HOUR = wagePerHour;
        this.FULL_DAY_HOURS = fullDayHours;
        this.PART_TIME_HOURS = partTimeHours;
        this.WORKING_DAYS_PER_MONTH = workingDaysPerMonth;
        this.MAX_WORKING_HOURS = maxWorkingHours;
    }
}

// will calculate wages for multiple companies
class EmpWageBuilder {
    private final CompanyEmpWage[] companyEmpWages;
    
    public EmpWageBuilder(int totalCompanies) {
        this.companyEmpWages = new CompanyEmpWage[totalCompanies];
    }

    public void addCompanyEmpWage(int index, CompanyEmpWage companyEmpWage) {
        this.companyEmpWages[index] = companyEmpWage;
    } 
    
    // for each company
    public void calculateTotalWages() {
        for (int i = 0; i < companyEmpWages.length; i++) {
            calculateTotalWage(companyEmpWages[i]);
            System.out.println("Total Wage for Company " + companyEmpWages[i].companyName + ": " + companyEmpWages[i].totalWage);
        }
    }
    
    // for specific company
    private void calculateTotalWage(CompanyEmpWage companyEmpWage) {
        int totalWorkingHours = 0;
        int totalWorkingDays = 0;

        while (totalWorkingHours < companyEmpWage.MAX_WORKING_HOURS && totalWorkingDays < companyEmpWage.WORKING_DAYS_PER_MONTH) {
            boolean isFullTime = checkEmployeeAttendance() == 1;

            if (isFullTime) {
                totalWorkingHours += companyEmpWage.FULL_DAY_HOURS;
            } else {
                totalWorkingHours += companyEmpWage.PART_TIME_HOURS;
            }

            totalWorkingDays++;
        }

        companyEmpWage.totalWage = companyEmpWage.WAGE_PER_HOUR * totalWorkingHours;

        System.out.println("Total Wage for company "+companyEmpWage);
    }

    private int checkEmployeeAttendance() {
        Random random = new Random();
        return random.nextInt(2); // 0 for absent, 1 for present
    }
}

 
public class EmployeeWage {

    public static int total_company;

    public static void main(String[] args) {

        // Use case 1: printing welcome msg
        System.out.println("Welcome to Employee Wage Computation Program");

        Scanner sc = new Scanner(System.in);

        // Use case 8: adding multiple companies
        System.out.println("Enter total number of companies: ");
        total_company = sc.nextInt();

       

        EmpWageBuilder empWageBuilder = new EmpWageBuilder(total_company);

        for (int i = 0; i < total_company; i++) {
            System.out.println("Enter company name for company " + (i + 1));
            String companyName = sc.next();

            System.out.println("Enter WAGE_PER_HOUR for company " + (i + 1));
            int wagePerHour = sc.nextInt();

            
            System.out.println("Enter FULL_DAY_HOURS for company " + (i + 1));
            int fullDayHours = sc.nextInt();

            System.out.println("Enter PART_TIME_HOURS for company " + (i + 1));
            int partTimeHours = sc.nextInt();

            System.out.println("Enter WORKING_DAYS_PER_MONTH for company " + (i + 1));
            int workingDaysPerMonth = sc.nextInt();

            System.out.println("Enter MAX_WORKING_HOURS for company " + (i + 1));
            int maxWorkingHours = sc.nextInt();

            // Create CompanyEmpWage object and add to EmpWageBuilder
            CompanyEmpWage companyEmpWage = new CompanyEmpWage(
                    companyName,
                    wagePerHour,fullDayHours, partTimeHours, workingDaysPerMonth, maxWorkingHours
                    
            );

            empWageBuilder.addCompanyEmpWage(i, companyEmpWage);
        }

        // Use Case 10: Calculate and Print Total Wages for Each Company
        empWageBuilder.calculateTotalWages();


       sc.close();
    }

}