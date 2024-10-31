package com.example.zb_wifi.service;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ApiService {
    Gson gson = new Gson();
    String apiKey = "5677466d68726f6d393772486b4776";
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    public int init() throws IOException {
        int startIdx = 1; // 시작 데이터 번호
        int maxIdx = 1; // 마지막 데이터 번호
        String url = "http://openapi.seoul.go.kr:8088/"+ apiKey +"/json/TbPublicWifiInfo/"+startIdx+"/"+maxIdx+"/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(body.string()).getAsJsonObject();
                maxIdx = jsonObject.getAsJsonObject("TbPublicWifiInfo").get("list_total_count").getAsInt();
            }
        }
        //첫 요청으로 몇개의 데이터가 있는지 알아내고 , 그 만큼 while 문으로 요청을 계속 보낸다. (1000개 단위로)
        while (startIdx < maxIdx) {
            //Todo start인덱스를 1000씩 더해가면서, start ~ end 의 데이터를 요청한다.

        }

        return maxIdx;
    }
}