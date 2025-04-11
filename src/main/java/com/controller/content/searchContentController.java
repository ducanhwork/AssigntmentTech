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
import java.util.List;

@WebServlet(name = "searchContentController", urlPatterns = {"/searchContent"})
public class searchContentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ContentService contentService = new ContentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword != null && !keyword.isEmpty()) {
            // Assuming you have a ContentService to handle the search logic
             List<Content> results = contentService.findByTitle(keyword);
             req.setAttribute("results", results);
            if(results == null || results.isEmpty()) {
                req.getRequestDispatcher("/views/content/searchContent.jsp").forward(req, resp);
                return;
            }
            int recordsPerPage = 3;
            int totalRecords = results.size();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            String pageStr = req.getParameter("page");
            int currentPage = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
            if (currentPage < 1) currentPage = 1;
            if (currentPage > totalPages) currentPage = totalPages;

            int start = (currentPage - 1) * recordsPerPage;
            int end = Math.min(start + recordsPerPage, totalRecords);

            List<Content> contentList = results.subList(start, end);
            System.out.println(contentList);
            // Đưa danh sách nội dung vào req để hiển thị trên trang JSP
            req.setAttribute("contentList", contentList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("recordsPerPage", recordsPerPage);

            req.getRequestDispatcher("/views/content/searchContent.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("viewContent");
        }
    }
}
