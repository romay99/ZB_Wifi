package com.example.zb_wifi.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB 연결을 위한 클래스
 */
public class SqliteConnection {
    private static Connection connect = null;

    public static void closeConnection() {
        if (connect != null) {
            try{
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection() {
        if (connect == null) {
            try {
                Class.forName("org.sqlite.jdbc3");
                connect = DriverManager.getConnection("jdbc:sqlite:" + "SQLiteDb.db");
                System.out.println("DB 연결 서공");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
