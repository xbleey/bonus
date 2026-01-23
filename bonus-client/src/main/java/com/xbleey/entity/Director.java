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
@Table(name = "director")
@Data
public class Director {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "director_user")
    private String directorUser;
    @Column(name = "director_pass")
    private String directorPass;
    @Column(name = "director_name")
    private String directorName;
    @Column(name = "director_sex")
    private Boolean directorSex;
    @Column(name = "director_phone")
    private Long directorPhone;
    @Column(name = "director_bank")
    private int directorBank;


}
