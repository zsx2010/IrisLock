package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.FollowUser;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.Gif;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 搜索的Data的数据结构
 *
 * @author wangheng on 2016-03-15 18:42
 */
public class DataSearch extends BaseListData<Gif> implements Serializable {

    private ArrayList<FollowUser> canFollowUserList;

    public ArrayList<FollowUser> getCanFollowUserList() {
        return canFollowUserList;
    }

    public void setCanFollowUserList(ArrayList<FollowUser> canFollowUserList) {
        this.canFollowUserList = canFollowUserList;
    }

    @Override
    public String toString() {
        return "DataSearch{" +
                "canFollowUserList=" + canFollowUserList +
                '}' + super.toString();
    }
}
