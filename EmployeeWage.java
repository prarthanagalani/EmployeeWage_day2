package EmployeeWage_day2;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// interface
interface EmpWageInterface {
    void addCompanyEmpWage(CompanyEmpWage companyEmpWage);

    void calculateTotalWages();

    int getTotalWageByCompany(String companyName); // New method for Use Case 14
}

// for initializing all necessary info
class CompanyEmpWage {
    public final String companyName;
    public final int WAGE_PER_HOUR;
    public final int FULL_DAY_HOURS;
    public final int PART_TIME_HOURS;
    public final int WORKING_DAYS_PER_MONTH;
    public final int MAX_WORKING_HOURS;

    public int totalWage;
    public List<Integer> dailyWages;

    public CompanyEmpWage(String companyName, int wagePerHour, int fullDayHours, int partTimeHours,
            int workingDaysPerMonth, int maxWorkingHours) {
        this.companyName = companyName;
        this.WAGE_PER_HOUR = wagePerHour;
        this.FULL_DAY_HOURS = fullDayHours;
        this.PART_TIME_HOURS = partTimeHours;
        this.WORKING_DAYS_PER_MONTH = workingDaysPerMonth;
        this.MAX_WORKING_HOURS = maxWorkingHours;
        this.dailyWages = new ArrayList<>();
    }
}

// will calculate wages for multiple companies
class EmpWageBuilder implements EmpWageInterface {
    private final List<CompanyEmpWage> companyEmpWages;

    public EmpWageBuilder() {
        this.companyEmpWages = new ArrayList<>();
    }

    // overriden methods from interface
    @Override
    public void addCompanyEmpWage(CompanyEmpWage companyEmpWage) {
        this.companyEmpWages.add(companyEmpWage);
    }

    // for each company
    @Override
    public void calculateTotalWages() {
        for (CompanyEmpWage companyEmpWage : companyEmpWages) {
            calculateTotalWage(companyEmpWage);
            System.out
                    .println("Total Wage for Company " + companyEmpWage.companyName + ": " + companyEmpWage.totalWage);
            System.out
                    .println("Daily Wage for Company " + companyEmpWage.companyName + ": " + companyEmpWage.dailyWages);
        }
    }

    // for specific company
    private void calculateTotalWage(CompanyEmpWage companyEmpWage) {
        int totalWorkingHours = 0;
        int totalWorkingDays = 0;

        while (totalWorkingHours < companyEmpWage.MAX_WORKING_HOURS
                && totalWorkingDays < companyEmpWage.WORKING_DAYS_PER_MONTH) {
            boolean isFullTime = checkEmployeeAttendance() == 1;

            int dailyWage;
            if (isFullTime) {
                totalWorkingHours += companyEmpWage.FULL_DAY_HOURS;
                dailyWage = companyEmpWage.FULL_DAY_HOURS * companyEmpWage.WAGE_PER_HOUR;
            } else {
                totalWorkingHours += companyEmpWage.PART_TIME_HOURS;
                dailyWage = companyEmpWage.PART_TIME_HOURS * companyEmpWage.WAGE_PER_HOUR;
            }

            totalWorkingDays++;
            companyEmpWage.dailyWages.add(dailyWage);
        }

        companyEmpWage.totalWage = companyEmpWage.WAGE_PER_HOUR * totalWorkingHours;
    }


    private int checkEmployeeAttendance() {
        Random random = new Random();
       
        return random.nextInt(2); // 0 for absent, 1 for present
    }
   
    @Override
    public int getTotalWageByCompany(String companyName) {
        for (CompanyEmpWage companyEmpWage : companyEmpWages) {
            if (companyEmpWage.companyName.equals(companyName)) {
                return companyEmpWage.totalWage;
            }
        }
        return 0; // Return 0 if the company is not found
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

        // EmpWageBuilder empWageBuilder = new EmpWageBuilder(total_company);

        EmpWageInterface empWageBuilder = new EmpWageBuilder();

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
                    wagePerHour, fullDayHours, partTimeHours, workingDaysPerMonth, maxWorkingHours

            );
            
            // Add the CompanyEmpWage object to the EmpWageBuilder using the EmpWageInterface
            ((EmpWageBuilder) empWageBuilder).addCompanyEmpWage(companyEmpWage);
        }

        // Use Case 13: Calculate and Print Daily Wages for Each Company using ArrayList
        empWageBuilder.calculateTotalWages();

        // Use Case 14: query total wage for a specific company
        System.out.println("Enter the company name to get the total wage: ");
        String company = sc.next();
        int totalWage = empWageBuilder.getTotalWageByCompany(company);
        System.out.println("Total Wage for Company " + company + ": " + totalWage);

        sc.close();
    }

}