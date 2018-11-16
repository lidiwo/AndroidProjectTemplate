package com.lidiwo.android.base_module.base;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 16:48
 * @Company：智能程序员
 * @Description： *****************************************************
 */

public class BaseResponseBean<T> {
    private int code;
    private String message;
    private T results;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
