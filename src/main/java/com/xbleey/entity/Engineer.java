/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Engineer
 * Author:   11580
 * Date:     2019/6/14 0014 23:26
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
 * @create 2019/6/14 0014
 * @since 1.0.0
 */
@Entity
@Table(name = "engineer")
public class Engineer {
    @Id
    @Column(name = " engineer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer engineerId;
    @Column(name = " engineer_user")
    private String engineerUser;
    @Column(name = " engineer_pass")
    private String engineerPass;
    @Column(name = " engineer_sex")
    private Boolean engineerSex;
    @Column(name = " engineer_phone")
    private Long engineerPhone;
    @Column(name = " engineer_bank")
    private Integer engineerBank;

    public Integer getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Integer engineerId) {
        this.engineerId = engineerId;
    }

    public String getEngineerUser() {
        return engineerUser;
    }

    public void setEngineerUser(String engineerUser) {
        this.engineerUser = engineerUser;
    }

    public String getEngineerPass() {
        return engineerPass;
    }

    public void setEngineerPass(String engineerPass) {
        this.engineerPass = engineerPass;
    }

    public Boolean getEngineerSex() {
        return engineerSex;
    }

    public void setEngineerSex(Boolean engineerSex) {
        this.engineerSex = engineerSex;
    }

    public Long getEngineerPhone() {
        return engineerPhone;
    }

    public void setEngineerPhone(Long engineerPhone) {
        this.engineerPhone = engineerPhone;
    }

    public Integer getEngineerBank() {
        return engineerBank;
    }

    public void setEngineerBank(Integer engineerBank) {
        this.engineerBank = engineerBank;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "engineerId=" + engineerId +
                ", engineerUser='" + engineerUser + '\'' +
                ", engineerPass='" + engineerPass + '\'' +
                ", engineerSex=" + engineerSex +
                ", engineerPhone=" + engineerPhone +
                ", engineerBank=" + engineerBank +
                '}';
    }
}
 

