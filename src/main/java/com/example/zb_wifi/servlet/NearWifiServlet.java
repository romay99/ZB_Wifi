package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.entity.WiFi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/near")
public class NearWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("lat").isEmpty() || request.getParameter("lnt").isEmpty()){
            response.sendRedirect("index.html");
        }
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lnt = Double.parseDouble(request.getParameter("lnt"));
        List<WiFi> nearWifiList = DataBaseService.getNearWifi(lat, lnt);
        request.setAttribute("list",nearWifiList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
