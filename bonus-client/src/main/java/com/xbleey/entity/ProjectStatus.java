package com.xbleey.entity;

import java.util.EnumSet;
import java.util.Set;

/**
 * 项目状态定义及允许的状态流转。
 */
public enum ProjectStatus {
    DRAFT("未提交"),
    UNDER_REVIEW("审核中"),
    DIRECTOR_APPROVED("总管过审"),
    DIRECTOR_REJECTED("总管拒绝通过"),
    BOSS_APPROVED("总经理过审"),
    BOSS_REJECTED("总经理拒绝通过");

    private final String description;
    private Set<ProjectStatus> nextStatuses;

    ProjectStatus(String description) {
        this.description = description;
    }

    static {
        DRAFT.nextStatuses = EnumSet.of(UNDER_REVIEW);
        UNDER_REVIEW.nextStatuses = EnumSet.of(DIRECTOR_APPROVED, DIRECTOR_REJECTED);
        DIRECTOR_APPROVED.nextStatuses = EnumSet.of(BOSS_APPROVED, BOSS_REJECTED);
        DIRECTOR_REJECTED.nextStatuses = EnumSet.noneOf(ProjectStatus.class);
        BOSS_APPROVED.nextStatuses = EnumSet.noneOf(ProjectStatus.class);
        BOSS_REJECTED.nextStatuses = EnumSet.noneOf(ProjectStatus.class);
    }

    public boolean canTransitTo(ProjectStatus targetStatus) {
        if (targetStatus == null) {
            return false;
        }
        return nextStatuses.contains(targetStatus);
    }

    public Set<ProjectStatus> getNextStatuses() {
        return EnumSet.copyOf(nextStatuses);
    }

    public String getDescription() {
        return description;
    }

    public static ProjectStatus fromDatabaseValue(String value) {
        if (value == null) {
            return null;
        }
        for (ProjectStatus status : values()) {
            if (status.description.equals(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的项目状态：" + value);
    }

    @Override
    public String toString() {
        return description;
    }
}
