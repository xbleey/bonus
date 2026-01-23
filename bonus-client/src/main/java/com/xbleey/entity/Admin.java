/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Admin
 * Author:   11580
 * Date:     2019/6/14 0014 16:47
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
 * @create 2019/6/14 0014
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "administrator")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "admin_user")
    private String adminUser;
    @Column(name = "admin_pass")
    private String adminPass;
}
 

