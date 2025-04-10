package com.filters;

import com.entities.Member;
import com.services.MemberService;
import com.services.MemberServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class RouterFilter implements Filter {
    private static final MemberService memberService = new MemberServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Paths to exclude
        boolean isLoginOrRegister = url.equals(contextPath + "/login") || url.equals(contextPath + "/register");
        boolean isStaticResource = url.contains("/assets") || url.contains("/favicon.ico");

        if (isLoginOrRegister || isStaticResource) {
            filterChain.doFilter(request, response);
            return;
        }
        // Check if user is already in session
        if (request.getSession().getAttribute("member") == null) {
            // Try to get from cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("rememberMe".equals(cookie.getName())) {
                        String rememberedUser = cookie.getValue();
                        String[] userInfo = rememberedUser.split(":");
                        Member member = memberService.findByEmailAndPassword(userInfo[0], userInfo[1]);
                        if (member == null) {
                            // Invalid cookie, redirect to login
                            response.sendRedirect(contextPath + "/login");
                            return;
                        }
                        // You can add validation here if needed
                        request.getSession().setAttribute("member", rememberedUser);
                        break;
                    }
                }
            }
        }

        // After checking cookie, if still no user, redirect to login
        if (request.getSession().getAttribute("member") == null) {
            response.sendRedirect(contextPath + "/login");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
