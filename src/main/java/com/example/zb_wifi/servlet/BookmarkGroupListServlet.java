package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.entity.BookmarkGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/groupList")
public class BookmarkGroupListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookmarkGroup> bookmarkGroupList = DataBaseService.getBookmarkGroupList();
        request.setAttribute("bookmarkGroupList", bookmarkGroupList);
        request.getRequestDispatcher("bookmark-group.jsp").forward(request, response);
    }
}
