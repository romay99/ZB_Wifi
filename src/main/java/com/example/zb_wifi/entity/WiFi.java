package com.example.zb_wifi.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class WiFi {
    private int wifiId;

    @SerializedName("X_SWIFI_MGR_NO")
    private String wifiManagementNumber;

    @SerializedName("X_SWIFI_WRDOFC")
    private String wifiBorough;

    @SerializedName("X_SWIFI_MAIN_NM")
    private String wifiName;

    @SerializedName("X_SWIFI_ADRES1")
    private String wifiStreetName;

    @SerializedName("X_SWIFI_ADRES2")
    private String wifiDetailAddress;

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String wifiFloor;

    @SerializedName("X_SWIFI_INSTL_TY")
    private String wifiCategory;

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String wifiOrganization;

    @SerializedName("X_SWIFI_SVC_SE")
    private String wifiServiceCategory;

    @SerializedName("X_SWIFI_CMCWR")
    private String wifiWireCategory;

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private Integer wifiInstallYear;

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String wifiInsideOutside;

    @SerializedName("X_SWIFI_REMARS3")
    private String wifiConnectEnvironment;

    @SerializedName("LAT")
    private Double wifiLocationX;

    @SerializedName("LNT")
    private Double wifiLocationY;

    @SerializedName("WORK_DTTM")
    private String wifiWorkDateTime;

    private Double distance;

}
