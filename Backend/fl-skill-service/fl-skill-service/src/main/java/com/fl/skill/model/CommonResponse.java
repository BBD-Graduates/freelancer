package com.fl.skill.model;

import java.util.Date;

public class CommonResponse<T> {
    public Date current_Timestamp;
    public int status;

    public T response;

    public CommonResponse(){

    }
    public CommonResponse( T response,int status){
        current_Timestamp = new Date();
        this.status = status;
        this.response = response;
    }
}
