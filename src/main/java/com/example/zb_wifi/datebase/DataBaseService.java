package com.example.zb_wifi.datebase;

import java.sql.*;

public class DataBaseService {
    private static final String dataBaseUrl ="jdbc:sqlite:wifi.db";
    private Connection connection = null;

    public DataBaseService() {
        getConnection();
    }
    private void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dataBaseUrl);
            System.out.println("DB연결 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 테이블 생성
    public void createTable() {
        //wifi 테이블 생성 (존재하지 않을때만)
        String sql1 = "CREATE TABLE IF NOT EXISTS wifi (" +
                "wifi_id TEXT PRIMARY KEY, " +
                "wifi_borough TEXT, " +
                "wifi_name TEXT, " +
                "wifi_street_name TEXT, " +
                "wifi_detail_address TEXT, " +
                "wifi_floor INTEGER, " +
                "wifi_category TEXT, " +
                "wifi_organization TEXT, " +
                "wifi_service_category TEXT, " +
                "wifi_wire_category TEXT, " +
                "wifi_install_year INTEGER, " +
                "wifi_inside_outside TEXT, " +
                "wifi_connect_environment TEXT, " +
                "wifi_location_x REAL, " +
                "wifi_location_y REAL, " +
                "wifi_work_datetime TEXT" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql1);
            System.out.println("테이블 생성 완료");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
