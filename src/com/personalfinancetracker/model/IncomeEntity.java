package com.personalfinancetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeEntity extends AbstractEntity{
    private int id;
    private String income;
    private BigDecimal amount;
    private LocalDate date;

    public IncomeEntity(int id, String income, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.income = income;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
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
