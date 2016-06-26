package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Base Data
 * @author wangheng on 2016-03-15 17:11
 */
public class BaseListData<T extends Serializable> implements BaseData{

    private static final int IS_LAST_PAGE = 1;

    private ArrayList<T> list;

    private int lastPage;


    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isLastPage(){
        return IS_LAST_PAGE == lastPage;
    }

    @Override
    public String toString() {
        return "BaseListData{" +
                "list=" + list +
                ", lastPage=" + lastPage +
                '}';
    }
}
