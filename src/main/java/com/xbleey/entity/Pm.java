/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Pm
 * Author:   11580
 * Date:     2019/6/18 0018 21:13
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
@Table(name = "PM")
public class Pm {
    @Id
    @Column(name = " PM_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pmId;
    @Column(name = " PM_user")
    private String pmUser;
    @Column(name = " PM_pass")
    private String pmPass;
    @Column(name = " PM_name")
    private String pmName;
    @Column(name = " PM_sex")
    private Boolean pmSex;
    @Column(name = " PM_phone")
    private Long pmPhone;
    @Column(name = " PM_bank")
    private int pmBank;

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public String getPmUser() {
        return pmUser;
    }

    public void setPmUser(String pmUser) {
        this.pmUser = pmUser;
    }

    public String getPmPass() {
        return pmPass;
    }

    public void setPmPass(String pmPass) {
        this.pmPass = pmPass;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public Boolean getPmSex() {
        return pmSex;
    }

    public void setPmSex(Boolean pmSex) {
        this.pmSex = pmSex;
    }

    public Long getPmPhone() {
        return pmPhone;
    }

    public void setPmPhone(Long pmPhone) {
        this.pmPhone = pmPhone;
    }

    public int getPmBank() {
        return pmBank;
    }

    public void setPmBank(int pmBank) {
        this.pmBank = pmBank;
    }

    @Override
    public String toString() {
        return "Pm{" +
                "pmId=" + pmId +
                ", pmUser='" + pmUser + '\'' +
                ", pmPass='" + pmPass + '\'' +
                ", pmName='" + pmName + '\'' +
                ", pmSex=" + pmSex +
                ", pmPhone=" + pmPhone +
                ", pmBank=" + pmBank +
                '}';
    }
}
 

