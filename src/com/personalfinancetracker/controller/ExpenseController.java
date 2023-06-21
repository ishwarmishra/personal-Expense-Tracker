package com.personalfinancetracker.controller;

import com.personalfinancetracker.model.ExpenseEntity;
import com.personalfinancetracker.repository.IRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ExpenseController {

    public static void dashboard(IRepository<ExpenseEntity> expenseRepository) {
        System.out.println("Welcome to the Expense Tracker!");
        System.out.println("Please select an option:");
        System.out.println("1. Add Expense");
        System.out.println("2. Delete Expense");
        System.out.println("3. Update Expense");
        System.out.println("4. Find by ID");
        System.out.println("5. Find all Data");
        System.out.println("6. Exit");
        System.out.println("Please enter the choice:");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Add Expense");
                saveData(expenseRepository);
                dashboard(expenseRepository);
                break;
            
            case 2:
                System.out.println("Delete Expense");
                deleteData(expenseRepository);
                dashboard(expenseRepository);
                break;
            case 3:
                System.out.println("Update Expense");
                updateData(expenseRepository);
                dashboard(expenseRepository);
                break;
            case 4:
                System.out.println("Find by ID");
                findByIdData(expenseRepository);
                dashboard(expenseRepository);
                break;
            case 5:
                System.out.println("Find all Data");
                findAll(expenseRepository);
                dashboard(expenseRepository);
                break;
            case 6:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
                dashboard(expenseRepository);
                break;
        }
    }

    private static void saveData(IRepository<ExpenseEntity> expenseRepository) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter expense name:");
        String expenseName = sc.nextLine();
        
        System.out.println("Enter Expense amount:");
        BigDecimal amount = sc.nextBigDecimal();
        
        System.out.println("Enter date in this format (dd/mm/yyyy):");
        String date = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        ExpenseEntity expenseEntity = new ExpenseEntity(expenseName, amount, localDate);
        expenseRepository.add(expenseEntity);

        System.out.println("Expense added successfully.");
    }

    
    private static void deleteData(IRepository<ExpenseEntity> expenseRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID to delete:");
        int id = sc.nextInt();

        ExpenseEntity expenseEntity = expenseRepository.findById(id);
        if (expenseEntity != null) {
            expenseRepository.delete(id);
            System.out.println("Expense with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Expense with ID " + id + " not found.");
        }
    }

    private static void updateData(IRepository<ExpenseEntity> expenseRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID for the update of the expense name:");
        int id = sc.nextInt();

        ExpenseEntity expenseEntity = expenseRepository.findById(id);
        if (expenseEntity != null) {
            sc.nextLine(); 
            System.out.println("Enter expense name:");
            String expenseName = sc.nextLine();
            
            System.out.println("Enter amount:");
            BigDecimal amount = sc.nextBigDecimal();
            
            System.out.println("Enter date in this format (dd/mm/yyyy):");
            String date = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);

            expenseEntity.setExpense(expenseName);
            expenseEntity.setAmount(amount);
            expenseEntity.setDate(localDate);

            System.out.println("Expense with ID " + id + " updated successfully.");
        } else {
            System.out.println("Expense with ID " + id + " not found.");
        }
    }

    private static void findByIdData(IRepository<ExpenseEntity> expenseRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID to find:");
        int id = sc.nextInt();

        ExpenseEntity expenseEntity = expenseRepository.findById(id);
        if (expenseEntity != null) {
            System.out.println("Expense Details:");
            System.out.println("ID: " + expenseEntity.getId());
            System.out.println("Name: " + expenseEntity.getExpense());
            System.out.println("Amount: " + expenseEntity.getAmount());
            System.out.println("Date: " + expenseEntity.getDate());
        } else {
            System.out.println("Expense with ID " + id + " not found.");
        }
    }

    private static void findAll(IRepository<ExpenseEntity> expenseRepository) {
        System.out.println("All Expense Data:");
        expenseRepository.findAll().forEach(expenseEntity -> {
            System.out.println("ID: " + expenseEntity.getId());
            System.out.println("Name: " + expenseEntity.getExpense());
            System.out.println("Amount: " + expenseEntity.getAmount());
            System.out.println("Date: " + expenseEntity.getDate());
            System.out.println();
        });
    }
}
