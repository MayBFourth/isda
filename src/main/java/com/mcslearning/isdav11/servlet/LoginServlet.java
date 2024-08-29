package com.mcslearning.isdav11.servlet;

import com.mcslearning.isdav11.dao.CustomerDAO;
import com.mcslearning.isdav11.model.Customer;
import com.mcslearning.isdav11.util.DatabaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseUtil.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.getCustomerByUsername(username);

            if (customer != null && customer.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);

                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while logging in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
