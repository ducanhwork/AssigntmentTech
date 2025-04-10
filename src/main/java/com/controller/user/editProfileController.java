package com.controller.user;

import com.entities.Member;
import com.services.MemberService;
import com.services.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            // User is logged in, proceed with editing profile
            // Fetch user data from session or database
            // For demonstration, let's assume we have a user object in the session
            Object user = session.getAttribute("member");
            if (user != null) {
                String error = req.getParameter("error");
                String success = req.getParameter("success");
                if (error != null) {
                    req.setAttribute("error", error);
                }
                if (success != null) {
                    req.setAttribute("success", success);
                }
                // Set user data as request attribute to display in the JSP
                req.setAttribute("user", (Member) user);
                req.getRequestDispatcher("/views/user/editProfile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("login");
            }
        } else {
            // User is not logged in, redirect to login page
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // User is logged in, proceed with updating profile
        Object user = session.getAttribute("member");
        user = (Member) user;
        // Get updated user data from request
        String id = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String description = req.getParameter("description");
        ((Member) user).setFirstname(firstName);
        ((Member) user).setLastname(lastName);
        ((Member) user).setEmail(email);
        ((Member) user).setPhone(phone);
        ((Member) user).setDescription(description);
        // Update user data in the database
        boolean isUpdated = memberService.updateMember(Long.parseLong(id), firstName, lastName, phone, description);
        if (!isUpdated) {
            resp.sendRedirect(req.getContextPath() + "/editProfile?error=Failed to update profile");
            return;
        } else {
            session.setAttribute("member", user);
            resp.sendRedirect(req.getContextPath() + "/editProfile?success=Profile updated successfully");
        }

    }
}
