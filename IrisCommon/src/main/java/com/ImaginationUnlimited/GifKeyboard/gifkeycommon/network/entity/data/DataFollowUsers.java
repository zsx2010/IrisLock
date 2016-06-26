package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.PersonFansInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by suiyue on 16/6/6.
 */
public class DataFollowUsers extends BaseListData<PersonFansInfo> implements Serializable {

    private ArrayList<PersonFansInfo> personFansInfos;

    public ArrayList<PersonFansInfo> getPersonFansInfos() {
        return personFansInfos;
    }

    public void setPersonFansInfos(ArrayList<PersonFansInfo> personFansInfos) {
        this.personFansInfos = personFansInfos;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
