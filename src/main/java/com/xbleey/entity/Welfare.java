package com.xbleey.entity;

import javax.persistence.*;

@Entity
@Table(name = "welfare")
public class Welfare {

    @Id
    @Column(name = " welfare_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long welfareId;
    @Column(name = " welfare_project_id")
    private long welfareProjectId;
    @Column(name = " welfare_name")
    private String welfareName;
    @Column(name = " welfare_info")
    private String welfareInfo;
    @Column(name = " welfare_number")
    private long welfareNumber;



    public long getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(long welfareId) {
        this.welfareId = welfareId;
    }


    public long getWelfareProjectId() {
        return welfareProjectId;
    }

    public void setWelfareProjectId(long welfareProjectId) {
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


    public long getWelfareNumber() {
        return welfareNumber;
    }

    public void setWelfareNumber(long welfareNumber) {
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
