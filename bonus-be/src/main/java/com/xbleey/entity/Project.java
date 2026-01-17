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

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/18 0018
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_instruction")
    private String projectInstruction;
    @Column(name = "project_pm_id")
    private Integer projectPmId;
    @Column(name = "project_start_money")
    private Integer projectStartMoney;
    @Column(name = "project_true_money")
    private Integer projectTrueMoney;
    @Column(name = "project_total_money")
    private Integer projectTotalMoney;
    @Column(name = "project_status")
    @Convert(converter = ProjectStatusConverter.class)
    private ProjectStatus projectStatus;
    @Column(name = "project_unpass_dir")
    private String dirUnPassInfo;
    @Column(name = "project_unpass_boss")
    private String bossUnPassInfo;
}
