package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

/**
 * Created by someHui on 16/5/19.
 */
public class DataRegifey implements BaseData {
    String originGif;
    String editInfo;

    public String getOriginGif() {
        return originGif;
    }

    public void setOriginGif(String originGif) {
        this.originGif = originGif;
    }

    public String getEditInfo() {
        return editInfo;
    }

    public void setEditInfo(String editInfo) {
        this.editInfo = editInfo;
    }

    @Override
    public String toString() {
        return "DataRegifey{" +
                "originGif='" + originGif + '\'' +
                ", editInfo='" + editInfo + '\'' +
                '}';
    }
}
