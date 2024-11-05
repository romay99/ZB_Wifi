package com.example.zb_wifi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookmarkGroup {
    private int groupId;
    private String groupName;
    private int groupPriority;
    private String groupMakeDate;
    private String groupModifyDate;
}
