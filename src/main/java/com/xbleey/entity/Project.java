/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Project
 * Author:   11580
 * Date:     2019/6/18 0018 23:15
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
@Table(name = "project")
public class Project {
    @Id
    @Column(name = " project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    @Column(name = " project_name")
    private String projectName;
    @Column(name = " project_instruction")
    private String projectInstruction;
    @Column(name = " project_pm_id")
    private Integer projectPmId;
    @Column(name = " project_start_money")
    private Integer projectStartMoney;
    @Column(name = " project_true_money")
    private Integer projectTrueMoney;
    @Column(name = " project_total_money")
    private Integer projectTotalMoney;
    @Column(name = " project_status")
    private String projectStatus;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectInstruction() {
        return projectInstruction;
    }

    public void setProjectInstruction(String projectInstruction) {
        this.projectInstruction = projectInstruction;
    }

    public Integer getProjectPmId() {
        return projectPmId;
    }

    public void setProjectPmId(Integer projectPmId) {
        this.projectPmId = projectPmId;
    }

    public Integer getProjectStartMoney() {
        return projectStartMoney;
    }

    public void setProjectStartMoney(Integer projectStartMoney) {
        this.projectStartMoney = projectStartMoney;
    }

    public Integer getProjectTrueMoney() {
        return projectTrueMoney;
    }

    public void setProjectTrueMoney(Integer projectTrueMoney) {
        this.projectTrueMoney = projectTrueMoney;
    }

    public Integer getProjectTotalMoney() {
        return projectTotalMoney;
    }

    public void setProjectTotalMoney(Integer projectTotalMoney) {
        this.projectTotalMoney = projectTotalMoney;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectInstruction='" + projectInstruction + '\'' +
                ", projectPmId=" + projectPmId +
                ", projectStartMoney=" + projectStartMoney +
                ", projectTrueMoney=" + projectTrueMoney +
                ", projectTotalMoney=" + projectTotalMoney +
                ", projectStatus=" + projectStatus +
                '}';
    }
}
 

