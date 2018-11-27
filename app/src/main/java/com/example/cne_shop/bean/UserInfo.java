package com.example.cne_shop.bean;

/**
 * Created by 博 on 2017/7/25.
 */

public class UserInfo {
    private int user_id ;
    private String ut ;
    private String user_name ;
    private String mobile ;
    private String nick_name ;
    private String shop_name ;
    private String shop_address_detail ;
    private int admin_user_id ;
    private String admin_user_no ;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address_detail() {
        return shop_address_detail;
    }

    public void setShop_address_detail(String shop_address_detail) {
        this.shop_address_detail = shop_address_detail;
    }

    public int getAdmin_user_id() {
        return admin_user_id;
    }

    public void setAdmin_user_id(int admin_user_id) {
        this.admin_user_id = admin_user_id;
    }

    public String getAdmin_user_no() {
        return admin_user_no;
    }

    public void setAdmin_user_no(String admin_user_no) {
        this.admin_user_no = admin_user_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUt() {
        return ut;
    }

    public void setUt(String ut) {
        this.ut = ut;
    }
}
