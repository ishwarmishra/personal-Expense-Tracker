package com.personalfinancetracker.controller;

import com.personalfinancetracker.model.IncomeEntity;
import com.personalfinancetracker.repository.IncomeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static void saveData(IncomeRepository incomeRepository) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter income source:");
        String incomeSource = sc.nextLine();

        System.out.println("Enter amount:");
        BigDecimal amount = readValidAmount(sc);

        System.out.println("Enter date in this format (dd/mm/yyyy):");
        String date = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = readValidDate(sc);


        int id = 1; 

        IncomeEntity incomeEntity = new IncomeEntity(id, incomeSource, amount, localDate);
        incomeRepository.add(incomeEntity);

        System.out.println("Income added successfully.");
    }
    
      private static BigDecimal readValidAmount(Scanner sc) {
        BigDecimal amount = null;
        boolean isValid = false;

        while (!isValid) {
            String input = sc.nextLine();

            try {
                amount = new BigDecimal(input);
                if (amount.compareTo(BigDecimal.ZERO) >= 0) {
                    isValid = true;
                } else {
                    System.out.println("Invalid amount. Please enter a positive numeric value:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid numeric value:");
            }
        }

        return amount;
    }

    private static LocalDate readValidDate(Scanner sc) {
        LocalDate date = null;
        boolean isValid = false;

        while (!isValid) {
            String input = sc.nextLine();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(input, formatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please enter a valid date in the format (dd/MM/yyyy):");
            }
        }

        return date;
    }

    public static void deleteData(IncomeRepository incomeRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID to delete:");
        int id = sc.nextInt();

        incomeRepository.delete(id);
        System.out.println("Income with ID " + id + " deleted successfully.");
    }

    public static void updateData(IncomeRepository incomeRepository) {
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

    public static void findByIdData(IncomeRepository incomeRepository) {
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

    public static void findAll(IncomeRepository incomeRepository) {
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
