package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.dto.BookmarkListResponseDTO;
import com.example.zb_wifi.entity.JoinGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookmarkList")
public class BookmarkListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookmarkListResponseDTO> joinGroupList = DataBaseService.getJoinGroupList();
        request.setAttribute("joinGroupList", joinGroupList);
        request.getRequestDispatcher("bookmark-list.jsp").forward(request, response);

    }
}
