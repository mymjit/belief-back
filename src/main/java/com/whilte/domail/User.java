package com.whilte.domail;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "belief_user")
public class User implements Serializable {
    private static final long serialVersionUID = -1L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /** 用户名称 （真实名称） */
    private String  userName;
    /** 用户别名   (匿名,禁止重复) */
    private String  alias;
    /** 性别  */
    private String  sex;
    /** 年龄 ( 根据身份证号自动计算 ) */
    private int age;
    /** 身份证(实名认证 ) */
    private String  idCard;
    /** 邮箱 */
    private String  email;
    /** 电话 (登入的账号,唯一) */
    @Column( unique = true, length = 11)
    private String  telephoneNumber;
    /** 密码 (MD5加密后的密码) */
    private String  password;
    /** 积分 (对网站的贡献) */
    private int integral;
    /** 数据状态(控制信息删除销毁等操作)(0.删除  1.正常(默认的状态)  2.异常(存在被盗的可能) 3.拉黑(被系统禁止登入) )*/
    private int state;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User(String userName, String alias, String sex, int age, String idCard, String email,
                String telephoneNumber, String password, int integral, int state) {
        this.userName = userName;
        this.alias = alias;
        this.sex = sex;
        this.age = age;
        this.idCard = idCard;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        this.integral = integral;
        this.state = state;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", alias='" + alias + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", integral=" + integral +
                ", state=" + state +
                '}';
    }
}
