package com.xbleey.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 在数据库与枚举之间做双向转换，保证表中仍然保存中文状态值。
 */
@Converter(autoApply = true)
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, String> {

    @Override
    public String convertToDatabaseColumn(ProjectStatus attribute) {
        return attribute == null ? null : attribute.getDescription();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(String dbData) {
        return ProjectStatus.fromDatabaseValue(dbData);
    }
}
