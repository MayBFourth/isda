package com.mcslearning.isdav11.servlet;

import com.mcslearning.isdav11.dao.CustomerDAO;
import com.mcslearning.isdav11.model.Customer;
import com.mcslearning.isdav11.util.DatabaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ResetPasswordServlet.class.getName());
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String confirmPass = request.getParameter("confirmPass");
        String newPass = request.getParameter("newPass");

        if (!validatePassword(newPass)) {
            request.setAttribute("error", "Password must be at least 8 characters long, with at least one number, one uppercase letter, and one special character.");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }
        try(Connection connection = DatabaseUtil.getConnection()){
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.getCustomerByEmail(email);
            if(customer != null && confirmPass.equals(newPass)){
                customerDAO.updateCustomerPassword(customer.getId(), newPass);
                request.setAttribute("success", "Password changed successfully!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                request.setAttribute("error", "Invalid email.");
            }
        }
    }

    private boolean validatePassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[!@#$&*].*");
    }
}
