package com.wcsn.irislock.login.bean;

/**
 * Created by suiyue on 2016/6/22 0022.
 */
public class UserInfo {
    private String user_id;
    private String user_name;
    private String user_info;
    private String user_flag;
    private String valid_time_start;
    private String valid_time_stop;
    private String valid_time_week;
    private String register_time;
    private String iris_path;

    public String getIris_path() {
        return iris_path;
    }

    public void setIris_path(String iris_path) {
        this.iris_path = iris_path;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getUser_flag() {
        return user_flag;
    }

    public void setUser_flag(String user_flag) {
        this.user_flag = user_flag;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }


    public String getValid_time_start() {
        return valid_time_start;
    }

    public void setValid_time_start(String valid_time_start) {
        this.valid_time_start = valid_time_start;
    }

    public String getValid_time_stop() {
        return valid_time_stop;
    }

    public void setValid_time_stop(String valid_time_stop) {
        this.valid_time_stop = valid_time_stop;
    }

    public String getValid_time_week() {
        return valid_time_week;
    }

    public void setValid_time_week(String valid_time_week) {
        this.valid_time_week = valid_time_week;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "iris_path='" + iris_path + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_nameTest='" + user_name + '\'' +
                ", user_info='" + user_info + '\'' +
                ", user_flag='" + user_flag + '\'' +
                ", valid_time_start='" + valid_time_start + '\'' +
                ", valid_time_stop='" + valid_time_stop + '\'' +
                ", valid_time_week='" + valid_time_week + '\'' +
                ", register_time='" + register_time + '\'' +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
