package com.xbleey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "welfare")
public class Welfare {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "welfare_project_id")
    private long welfareProjectId;
    @Column(name = "welfare_name")
    private String welfareName;
    @Column(name = "welfare_info")
    private String welfareInfo;
    @Column(name = "welfare_number")
    private long welfareNumber;
}
