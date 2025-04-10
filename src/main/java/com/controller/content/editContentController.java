package com.controller.content;

import com.entities.Content;
import com.services.ContentService;
import com.services.ContentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "editContentController", urlPatterns = "/editContent")
public class editContentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ContentService contentService = new ContentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String error = req.getParameter("error");
        String success = req.getParameter("success");
        req.setAttribute("contentId", id);
         Content content = contentService.findById(Long.parseLong(id));
        System.out.println("Content ID: " + id);
        System.out.println("Content: " + content.getTitle());
        req.setAttribute("content", content);
        if (error != null) {
            req.setAttribute("error", error);
        }
        if (success != null) {
            req.setAttribute("success", success);
        }
        req.getRequestDispatcher("/views/content/editContent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String brief = req.getParameter("brief");
        String content = req.getParameter("content");

        boolean isUpdated = contentService.updateContent(Long.parseLong(id), title, brief, content);
        if (isUpdated) {
            resp.sendRedirect("editContent?id=" + id+"&success=Successfully to update content");
        } else {
            resp.sendRedirect("editContent?id=" + id+"&error=Failed to update content");
        }
    }
}
