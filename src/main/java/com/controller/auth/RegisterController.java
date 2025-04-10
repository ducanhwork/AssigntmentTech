package com.controller.auth;

import com.services.MemberService;
import com.services.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            boolean result = memberService.insertMember(username, email, password);
            // Kiểm tra kết quả đăng ký
            if (result) {
                // Đăng ký thành công -> chuyển hướng về trang đăng nhập
                req.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            } else {
                // Thất bại (ví dụ: trùng username/email) -> gửi lại form và hiển thị thông báo
                req.setAttribute("error", "Tên đăng nhập hoặc email đã tồn tại!");
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            }

        } catch (Exception e) {

            req.setAttribute("error", "Đã xảy ra lỗi trong quá trình đăng ký.");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }

}

