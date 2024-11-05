package com.example.zb_wifi.datebase;

import com.example.zb_wifi.dto.AddBookmarkGroupDto;
import com.example.zb_wifi.entity.BookmarkGroup;
import com.example.zb_wifi.entity.History;
import com.example.zb_wifi.entity.WiFi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {
    private static final String dataBaseUrl = "jdbc:sqlite:C:\\WorkSpace\\ZB_WiFi\\wifi.db";
    static private Connection connection = null;

    static {
        getConnection();
    }

    static private void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dataBaseUrl);
            System.out.println("DB연결 완료");
            System.out.println("connection = " + connection.getMetaData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DB에 테이블 생성하는 메서드.(테이블이 존재하지 않을때만 실행됨)
     */
    static public void createTable() {
        //wifi 테이블 생성 (존재하지 않을때만)
        String createWifiTable = "create table if not exists wifi(" +
                "wifi_id integer primary key autoincrement, " +
                "wifi_management_number text, " +
                "wifi_borough text, " +
                "wifi_name text, " +
                "wifi_street_name text, " +
                "wifi_detail_address text, " +
                "wifi_floor text, " +
                "wifi_category text, " +
                "wifi_organization text, " +
                "wifi_service_category text, " +
                "wifi_wire_category text, " +
                "wifi_install_year integer, " +
                "wifi_inside_outside text, " +
                "wifi_connect_environment text, " +
                "wifi_location_x real, " +
                "wifi_location_y real, " +
                "wifi_work_datetime text" +
                ");";

        String createHistoryTable = "create table if not exists history (" +
                "history_id integer primary key autoincrement, " +
                "history_location_x real, " +
                "history_location_y real, " +
                "history_date text" +
                ");";

        String createBookmarkGroupTable = "create table if not exists bookmark_group (" +
                "group_id integer primary key autoincrement, " +
                "group_name text, " +
                "group_make_date text, " +
                "group_modify_date text, " +
                "group_priority integer" +
                ");";

        String createJoinGroupTable = "create table if not exists join_group (" +
                "join_group_id integer primary key autoincrement, " +
                "wifi_id integer, " +
                "group_id integer, " +
                "foreign key (wifi_id) references wifi(wifi_id), " +
                "foreign key (group_id) references bookmark_group(group_id)" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);

            stmt.execute("PRAGMA foreign_keys = ON"); // 외래키 기능 ON
            stmt.execute(createWifiTable);
            stmt.execute(createHistoryTable);
            stmt.execute(createBookmarkGroupTable);
            stmt.execute(createJoinGroupTable);
            connection.commit();
            System.out.println("테이블 생성 완료");
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * DB 내부 WIFI 데이터 갯수 카운트하는 메서드
     *
     * @return WIFI 데이터 갯수 리턴
     */
    static public int countDbData() {
        int result = -1;
        String countSql = "select count(*) from wifi";

        try (PreparedStatement stmt = connection.prepareStatement(countSql)) {
            ResultSet rs = stmt.executeQuery();
            result = rs.getInt(1);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * API 를 이용해 데이터를 DB 에 저장하는 메서드
     */
    static public void saveWiFiDataToDB(String json) {
        Gson gson = new Gson();
        try {
            // JSON 문자열을 JsonObject로 변환
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            // "row" 배열을 가져오기
            JsonArray wifiArray = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
            // WiFi 객체 리스트로 변환
            List<WiFi> wifiList = gson.fromJson(wifiArray, new TypeToken<List<WiFi>>() {
            }.getType());

            String insertQuery = "insert into wifi (wifi_id,wifi_management_number, wifi_borough, wifi_name, wifi_street_name, wifi_detail_address, "
                    + "wifi_floor, wifi_category, wifi_organization, wifi_service_category, wifi_wire_category, "
                    + "wifi_install_year, wifi_inside_outside, wifi_connect_environment, wifi_location_x, wifi_location_y, wifi_work_datetime) "
                    + "values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
                for (WiFi wifi : wifiList) {
                    stmt.setString(2, wifi.getWifiManagementNumber());
                    stmt.setString(3, wifi.getWifiBorough());
                    stmt.setString(4, wifi.getWifiName());
                    stmt.setString(5, wifi.getWifiStreetName());
                    stmt.setString(6, wifi.getWifiDetailAddress());
                    stmt.setString(7, wifi.getWifiFloor());
                    stmt.setString(8, wifi.getWifiCategory());
                    stmt.setString(9, wifi.getWifiOrganization());
                    stmt.setString(10, wifi.getWifiServiceCategory());
                    stmt.setString(11, wifi.getWifiWireCategory());
                    if (wifi.getWifiInstallYear() != null) {
                        stmt.setInt(12, wifi.getWifiInstallYear());
                    } else {
                        stmt.setNull(12, Types.INTEGER);
                    }
                    stmt.setString(13, wifi.getWifiInsideOutside());
                    stmt.setString(14, wifi.getWifiConnectEnvironment());
                    if (wifi.getWifiLocationX() != null) {
                        stmt.setDouble(15, wifi.getWifiLocationX());
                    } else {
                        stmt.setNull(15, Types.DOUBLE);
                    }
                    if (wifi.getWifiLocationY() != null) {
                        stmt.setDouble(16, wifi.getWifiLocationY());
                    } else {
                        stmt.setNull(16, Types.DOUBLE);
                    }
                    stmt.setString(17, wifi.getWifiWorkDateTime());

                    stmt.addBatch();
                }
                stmt.executeBatch();

                System.out.println("데이터 저장 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Wifi 테이블 초기화 시키는 메서드
     */
    static public void clearWifiTable() {
        // 테이블 초기화
        String clearTableSQL = "delete from wifi";
        try (PreparedStatement stmt = connection.prepareStatement(clearTableSQL)) {
            stmt.executeUpdate();
            System.out.println("테이블 클리어 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 사용자 위치 기반으로 가까운 20개의 wifi 반환해주는 메서드
     *
     * @param lat 사용자 위도
     * @param lnt 사용자 경도
     * @return List<Wifi> 반환함
     */
    static public List<WiFi> getNearWifi(double lat, double lnt) {
        String sql = "select * from wifi";
        List<WiFi> wifiList = new ArrayList<>();
        List<WiFi> nearWifiList = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                WiFi wifi = new WiFi();
                wifi.setWifiId(rs.getInt("wifi_id"));
                wifi.setWifiManagementNumber(rs.getString("wifi_management_number"));
                wifi.setWifiBorough(rs.getString("wifi_borough"));
                wifi.setWifiName(rs.getString("wifi_name"));
                wifi.setWifiStreetName(rs.getString("wifi_street_name"));
                wifi.setWifiDetailAddress(rs.getString("wifi_detail_address"));
                wifi.setWifiFloor(rs.getString("wifi_floor"));
                wifi.setWifiCategory(rs.getString("wifi_category"));
                wifi.setWifiOrganization(rs.getString("wifi_organization"));
                wifi.setWifiServiceCategory(rs.getString("wifi_service_category"));
                wifi.setWifiWireCategory(rs.getString("wifi_wire_category"));
                wifi.setWifiInstallYear(rs.getInt("wifi_install_year"));
                wifi.setWifiInsideOutside(rs.getString("wifi_inside_outside"));
                wifi.setWifiConnectEnvironment(rs.getString("wifi_connect_environment"));
                wifi.setWifiLocationX(rs.getDouble("wifi_location_x"));
                wifi.setWifiLocationY(rs.getDouble("wifi_location_y"));
                wifi.setWifiWorkDateTime(rs.getString("wifi_work_datetime"));
                wifi.setDistance(round(getDistance(wifi.getWifiLocationX(), wifi.getWifiLocationY(), lat, lnt), 4).doubleValue());

                wifiList.add(wifi);
            }

            wifiList.sort((a, b) -> Double.compare(a.getDistance(), b.getDistance()));

            for (int i = 0; i < 20; i++) {
                nearWifiList.add(wifiList.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return nearWifiList;
    }

    /**
     * wifi의 distance 를 계산할때 반올림하는 메서드
     *
     * @param value  distance 값
     * @param places 반올림해서 표시해줄 자릿수
     * @return 반올림한 실수값
     */
    public static BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(places, RoundingMode.HALF_UP); // 소수점 이하 places 자리로 반올림
    }

    /**
     * 사용자 위치 기반으로 거리 구하는 메서드
     *
     * @return km 단위의 거리 반환
     */
    static private double getDistance(double lat1, double lnt1, double lat2, double lnt2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lntDistance = Math.toRadians(lnt2 - lnt1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lntDistance / 2) * Math.sin(lntDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // 킬로미터 단위 반환
    }

    /**
     * 관리번호로 WIFI 찾기
     * @param managementNo 관리번호
     * @return wifi class 리턴
     */
    static public WiFi findWifiByManagementNo(String managementNo) {
        String findSQL = "select * from wifi where wifi_management_number = ?";
        WiFi wifi = new WiFi();

        try (PreparedStatement stmt = connection.prepareStatement(findSQL)){
            stmt.setString(1, managementNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                wifi.setWifiId(rs.getInt("wifi_id"));
                wifi.setWifiManagementNumber(rs.getString("wifi_management_number"));
                wifi.setWifiBorough(rs.getString("wifi_borough"));
                wifi.setWifiName(rs.getString("wifi_name"));
                wifi.setWifiStreetName(rs.getString("wifi_street_name"));
                wifi.setWifiDetailAddress(rs.getString("wifi_detail_address"));
                wifi.setWifiFloor(rs.getString("wifi_floor"));
                wifi.setWifiCategory(rs.getString("wifi_category"));
                wifi.setWifiOrganization(rs.getString("wifi_organization"));
                wifi.setWifiServiceCategory(rs.getString("wifi_service_category"));
                wifi.setWifiWireCategory(rs.getString("wifi_wire_category"));
                wifi.setWifiInstallYear(rs.getInt("wifi_install_year"));
                wifi.setWifiInsideOutside(rs.getString("wifi_inside_outside"));
                wifi.setWifiConnectEnvironment(rs.getString("wifi_connect_environment"));
                wifi.setWifiLocationX(rs.getDouble("wifi_location_x"));
                wifi.setWifiLocationY(rs.getDouble("wifi_location_y"));
                wifi.setWifiWorkDateTime(rs.getString("wifi_work_datetime"));
            }
            addHistory(wifi.getWifiLocationX(), wifi.getWifiLocationY()); // 히스토리 추가
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wifi;
    }

    /**
     * 히스토리 추가하는 메서드
     * @param x WIFI X 좌표
     * @param y WIFI Y 좌표
     */
    static void addHistory(double x, double y) {
        String insertHistorySQL = "insert into history(history_location_x,history_location_y,history_date) values(?,?,?)";
        History history = new History();
        history.setHistoryDate(LocalDateTime.now().toString());
        history.setHistoryLocationX(x);
        history.setHistoryLocationY(y);
        try (PreparedStatement stmt = connection.prepareStatement(insertHistorySQL)){
            stmt.setDouble(1, history.getHistoryLocationX());
            stmt.setDouble(2, history.getHistoryLocationY());
            stmt.setString(3, history.getHistoryDate());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 히스토리 리스트 리턴해주는 메서드
     * @return List<history>
     */
    static public List<History> getHistoryList() {
        String sql = "select * from history";
        List<History> historyList = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                History history = new History();
                history.setHistoryId(rs.getInt("history_id"));
                history.setHistoryDate(rs.getString("history_date"));
                history.setHistoryLocationX(rs.getDouble("history_location_x"));
                history.setHistoryLocationY(rs.getDouble("history_location_y"));

                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return historyList;
    }

    /**
     * 히스토리 삭제하는 메서드
     * @param id History Id
     */
    static public void deleteHistoryById(int id) {
        String sql = "delete from history where history_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * BookmarkGroup List 받는 메서드
     * @return BookmarkGroup List
     */
    static public List<BookmarkGroup> getBookmarkGroupList() {
        List<BookmarkGroup> list = new ArrayList<>();
        String sql = "select * from bookmark_group order by group_priority asc";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setGroupId(rs.getInt(1));
                bookmarkGroup.setGroupName(rs.getString(2));
                bookmarkGroup.setGroupPriority(rs.getInt(3));
                bookmarkGroup.setGroupMakeDate(rs.getString(4));
                bookmarkGroup.setGroupModifyDate(rs.getString(5));

                list.add(bookmarkGroup);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    /**
     * BookmarkGroup 추가하는 메서드
     * @param dto 이름 , 그룹순서를 가지고있는 dto
     */
    static public void addBookmarkGroup(AddBookmarkGroupDto dto) {
        String insertSql = "insert into bookmark_group(group_name,group_make_date,group_priority) values(?,?,?)";
        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        bookmarkGroup.setGroupName(dto.getGroupName());
        bookmarkGroup.setGroupMakeDate(LocalDateTime.now().toString());
        bookmarkGroup.setGroupPriority(dto.getGroupPriority());
        try {
            PreparedStatement stmt = connection.prepareStatement(insertSql);
            stmt.setString(1, dto.getGroupName());
            stmt.setString(2, bookmarkGroup.getGroupMakeDate());
            stmt.setInt(3, bookmarkGroup.getGroupPriority());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
