package com.mcslearning.isdav11.servlet;

import com.mcslearning.isdav11.dao.CustomerDAO;
import com.mcslearning.isdav11.model.Customer;
import com.mcslearning.isdav11.util.DatabaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        if (!validatePassword(newPassword)) {
            request.setAttribute("error", "Password must be at least 8 characters long, with at least one number, one uppercase letter, and one special character.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }
        System.out.println(username);

        try (Connection connection = DatabaseUtil.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.getCustomerByUsername(username);

            if (customer != null && customer.getPassword().equals(currentPassword)) {
                customerDAO.updateCustomerPassword(customer.getId(), newPassword);
                request.setAttribute("success", "Password changed successfully!");
            } else {
                request.setAttribute("error", "Invalid current password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while changing the password.");
        }

        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    private boolean validatePassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[!@#$&*].*");
    }
}
