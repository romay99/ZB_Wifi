package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.entity.BookmarkGroup;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookmark-group-edit")
public class BookmarkGroupEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BookmarkGroup bookmarkGroup = DataBaseService.findBookmarkGroupById(id);
        request.setAttribute("bookmarkGroup", bookmarkGroup);
        request.getRequestDispatcher("/bookmark-group-edit.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("이거실행 = ");
        Gson gson = new Gson();
        BookmarkGroup bookmarkGroup = gson.fromJson(request.getReader(), BookmarkGroup.class);
        DataBaseService.editBookmarkGroup(bookmarkGroup);
        response.setStatus(200);
    }
}
