package com.controller.content;

import com.entities.Content;
import com.services.ContentService;
import com.services.ContentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

@WebServlet(name = "viewContentDetailController", urlPatterns = {"/viewContentDetail"})
public class viewContentDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ContentService contentService = new ContentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String contentId = req.getParameter("id");
        Content content = contentService.findById(Long.valueOf(contentId));
        Gson gson = new Gson();
        //localdate time
        String json = "{";
        Field field[] = Content.class.getDeclaredFields();
        for (Field f : field) {
            f.setAccessible(true);
            if (f.getType().getName().equals("java.time.LocalDateTime")) {
                LocalDateTime localDateTime = null;
                try {
                    localDateTime = (LocalDateTime) f.get(content);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                String localDateTimeString = localDateTime.toString();
                json = json + "\"" + f.getName() + "\":\"" + localDateTimeString + "\",";
            } else {
                try {
                    json = json + "\"" + f.getName() + "\":\"" + f.get(content) + "\",";
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        json = json.substring(0, json.length() - 1);
        json += "}";
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
