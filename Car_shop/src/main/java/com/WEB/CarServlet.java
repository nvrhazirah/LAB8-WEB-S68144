/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.WEB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.DAO.CarDAO;
import com.Model.Car;

@WebServlet("/")
public class CarServlet extends HttpServlet {

    private CarDAO carDAO;

    public void init() {
        carDAO = new CarDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {

                case "/new":
                    showNewForm(request, response);
                    break;

                case "/insert":
                    insertCar(request, response);
                    break;

                case "/delete":
                    deleteCar(request, response);
                    break;

                case "/edit":
                    showEditForm(request, response);
                    break;

                case "/update":
                    updateCar(request, response);
                    break;

                default:
                    listCar(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Car> listCar = carDAO.selectAllCars();
        request.setAttribute("listCar", listCar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int carid = Integer.parseInt(request.getParameter("carid"));
        Car existingCar = carDAO.selectCar(carid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
        request.setAttribute("car", existingCar);
        dispatcher.forward(request, response);

    }

    private void insertCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int cyclinder = Integer.parseInt(request.getParameter("cyclinder"));
        double price = Double.parseDouble(request.getParameter("price"));

        Car newCar = new Car(brand, model, cyclinder, price);
        carDAO.insertCar(newCar);
        response.sendRedirect("list");
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int carid = Integer.parseInt(request.getParameter("carid"));
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int cyclinder = Integer.parseInt(request.getParameter("cyclinder"));
        double price = Double.parseDouble(request.getParameter("price"));

        Car uCar = new Car(carid, brand, model, cyclinder, price);
        carDAO.updateCar(uCar);
        response.sendRedirect("list");
    }
    
    private void deleteCar (HttpServletRequest request, HttpServletResponse response)
            throws SQLException,IOException{
        int carid = Integer.parseInt(request.getParameter("carid"));
        carDAO.deleteCar(carid);
        response.sendRedirect("list");
    }

}
