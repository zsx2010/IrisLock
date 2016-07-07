package com.wcsn.irislock.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import java.io.Serializable;

/**
 * Entity mapped to table "USER".
 */
public class User implements Serializable{

    private Long id;
    /** Not-null value. */
    private String user_id;
    /** Not-null value. */
    private String user_name;
    /** Not-null value. */
    private String user_info;
    /** Not-null value. */
    private String user_flag;
    /** Not-null value. */
    private String valid_time_start;
    /** Not-null value. */
    private String valid_time_stop;
    /** Not-null value. */
    private String valid_time_week;
    /** Not-null value. */
    private String register_time;
    /** Not-null value. */
    private String iris_path;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String user_id, String user_name, String user_info, String user_flag, String valid_time_start, String valid_time_stop, String valid_time_week, String register_time, String iris_path) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_info = user_info;
        this.user_flag = user_flag;
        this.valid_time_start = valid_time_start;
        this.valid_time_stop = valid_time_stop;
        this.valid_time_week = valid_time_week;
        this.register_time = register_time;
        this.iris_path = iris_path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getUser_id() {
        return user_id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /** Not-null value. */
    public String getUser_name() {
        return user_name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /** Not-null value. */
    public String getUser_info() {
        return user_info;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    /** Not-null value. */
    public String getUser_flag() {
        return user_flag;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUser_flag(String user_flag) {
        this.user_flag = user_flag;
    }

    /** Not-null value. */
    public String getValid_time_start() {
        return valid_time_start;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setValid_time_start(String valid_time_start) {
        this.valid_time_start = valid_time_start;
    }

    /** Not-null value. */
    public String getValid_time_stop() {
        return valid_time_stop;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setValid_time_stop(String valid_time_stop) {
        this.valid_time_stop = valid_time_stop;
    }

    /** Not-null value. */
    public String getValid_time_week() {
        return valid_time_week;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setValid_time_week(String valid_time_week) {
        this.valid_time_week = valid_time_week;
    }

    /** Not-null value. */
    public String getRegister_time() {
        return register_time;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    /** Not-null value. */
    public String getIris_path() {
        return iris_path;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setIris_path(String iris_path) {
        this.iris_path = iris_path;
    }

}
