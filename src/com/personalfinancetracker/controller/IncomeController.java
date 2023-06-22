package com.personalfinancetracker.controller;

import com.personalfinancetracker.model.IncomeEntity;
import com.personalfinancetracker.repository.IncomeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class IncomeController {

    public static void dashboard(IncomeRepository incomeRepository) {
        System.out.println("Welcome to the Income Tracker!");
        System.out.println("Please select an option:");
        System.out.println("1. Add Income Source");
        System.out.println("2. Delete Income");
        System.out.println("3. Update Income");
        System.out.println("4. Find by ID");
        System.out.println("5. Find all Data");
        System.out.println("6. Exit");
        System.out.println("Please Enter the Choice:");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Add Income");
                saveData(incomeRepository);
                dashboard(incomeRepository);
                break;
            case 2:
                System.out.println("Delete Income");
                deleteData(incomeRepository);
                dashboard(incomeRepository);
                break;
            case 3:
                System.out.println("Update Income");
                updateData(incomeRepository);
                dashboard(incomeRepository);
                break;
            case 4:
                System.out.println("Find by ID");
                findByIdData(incomeRepository);
                dashboard(incomeRepository);
                break;
            case 5:
                System.out.println("Find all Data");
                findAll(incomeRepository);
                dashboard(incomeRepository);
                break;
            case 6:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
                dashboard(incomeRepository);
                break;
        }
    }

    private static void saveData(IncomeRepository incomeRepository) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter income source:");
        String incomeSource = sc.nextLine();

        System.out.println("Enter amount:");
        BigDecimal amount = sc.nextBigDecimal();

        System.out.println("Enter date in this format (dd/mm/yyyy):");
        String date = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        int id = 1; 

        IncomeEntity incomeEntity = new IncomeEntity(id, incomeSource, amount, localDate);
        incomeRepository.add(incomeEntity);

        System.out.println("Income added successfully.");
    }

    private static void deleteData(IncomeRepository incomeRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID to delete:");
        int id = sc.nextInt();

        incomeRepository.delete(id);
        System.out.println("Income with ID " + id + " deleted successfully.");
    }

    private static void updateData(IncomeRepository incomeRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID for the update of the income source:");
        int id = sc.nextInt();

        IncomeEntity incomeEntity = incomeRepository.findById(id);

        if (incomeEntity != null) {
            sc.nextLine();
            System.out.println("Enter income source:");
            String incomeSource = sc.nextLine();

            System.out.println("Enter amount of Income:");
            BigDecimal amount = sc.nextBigDecimal();

            System.out.println("Enter date in this format (dd/mm/yyyy):");
            String date = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);

            incomeEntity.setIncome(incomeSource);
            incomeEntity.setAmount(amount);
            incomeEntity.setDate(localDate);

            incomeRepository.update(incomeEntity);

            System.out.println("Income with ID " + id + " updated successfully.");
        } else {
            System.out.println("Income with ID " + id + " not found.");
        }
    }

    private static void findByIdData(IncomeRepository incomeRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID to find:");
        int id = sc.nextInt();

        IncomeEntity incomeEntity = incomeRepository.findById(id);
        if (incomeEntity != null) {
            System.out.println("Income Details:");
            System.out.println("ID: " + incomeEntity.getId());
            System.out.println("Source: " + incomeEntity.getIncome());
            System.out.println("Amount: " + incomeEntity.getAmount());
            System.out.println("Date: " + incomeEntity.getDate());
        } else {
            System.out.println("Income with ID " + id + " not found.");
        }
    }

    private static void findAll(IncomeRepository incomeRepository) {
    System.out.println("All Income Data:");
    List<IncomeEntity> incomeEntities = incomeRepository.findAll();
    for (IncomeEntity incomeEntity : incomeEntities) {
        System.out.println("ID: " + incomeEntity.getId());
        System.out.println("Source: " + incomeEntity.getIncome());
        System.out.println("Amount: " + incomeEntity.getAmount());
        System.out.println("Date: " + incomeEntity.getDate());
        System.out.println();
    }
}

    }
