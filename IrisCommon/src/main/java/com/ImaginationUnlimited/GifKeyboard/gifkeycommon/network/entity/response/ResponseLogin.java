package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.DataLogin;

/**
 * 登录返回的数据结构
 * @author wangheng on 2016-03-29 11:20
 */
public class ResponseLogin extends BaseResponse<DataLogin> {

    private static final int CODE_ERROR_EMAIL_FORMAT = 2;
    private static final int CODE_ERROR_PASSWORD = 3;

    public boolean isErrorEmailFormat(){
        return CODE_ERROR_EMAIL_FORMAT == code;
    }

    public boolean isErrorPassword(){
        return CODE_ERROR_PASSWORD == code;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected int getInvalidTokenCode() {
        return -1;// no valid code;
    }
}
