/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

/**
 *
 * @author zira
 */

import java.sql.*;
import java.util.*;

import com.Model.Car;

public class CarDAO {
    
    private String jdbcURL="jdbc:mysql://localhost:3306/carshopp";
    private String jdbcUsername="root";
    private String jdbcPassword="admin";
    
    private static final String INSERT_CAR_SQL  ="INSERT INTO carpricelist (brand,model,cyclinder,price) values (?,?,?,?)";
    private static final String SELECT_CAR_BY_ID="SELECT carid,brand,model,cyclinder,price from carpricelist where carid = ?";
    private static final String SELECT_ALL_CARS ="SELECT * from carpricelist";
    private static final String DELETE_CAR_SQL  ="delete from carpricelist where carid =?;";
    private static final String UPDATE_CAR_SQL  ="update carpricelist set brand= ?,model= ?,cyclinder= ?,price= ? where carid = ?;";


    public CarDAO(){
    }
    
    protected Connection getConnection(){ //mathod connection
    
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    
    
    public Car selectCar(int carid ) {
        Car car = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID);) {
            preparedStatement.setInt(1, carid);
            
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int cyclinder = Integer.parseInt(rs.getString("cyclinder"));
                double price = Double.parseDouble(rs.getString("price"));
                car = new Car(carid, brand, model, cyclinder, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return car;
    }
    
    public List <Car> selectAllCars() {

        List < Car > cars = new ArrayList < > ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int carid = rs.getInt("carid");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int cyclinder = Integer.parseInt(rs.getString("cyclinder"));
                double price = Double.parseDouble(rs.getString("price"));
                cars.add(new Car(carid, brand, model, cyclinder, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cars;
    }
    
    public boolean deleteCar(int carid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_SQL);) {
            statement.setInt(1, carid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateCar(Car car) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); 
            PreparedStatement ps = connection.prepareStatement(UPDATE_CAR_SQL);) {
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt   (3, car.getCyclinder());
            ps.setDouble(4, car.getPrice());
            ps.setInt   (5, car.getCarid());

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public void insertCar(Car car){
        System.out.println(INSERT_CAR_SQL);
        
        try (Connection connection = getConnection();//connect database guna method getConnection 
                PreparedStatement ps = connection.prepareStatement(INSERT_CAR_SQL);) { //insert data SQL
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt   (3, car.getCyclinder());
            ps.setDouble(4, car.getPrice());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}


