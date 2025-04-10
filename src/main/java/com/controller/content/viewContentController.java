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

@WebServlet(name = "viewContent", urlPatterns = {"/viewContent"})
public class viewContentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ContentService contentService = new ContentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // Thiết lập mã hóa cho req và response
        // Simulate fetching data (replace with actual database logic)
        var listContents = contentService.findAll();
        if(listContents == null || listContents.isEmpty()) {
            req.getRequestDispatcher("/views/content/listContent.jsp").forward(req, resp);
            return;
        }
        int recordsPerPage = 3;
        int totalRecords = listContents.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        String pageStr = req.getParameter("page");
        int currentPage = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
        if (currentPage < 1) currentPage = 1;
        if (currentPage > totalPages) currentPage = totalPages;

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecords);

        List<Content> contentList = listContents.subList(start, end);
        System.out.println(contentList);
        // Đưa danh sách nội dung vào req để hiển thị trên trang JSP
        req.setAttribute("contentList", contentList);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("recordsPerPage", recordsPerPage);

        // Chuyển hướng đến trang viewContent.jsp
        req.getRequestDispatcher("/views/content/listContent.jsp").forward(req, resp);
    }
}
