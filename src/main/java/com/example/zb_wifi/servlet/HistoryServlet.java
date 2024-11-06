package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.entity.History;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history/*")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            List<History> historyList = DataBaseService.getHistoryList();

            request.setAttribute("historyList", historyList);
            request.getRequestDispatcher("history.jsp").forward(request, response);
        } else { // 히스토리 삭제
            String[] historyId = pathInfo.split("/");
            int id = Integer.parseInt(historyId[1]);
            DataBaseService.deleteHistoryById(id);

            response.sendRedirect("/history");
        }
    }
}
