package com.example.zb_wifi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBookmarkGroupDto {
    private String groupName;
    private int groupPriority;
}
