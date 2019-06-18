/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Director
 * Author:   11580
 * Date:     2019/6/18 0018 21:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.entity;

import javax.persistence.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/18 0018
 * @since 1.0.0
 */
@Entity
@Table(name = "director")
public class Director {
    @Id
    @Column(name = " director_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer directorId;
    @Column(name = " director_user")
    private String directorUser;
    @Column(name = " director_pass")
    private String directorPass;
    @Column(name = " director_name")
    private String directorName;
    @Column(name = " director_sex")
    private Boolean directorSex;
    @Column(name = " director_phone")
    private Long directorPhone;
    @Column(name = " director_bank")
    private int directorBank;

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getDirectorUser() {
        return directorUser;
    }

    public void setDirectorUser(String directorUser) {
        this.directorUser = directorUser;
    }

    public String getDirectorPass() {
        return directorPass;
    }

    public void setDirectorPass(String directorPass) {
        this.directorPass = directorPass;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Boolean getDirectorSex() {
        return directorSex;
    }

    public void setDirectorSex(Boolean directorSex) {
        this.directorSex = directorSex;
    }

    public Long getDirectorPhone() {
        return directorPhone;
    }

    public void setDirectorPhone(Long directorPhone) {
        this.directorPhone = directorPhone;
    }

    public int getDirectorBank() {
        return directorBank;
    }

    public void setDirectorBank(int directorBank) {
        this.directorBank = directorBank;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", directorUser='" + directorUser + '\'' +
                ", directorPass='" + directorPass + '\'' +
                ", directorName='" + directorName + '\'' +
                ", directorSex=" + directorSex +
                ", directorPhone=" + directorPhone +
                ", directorBank=" + directorBank +
                '}';
    }
}
 

