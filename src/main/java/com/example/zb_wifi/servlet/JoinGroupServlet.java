package com.example.zb_wifi.servlet;

import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.dto.AddBookmarkGroupDto;
import com.example.zb_wifi.entity.JoinGroup;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/bookmark-add-submit")
public class JoinGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        JoinGroup joinGroup = gson.fromJson(request.getReader(), JoinGroup.class);
        joinGroup.setJoinGroupDate(LocalDateTime.now().toString());
        DataBaseService.joinGroup(joinGroup);
        response.setStatus(200);
    }
}
