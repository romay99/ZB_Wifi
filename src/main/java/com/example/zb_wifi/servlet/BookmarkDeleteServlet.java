package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.dto.BookmarkListResponseDTO;
import com.example.zb_wifi.entity.BookmarkGroup;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookmark-delete")
public class BookmarkDeleteServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        BookmarkListResponseDTO dto = gson.fromJson(request.getReader(), BookmarkListResponseDTO.class);
        int id = dto.getJoinGroupId();
        DataBaseService.deleteBookmarkById(id);
        response.setStatus(200);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BookmarkListResponseDTO dto = DataBaseService.findBookmarkById(id);
        request.setAttribute("dto", dto);
        request.getRequestDispatcher("/bookmark-delete.jsp").forward(request, response);
    }
}
