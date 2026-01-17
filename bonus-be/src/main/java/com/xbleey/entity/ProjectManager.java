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

import jakarta.persistence.Column;
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
@Entity
@Table(name = "project_manager")
@Data
public class ProjectManager {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id")
    private Integer pmId;
    @Column(name = "pm_user")
    private String pmUser;
    @Column(name = "pm_pass")
    private String pmPass;
    @Column(name = "pm_name")
    private String pmName;
    @Column(name = "pm_sex")
    private Boolean pmSex;
    @Column(name = "pm_phone")
    private Long pmPhone;

}
 

