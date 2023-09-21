package com.example.front_debug_server.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HttpResp<T> implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int status;

    private String message;

    private T data;

    public HttpResp() {}

    public HttpResp (HttpError error) {
        this.status = error.getStatus();
        this.message = error.getDesc();
    }

    public HttpResp(int status, String msg, T data) {
        this.status = status;
        this.message = msg;
        this.data = data;
    }

    public static <T> HttpResp<T> ok() {
        return restResult(null, HttpError.OK);
    }

    public static <T> HttpResp<T> ok(T data) {
        return restResult(data, HttpError.OK);
    }

    private static <T> HttpResp<T> restResult(T data, HttpError errorEnum) {
        return new HttpResp<>(errorEnum.getStatus(), errorEnum.getDesc(), data);
    }

    private static <T> HttpResp<T> restResult(T data, HttpError errorEnum, String msg) {
        return new HttpResp<>(errorEnum.getStatus(), msg, data);
    }

    public static <T> HttpResp<T> failed(String msg) {
        return restResult(null, HttpError.UNKNOWN_ERROR, msg);
    }

    public static <T> HttpResp<T> failed(Throwable msg) {
        return restResult(null, HttpError.UNKNOWN_ERROR, msg.getMessage());
    }


    public static <T> HttpResp<T> failed(HttpError errorEnum, String msg) {
        return restResult(null, errorEnum, msg);
    }
}
