package com.sustly.dto;

/**
 * @Author: liyue
 * @Date: 19-9-5 下午6:47
 */
public class DataBack<T> {
    public T data;
    private boolean result;

    public DataBack(T data, boolean result) {
        this.data = data;
        this.result = result;
    }
}
