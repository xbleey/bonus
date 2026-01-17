/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Money
 * Author:   11580
 * Date:     2019/6/18 0018 23:35
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

import java.sql.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/18 0018
 * @since 1.0.0
 */
@Entity
@Table(name = "money")
@Data
public class Money {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "money_date")
    private Date moneyDate;
    @Column(name = "money_from_bank")
    private Integer moneyFromBank;
    @Column(name = "money_to_bank")
    private Integer moneyToBank;
    @Column(name = "money_number")
    private Integer moneyNumber;


}
