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
@Data
@Entity
@Table(name = "team")
public class Team {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "true_id")
    private Integer trueId;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "pm_id")
    private Integer pmId;
    @Column(name = "engineer_id")
    private Integer engineerId;
}
