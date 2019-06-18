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

import javax.persistence.*;
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
public class Money {
    @Id
    @Column(name = " money_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moneyId;
    @Column(name = " money_date")
    private Date moneyDate;
    @Column(name = " money_from_bank")
    private Integer moneyFromBank;
    @Column(name = " money_to_bank")
    private Integer moneyToBank;
    @Column(name = " money_number")
    private Integer moneyNumber;

    public Integer getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }

    public Date getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(Date moneyDate) {
        this.moneyDate = moneyDate;
    }

    public Integer getMoneyFromBank() {
        return moneyFromBank;
    }

    public void setMoneyFromBank(Integer moneyFromBank) {
        this.moneyFromBank = moneyFromBank;
    }

    public Integer getMoneyToBank() {
        return moneyToBank;
    }

    public void setMoneyToBank(Integer moneyToBank) {
        this.moneyToBank = moneyToBank;
    }

    public Integer getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(Integer moneyNumber) {
        this.moneyNumber = moneyNumber;
    }

    @Override
    public String toString() {
        return "Money{" +
                "moneyId=" + moneyId +
                ", moneyDate=" + moneyDate +
                ", moneyFromBank=" + moneyFromBank +
                ", moneyToBank=" + moneyToBank +
                ", moneyNumber=" + moneyNumber +
                '}';
    }
}
 

