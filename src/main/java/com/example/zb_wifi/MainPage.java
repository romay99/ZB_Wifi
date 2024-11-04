package com.example.zb_wifi;

import com.example.zb_wifi.datebase.DataBaseService;

import java.io.IOException;

public class MainPage   {
    public static void main(String[] args) throws IOException {
//        ApiService apiService = new ApiService();
//        System.out.println("저장된 데이터 갯수 = " + apiService.init());
        DataBaseService.clearWifiTable();
    }

}