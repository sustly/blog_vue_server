package com.sustly.dto;

import lombok.Data;

/**
 * @Author: liyue
 * @Date: 19-9-5 下午5:58
 */
@Data
@SuppressWarnings("unchecked")
public class ResponseMsg {

    private boolean result;
    private Object data;


    public static ResponseMsg onOk(Object data, boolean result){
        ResponseMsg msg = new ResponseMsg();
        msg.data = data;
        msg.result = result;
        return msg;
    }
}
