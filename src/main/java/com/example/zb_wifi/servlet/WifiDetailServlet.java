package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.entity.BookmarkGroup;
import com.example.zb_wifi.entity.WiFi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/detail")
public class WifiDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("mgrNo");
        WiFi result = DataBaseService.findWifiByManagementNo(number);
        List<BookmarkGroup> bookmarkGroupList = DataBaseService.getBookmarkGroupList();

        request.setAttribute("wifi",result);
        request.setAttribute("groupList", bookmarkGroupList);
        request.getRequestDispatcher("wifi-detail.jsp").forward(request, response);
    }
}
