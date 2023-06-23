package com.personalfinancetracker.repository;

import com.personalfinancetracker.model.IncomeEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncomeRepository<T> extends AbstractRepository<IncomeEntity> {

    public IncomeRepository() {
        super();
    }

    @Override
    public IncomeEntity add(IncomeEntity incomeEntity) {
        String query = "INSERT INTO income (id,name, amount, incomedate) VALUES (?, ?, ?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, incomeEntity.getId());

            preparedStatement.setString(2, incomeEntity.getIncome());
            preparedStatement.setBigDecimal(3, incomeEntity.getAmount());
            preparedStatement.setDate(4, Date.valueOf(incomeEntity.getDate()));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                incomeEntity.setId(id);
                return incomeEntity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM income WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IncomeEntity update(IncomeEntity incomeEntity) {
        String query = "UPDATE income SET name = ?, amount = ?, incomedate = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, incomeEntity.getIncome());
            preparedStatement.setBigDecimal(2, incomeEntity.getAmount());
            preparedStatement.setDate(3, Date.valueOf(incomeEntity.getDate()));
            preparedStatement.setInt(4, incomeEntity.getId());
            preparedStatement.executeUpdate();
            return incomeEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IncomeEntity findById(int id) {
        String query = "SELECT * FROM income WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
                String name = resultSet.getString("name");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("incomedate");
                return new IncomeEntity(id, name, amount, date.toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IncomeEntity> findAll() {
        List<IncomeEntity> incomeEntities = new ArrayList<>();
        String query = "SELECT * FROM income";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("incomedate");
                incomeEntities.add(new IncomeEntity(id, name, amount, date.toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeEntities;
    }
}
