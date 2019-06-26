/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Team
 * Author:   11580
 * Date:     2019/6/18 0018 23:39
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
@Table(name = "team")
public class Team {
    @Id
    @Column(name = " team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    @Column(name = " true_id")
    private Integer trueId;
    @Column(name = " project_id")
    private Integer projectId;
    @Column(name = " pm_id")
    private Integer pmId;
    @Column(name = " engineer_id")
    private Integer engineerId;
    @Column(name = " engineer_money")
    private Integer engineerMoney;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTrueId() {
        return trueId;
    }

    public void setTrueId(Integer trueId) {
        this.trueId = trueId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Integer engineerId) {
        this.engineerId = engineerId;
    }

    public Integer getEngineerMoney() {
        return engineerMoney;
    }

    public void setEngineerMoney(Integer engineerMoney) {
        this.engineerMoney = engineerMoney;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", trueId=" + trueId +
                ", projectId=" + projectId +
                ", pmId=" + pmId +
                ", engineerId=" + engineerId +
                ", engineerMoney=" + engineerMoney +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        try {
            obj = (Team) obj;
            if (this.trueId.equals(((Team) obj).getTrueId())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return super.equals(obj);
        }

    }
}
 

