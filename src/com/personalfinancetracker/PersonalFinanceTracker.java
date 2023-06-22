package com.personalfinancetracker;

import com.personalfinancetracker.controller.IncomeController;
import com.personalfinancetracker.controller.LoginController;
import com.personalfinancetracker.controller.ExpenseController;
import com.personalfinancetracker.model.ExpenseEntity;
import com.personalfinancetracker.model.IncomeEntity;
import com.personalfinancetracker.repository.ExpenseRepository;
import com.personalfinancetracker.repository.IRepository;
import com.personalfinancetracker.repository.IncomeRepository;

import java.util.Scanner;

public class PersonalFinanceTracker {
    public static void main(String[] args) {
        // Create repositories for income and expense
        IRepository<IncomeEntity> incomeRepository = new IncomeRepository();
        IRepository<ExpenseEntity> expenseRepository = new ExpenseRepository();

        // Create an instance of the LoginController
        LoginController loginController = new LoginController();

        // Perform login and proceed to the dashboard if successful
        if (loginController.login()) {
            dashboard(incomeRepository, expenseRepository);
        }
    }

    public static void dashboard(IRepository<IncomeEntity> incomeRepository, IRepository<ExpenseEntity> expenseRepository) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Personal Finance Tracker!");
            System.out.println("Please select an option:");
            System.out.println("1. Income Tracker");
            System.out.println("2. Expense Tracker");
            System.out.println("3. Exit");
            System.out.print("Please Enter the Choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    IncomeController incomeController = new IncomeController();
                    incomeController.dashboard((IncomeRepository) incomeRepository);
                    break;
                case 2:
                    ExpenseController expenseController = new ExpenseController();
                    expenseController.dashboard((ExpenseRepository)expenseRepository);
                    break;
                case 3:
                    System.out.println("Exiting Personal Finance Tracker...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
