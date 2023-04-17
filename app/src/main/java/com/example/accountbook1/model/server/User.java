package com.example.accountbook1.model.server;

/**
 * 用户
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className User
 * @see
 * @since 1.0.0
 */

public class User {
    private Long id;

    private String uid;
    private String realName;
    private String nickName;
    private String email;
    private String phone;
    private String password;

    public User() {
    }

    public User(String uid, String realName, String nickName, String email, String phone, String password) {
        this.id = null;
        this.uid = uid;
        this.realName = realName;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
