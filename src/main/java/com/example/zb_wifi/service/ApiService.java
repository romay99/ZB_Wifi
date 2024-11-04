package com.example.zb_wifi.service;

import com.example.zb_wifi.datebase.DataBaseService;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ApiService {
    String apiKey = "5677466d68726f6d393772486b4776";
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    public int init() throws IOException {
        DataBaseService.createTable(); // DB 테이블 생성
        int startIdx = 1; // 시작 데이터 번호
        int maxServerDataCount = 1; // 마지막 데이터 번호
        String initUrl = "http://openapi.seoul.go.kr:8088/"+ apiKey +"/json/TbPublicWifiInfo/"+1+"/"+2+"/";

        Request request = new Request.Builder()
                .url(initUrl)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(body.string()).getAsJsonObject();
                maxServerDataCount = jsonObject.getAsJsonObject("TbPublicWifiInfo").get("list_total_count").getAsInt();
            }
        }
        //첫 요청으로 몇개의 데이터가 있는지 알아내고 , 그 만큼 while 문으로 요청을 계속 보낸다. (1000개 단위로)
        int dbDataCount = DataBaseService.countDbData();
        int tmpEndIdx = 1000;
        if (dbDataCount != maxServerDataCount) { // DB 내부에 데이터갯수와 서버의 데이터 갯수가 다를때만 실행
            DataBaseService.clearWifiTable(); // WIFI 테이블을 한번 초기화 시켜준다.
            System.out.println("maxServerDataCount = " + maxServerDataCount);

            while (startIdx < maxServerDataCount) {
                String getUrl = "http://openapi.seoul.go.kr:8088/"+ apiKey +"/json/TbPublicWifiInfo/"+startIdx+"/"+ tmpEndIdx +"/";

                request = new Request.Builder()
                        .url(getUrl)
                        .get()
                        .build();
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    DataBaseService.saveWiFiDataToDB(body.string());
                }
                if (tmpEndIdx + 1000 < maxServerDataCount) {
                    tmpEndIdx += 1000;
                    startIdx += 1000;
                } else if (tmpEndIdx + 1000 >= maxServerDataCount) {
                    System.out.println("마지막 요청입니다.");
                    tmpEndIdx = maxServerDataCount;
                    startIdx += 1000;
                }
            }
        }
        return maxServerDataCount;
    }
}