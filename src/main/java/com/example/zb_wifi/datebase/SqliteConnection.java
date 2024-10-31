package com.example.zb_wifi.datebase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DB 연결을 위한 클래스
 */
public class SqliteConnection {
    private static Connection connect = null;

    /**
     * DB 연결 얻기
     * @return 오류나지 않았다면 Connection 객체 리턴
     */
    public static Connection getConnection() {
        if (connect == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connect = DriverManager.getConnection("jdbc:sqlite:wifi.db");
                System.out.println("DB Connection Established");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
