package com.controller.content;

import com.services.ContentService;
import com.services.ContentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "deleteContentController", value = "/deleteContent")
public class deleteContentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ContentService contentService = new ContentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean isDeleted = false;
        isDeleted = contentService.deleteContent(Long.parseLong(id));
        if (isDeleted) {
            req.setAttribute("success", "Content deleted successfully");
            req.getRequestDispatcher("/viewContent").forward(req, resp);
        } else {
            req.setAttribute("error", "Failed to delete content");
            req.getRequestDispatcher("/viewContent").forward(req, resp);
        }
    }
}
