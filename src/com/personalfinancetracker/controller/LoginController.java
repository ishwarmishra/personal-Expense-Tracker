package com.personalfinancetracker.controller;

import java.util.Scanner;

public class LoginController {
    private final String username = "ishwar";
    private final String password = "ishwar";

    public boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Login -----");
        System.out.print("Username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            System.out.println("Authentication successful.");
            return true;
        }

        System.out.println("Authentication failed. Invalid username or password.");
        return false;
    }
}
