package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.BaseData;

/**
 * 所有返回数据的数据结构
 * @author wangheng on 2016-03-15 17:06
 */
public class BaseResponse<T extends BaseData> {

    private static final int CODE_SUCCESS = 1;
    private static final int CUSTOM_INVALIDTOKEN = 2;

    protected int code;

    protected String msg;

    protected T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 请求是否成功
     * @return 请求是否成功
     */
    public final boolean isSuccess(){
        return CODE_SUCCESS == code;
    }

    protected int getInvalidTokenCode(){
        return 2;
    }

    public final  boolean isInvalidToken(){
        return getInvalidTokenCode() == code;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
