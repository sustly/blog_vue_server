package com.sustly.util;

import com.sustly.dto.DataBack;
import com.sustly.entry.User;

import java.util.List;

/**
 * @Author: liyue
 * @Date: 19-9-5 下午5:58
 */
public class ResponseMsg<T> {

    private T response;

    private ResponseMsg(T o){
        this.response = o;
    }
    /*public <R> ResponseMsg<R> onOk(T data, boolean result){
        return new ResponseMsg<R>(new DataBack<T>(data, result));
    }

    public static <R> ResponseMsg<R> onOk(List<R> dataList, boolean result){
        return new ResponseMsg<>(dataList, result);
    }*/
}
