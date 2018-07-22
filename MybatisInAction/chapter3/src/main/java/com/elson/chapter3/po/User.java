package com.elson.chapter3.po;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import java.util.Date;

public class User {

    private Long id;
    private String userName;
    private String cnName;
    private EnumOrdinalTypeHandlerSex sex;
    private Date birthday;
    private String mobile;
    private String email;
    private String note;

    //private EnumTypeHandlerSex sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public EnumOrdinalTypeHandlerSex getSex() {
        return sex;
    }

    public void setSex(EnumOrdinalTypeHandlerSex sex) {
        this.sex = sex;
    }

/*    public EnumTypeHandlerSex getSex() {
        return sex;
    }

    public void setSex(EnumTypeHandlerSex sex) {
        this.sex = sex;
    }*/

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "--->" + id + "--->" + userName + "--->" + cnName + "--->" + sex +
                "--->" + mobile + "--->" + email + "--->" + note;
    }


}
