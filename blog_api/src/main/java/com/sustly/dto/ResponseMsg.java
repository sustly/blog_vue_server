package com.sustly.dto;

import lombok.Data;

/**
 * @Author: liyue
 * @Date: 19-9-5 下午5:58
 */
@Data
public class ResponseMsg {

    private boolean result;
    private Object data;


    public static ResponseMsg onOk(Object data){
        ResponseMsg msg = new ResponseMsg();
        msg.data = data;
        msg.result = true;
        return msg;
    }

    public static ResponseMsg onFail(Object data){
        ResponseMsg msg = new ResponseMsg();
        msg.data = data;
        msg.result = false;
        return msg;
    }
}
