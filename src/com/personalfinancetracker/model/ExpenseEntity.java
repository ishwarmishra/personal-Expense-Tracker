package com.personalfinancetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseEntity extends AbstractEntity {
    private String expense;
    private BigDecimal amount;
    private LocalDate date;

    public ExpenseEntity(String expense, BigDecimal amount, LocalDate date) {
        this.expense = expense;
        this.amount = amount;
        this.date = date;
    }
    

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    }
