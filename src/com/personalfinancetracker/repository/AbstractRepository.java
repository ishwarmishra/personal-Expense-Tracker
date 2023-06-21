package com.personalfinancetracker.repository;

import com.personalfinancetracker.DataRepository;
import com.personalfinancetracker.model.AbstractEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractRepository<T extends AbstractEntity> implements IRepository<T> {

    protected List<T> dataList;
    protected int nextId;

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        String URL = "jdbc:mysql://localhost:3306/personalFinanceTracker";
        String USERNAME = "root";
        String PASSWORD = "new_password";

        try {
            con = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DataRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public AbstractRepository() {
        dataList = new ArrayList<>();
        nextId = 1;
    }

    @Override
    public T add(T data) {
        data.setId(nextId);
        nextId++;
        dataList.add(data);
        return data;
    }

    @Override
    public void delete(int id) {
        T itemToRemove = findById(id);
        if (itemToRemove != null) {
            dataList.remove(itemToRemove);
            System.out.println("Item with ID " + id + " deleted.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    @Override
    public T update(T data) {
        T itemToUpdate = getItemById(data.getId());
        if (itemToUpdate != null) {
            int index = dataList.indexOf(itemToUpdate);
            dataList.set(index, data);
            System.out.println("Item with ID " + data.getId() + " updated.");
        } else {
            System.out.println("Item with ID " + data.getId() + " not found.");
        }
        return data;
    }

    @Override
    public T findById(int id) {
        for (T data : dataList) {
            if (data.getId() == id) {
                return data;
            }
        }

        return null;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataList);
    }

    private T getItemById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
