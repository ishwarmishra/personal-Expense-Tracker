package com.personalfinancetracker.repository;

import com.personalfinancetracker.model.ExpenseEntity;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository extends AbstractRepository<ExpenseEntity> {

    public ExpenseRepository() {
        super();
    }
    
    
     @Override
    public ExpenseEntity add(ExpenseEntity expenseEntity) {
        String query = "INSERT INTO expense (name, amount, expensedate) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, expenseEntity.getExpense());
            preparedStatement.setBigDecimal(2, expenseEntity.getAmount());
            preparedStatement.setDate(3, Date.valueOf(expenseEntity.getDate()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM expense WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ExpenseEntity update(ExpenseEntity expenseEntity) {
        String query = "UPDATE expense SET name = ?, amount = ?, expensedate = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, expenseEntity.getExpense());
            preparedStatement.setBigDecimal(2, expenseEntity.getAmount());
            preparedStatement.setDate(3, Date.valueOf(expenseEntity.getDate()));
            preparedStatement.setInt(4, expenseEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExpenseEntity findById(int id) {
        String query = "SELECT * FROM expense WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("expensedate");
                return new ExpenseEntity(id, name, amount, date.toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ExpenseEntity> findAll() {
        List<ExpenseEntity> expenseEntities = new ArrayList<>();
        String query = "SELECT * FROM expense";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("expensedate");
                expenseEntities.add(new ExpenseEntity(id, name, amount, date.toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenseEntities;
    }

    

}
