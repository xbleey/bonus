package com.xbleey.entity;

import javax.persistence.*;

@Entity
@Table(name = "welfare")
public class Welfare {

    @Id
    @Column(name = " welfare_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer welfareId;
    @Column(name = " welfare_project_id")
    private Integer welfareProjectId;
    @Column(name = " welfare_name")
    private String welfareName;
    @Column(name = " welfare_info")
    private String welfareInfo;
    @Column(name = " welfare_number")
    private Integer welfareNumber;

    public Integer getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Integer welfareId) {
        this.welfareId = welfareId;
    }

    public Integer getWelfareProjectId() {
        return welfareProjectId;
    }

    public void setWelfareProjectId(Integer welfareProjectId) {
        this.welfareProjectId = welfareProjectId;
    }

    public String getWelfareName() {
        return welfareName;
    }

    public void setWelfareName(String welfareName) {
        this.welfareName = welfareName;
    }

    public String getWelfareInfo() {
        return welfareInfo;
    }

    public void setWelfareInfo(String welfareInfo) {
        this.welfareInfo = welfareInfo;
    }

    public Integer getWelfareNumber() {
        return welfareNumber;
    }

    public void setWelfareNumber(Integer welfareNumber) {
        this.welfareNumber = welfareNumber;
    }

    @Override
    public String toString() {
        return "Welfare{" +
                "welfareId=" + welfareId +
                ", welfareProjectId=" + welfareProjectId +
                ", welfareName='" + welfareName + '\'' +
                ", welfareInfo='" + welfareInfo + '\'' +
                ", welfareNumber=" + welfareNumber +
                '}';
    }
}
