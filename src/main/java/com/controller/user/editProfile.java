package com.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            // User is logged in, proceed with editing profile
            // Fetch user data from session or database
            // For demonstration, let's assume we have a user object in the session
            Object user = session.getAttribute("member");
            if (user != null) {
                // Set user data as request attribute to display in the JSP
                req.setAttribute("user", user);
            } else {
                resp.sendRedirect("login");
            }
            req.getRequestDispatcher("/views/user/editProfile.jsp").forward(req, resp);
        } else {
            // User is not logged in, redirect to login page
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
