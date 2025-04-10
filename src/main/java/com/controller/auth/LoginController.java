package com.controller.auth;

import com.entities.Member;
import com.services.MemberService;
import com.services.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;

@WebServlet("/login")
public class LoginController extends HttpServlet {
     private static final MemberService memberService = new MemberServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberMe");
        // Xử lý đăng nhập ở đây
        // Kiểm tra thông tin đăng nhập với cơ sở dữ liệu
        Member member = memberService.findByEmailAndPassword(email, password);
        if (member != null) {
            // Đăng nhập thành công

            if(remember != null) {
                Cookie rememberCookie = new Cookie("rememberMe", member.getEmail()+":"+ member.getPassword());
                rememberCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                rememberCookie.setHttpOnly(true);
                rememberCookie.setPath(req.getContextPath());
                resp.addCookie(rememberCookie);
            }
            req.getSession().setAttribute("member", member);
            resp.sendRedirect("viewContent");
        } else {
            // Đăng nhập thất bại
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/views/auth/login.jsp").forward(req, resp);
        }
    }
}
