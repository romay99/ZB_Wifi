package com.example.zb_wifi.servlet;

import com.example.zb_wifi.service.ApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifi")
public class ApiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApiService apiService = new ApiService();
        int result = apiService.init();
        request.setAttribute("count", result);
        request.getRequestDispatcher("/load-wifi.jsp").forward(request, response);
    }
}
