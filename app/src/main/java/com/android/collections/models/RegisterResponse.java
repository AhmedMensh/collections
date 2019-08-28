package com.android.collections.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("fmctoken")
    @Expose
    private String fmctoken;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("email")
    @Expose
    private String email;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getFmctoken() {
        return fmctoken;
    }

    public void setFmctoken(String fmctoken) {
        this.fmctoken = fmctoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
