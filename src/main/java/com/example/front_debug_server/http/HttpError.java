package com.example.front_debug_server.http;

import java.io.Serializable;

public enum HttpError implements Serializable {
    UNKNOWN_ERROR(-1, "服务器开小差了，未知错误"),
    PARAM_ERROR(1001, "参数错误"),

    OK(200, "ok");

    private int status;
    private String desc;

    HttpError(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
