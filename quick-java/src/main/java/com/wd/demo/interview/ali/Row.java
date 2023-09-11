package com.wd.demo.interview.ali;

/**
 * @Author: wangd
 * @Date: 2023/7/11 0:01
 */
public class Row {
    String id;
    String groupId;
    float quota;

    public Row(String id, String groupId, float quota) {
        this.id = id;
        this.groupId = groupId;
        this.quota = quota;
    }

    @Override
    public String toString() {
        return "Row{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", quota=" + quota +
                '}';
    }
}
