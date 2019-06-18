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
    @Column(name = " project_engineer_id")
    private Integer projectEngineerId;
    @Column(name = " project_for_money")
    private Integer projectForMoney;

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

    public Integer getProjectEngineerId() {
        return projectEngineerId;
    }

    public void setProjectEngineerId(Integer projectEngineerId) {
        this.projectEngineerId = projectEngineerId;
    }

    public Integer getProjectForMoney() {
        return projectForMoney;
    }

    public void setProjectForMoney(Integer projectForMoney) {
        this.projectForMoney = projectForMoney;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectInstruction='" + projectInstruction + '\'' +
                ", projectPmId=" + projectPmId +
                ", projectEngineerId=" + projectEngineerId +
                ", projectForMoney=" + projectForMoney +
                '}';
    }
}
 

