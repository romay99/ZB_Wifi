package com.example.zb_wifi;


import com.example.zb_wifi.datebase.DataBaseService;
import com.example.zb_wifi.datebase.SqliteConnection;
import com.example.zb_wifi.service.ApiService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainPage   {
    public static void main(String[] args) throws IOException {
        ApiService apiService = new ApiService();
        System.out.println("실행");
        DataBaseService db = new DataBaseService();

    }
}