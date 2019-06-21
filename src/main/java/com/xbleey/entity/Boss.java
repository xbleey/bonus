/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: boss
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
@Table(name = "boss")
public class Boss {
    @Id
    @Column(name = " boss_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bossId;
    @Column(name = " boss_user")
    private String bossUser;
    @Column(name = " boss_pass")
    private String bossPass;
    @Column(name = " boss_name")
    private String bossName;
    @Column(name = " boss_sex")
    private Boolean bossSex;
    @Column(name = " boss_phone")
    private Long bossPhone;

    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    public String getBossUser() {
        return bossUser;
    }

    public void setBossUser(String bossUser) {
        this.bossUser = bossUser;
    }

    public String getBossPass() {
        return bossPass;
    }

    public void setBossPass(String bossPass) {
        this.bossPass = bossPass;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public Boolean getBossSex() {
        return bossSex;
    }

    public void setBossSex(Boolean bossSex) {
        this.bossSex = bossSex;
    }

    public Long getBossPhone() {
        return bossPhone;
    }

    public void setBossPhone(Long bossPhone) {
        this.bossPhone = bossPhone;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "bossId=" + bossId +
                ", bossUser='" + bossUser + '\'' +
                ", bossPass='" + bossPass + '\'' +
                ", bossName='" + bossName + '\'' +
                ", bossSex=" + bossSex +
                ", bossPhone=" + bossPhone +
                '}';
    }
}
 

