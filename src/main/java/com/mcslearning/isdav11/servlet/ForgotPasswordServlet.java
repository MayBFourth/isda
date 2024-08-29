package com.mcslearning.isdav11.servlet;

import com.mcslearning.isdav11.dao.CustomerDAO;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import com.mcslearning.isdav11.util.DatabaseUtil;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String USERNAME = "ducnmpad@gmail.com";
    private static final String PASSWORD = "hbuxbkrxzqamqxya";    // Mật khẩu email gửi

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        try (Connection connection = DatabaseUtil.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            if (customerDAO.getCustomerByEmail(email) != null) {
                sendResetEmail(email);
                request.setAttribute("success", "Password reset link sent to your email.");
            } else {
                request.setAttribute("error", "Email not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendResetEmail (String email) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please visit the following link:\n"
                + "http://localhost:8080/ISDAv1_1_war_exploded/resetPassword.jsp?email=" + email);

        Transport.send(message);
    }
}
