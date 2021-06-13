package cn.seecoder.courselearning.po.user;

import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.vo.user.UserVO;

import java.util.Date;

public class User {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private String picture;

    private Integer balance;

    private UserRole userRole;

    private Date createTime;

    private Boolean isVip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsVip(){return isVip;}

    public void setIsVip(Boolean isVip) {
        this.isVip=isVip;
    }
    public User() {
    }

    public User(UserVO userVO){
        id = userVO.getId();
        uname = userVO.getUname();
        phone = userVO.getPhone();
        password = userVO.getPassword();
        picture = userVO.getPicture();
        balance = userVO.getBalance();
        userRole = userVO.getUserRole();
        createTime = userVO.getCreateTime();
        isVip=userVO.getIsVip();
    }
}