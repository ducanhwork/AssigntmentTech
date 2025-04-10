package com.controller.content;

import com.entities.Member;
import com.services.ContentService;
import com.services.ContentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "addContent", urlPatterns = "/addContent")
public class addContentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ContentService contentService = new ContentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle GET requests for adding content
        // This could involve displaying a form for the user to fill out
        // For now, we can just forward to a JSP page or return a simple message
        req.getRequestDispatcher("/views/content/createContent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle POST requests for adding content
        // This could involve processing the form data submitted by the user
        // For now, we can just return a simple message or redirect to another page
        String title = req.getParameter("title");
        String brief = req.getParameter("brief");
        String content = req.getParameter("content");
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");
        Long authorId = ((Member) member).getId();

        // Here you would typically call a service to save the content to the database
        boolean isInserted = contentService.insertContent(title, brief, content, authorId);
        if (!isInserted) {
            // Handle the case where the content could not be inserted
            req.setAttribute("errorMessage", "Failed to add content. Please try again.");
            req.getRequestDispatcher("/views/content/createContent.jsp").forward(req, resp);
            return;
        }
        else{
            // Redirect to a success page or the content list page
            resp.sendRedirect("viewContent");
        }

    }
}
