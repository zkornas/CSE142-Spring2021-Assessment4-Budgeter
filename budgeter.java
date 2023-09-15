// Zacharia Kornas
// 04.27.2021
// CSE 142 Section AX
// TA: Paul George Druta
// Take-home Assessment #4: Budgeter
//
// This programs calculates a users monthly and daily income/expenses

import java.util.*;

public class Budgeter {
   public static final int DAYS_IN_MONTH = 31; // Changes number of days in the month

   public static void main(String[] args) {
      // Creates an object that can read user input from console
      Scanner console = new Scanner(System.in);
      
      intro();
      double totalIncome = incomeExpense(console, "income");
      double totalExpenses = incomeExpense(console, "expense");
      calculateAverage(totalIncome, totalExpenses);
      calculateNetIncome(totalIncome, totalExpenses);
   }
   
   // Prints the beginning message explaining what the program is
   public static void intro() {
      System.out.println("This program asks for your monthly income and");
      System.out.println("expenses, then tells you your net monthly income.");
      System.out.println();
   }
   
   // Calculates the total expenses/income of the user
   // total expenses based on daily or monthly expenses
   // Returns the total expenses/income
   // Parameters:
   //    console - The object used to read user input
   //    value - Value used to dictate if calculating expenses or income
   public static double incomeExpense(Scanner console, String value){
      int expenseType = 1;
      if (value.equals("expense")) {
         System.out.print("Enter 1) monthly or 2) daily expenses? ");
         expenseType = console.nextInt();
      }
      System.out.print("How many categories of " + value + "? ");
      int valueCategories = console.nextInt();
      double totalValue = 0.0;
      for (int i = 1; i <= valueCategories; i++) {
         System.out.print("    Next " + value + " amount? $");
         totalValue += console.nextDouble();
      }
      System.out.println();
      if (value.equals("expense") && expenseType == 2) {
         totalValue = totalValue * DAYS_IN_MONTH;
      }
      return totalValue;
   }
   
   // Calculates total rounded income and expenses
   // and rounded daily average income and expenses
   // Parameters:
   //    totalIncome - exact total income of a user 
   //    totalExpense - exact total expense of a user
   public static void calculateAverage(double totalIncome, double totalExpenses) {
      double dailyIncome = round2(totalIncome / DAYS_IN_MONTH);
      double dailyExpenses = round2(totalExpenses / DAYS_IN_MONTH);
      double roundedTotalIncome = round2(totalIncome);
      double roundedTotalExpenses = round2(totalExpenses);
      System.out.println("Total income = $" + roundedTotalIncome 
                         + " ($" + dailyIncome + "/day)");
      System.out.println ("Total expenses = $" + roundedTotalExpenses 
                        + " ($" + dailyExpenses + "/day)");
      System.out.println();
   }
   
   // Calculates the rounded net income of a user after deducting expenses
   // Tells user what spender/saver category they fall into based on rounded net income
   // Also displays a custom message based on category
   // Parameters:
   //    totalIncome - exact total income of a user 
   //    totalExpense - exact total expense of a user
   public static void calculateNetIncome(double totalIncome, double totalExpenses) {
      double netIncome = round2(totalIncome - totalExpenses);
      if (netIncome > 0) {
         System.out.println("You earned $" + netIncome + " more than you spent this month.");
         if (netIncome > 250) {
            System.out.println("You're a big saver.");
            System.out.println("There are only so many rainy days, don't save too much!");
         } else {
            System.out.println("You're a saver.");
            System.out.println("A penny saved is a penny earned.");
         }
      } else {
         System.out.println("You spent $" + Math.abs(netIncome) + 
                            " more than you earned this month.");
         if (netIncome <= -250) {
            System.out.println("You're a big spender.");
            System.out.println("Maybe you should talk with a financial advisor.");
         } else {
            System.out.println("You're a spender.");
            System.out.println("Woah, slow down!");
         }
      }
   }
   
   // **TAKEN FROM LECTURE**
   // Rounds any number to two decimal places
   // Returns rounded value
   // Parameters
   //    num - the value to round
   public static double round2(double num) {
      return Math.round(num * 100.0) / 100.0;
   }
}
