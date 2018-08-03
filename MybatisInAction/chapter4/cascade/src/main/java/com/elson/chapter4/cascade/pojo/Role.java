package com.elson.chapter4.cascade.pojo;

public class Role {
    private Integer id;
    private String roleName;
    private String note;
    private Integer userId;
    private String userName;
    private String cnName;
    private Integer sex;
    private String mobile;
    private String email;
    private String userNote;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", cnName='" + cnName + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}