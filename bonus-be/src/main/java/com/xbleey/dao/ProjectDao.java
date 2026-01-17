/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ProjectDao
 * Author:   11580
 * Date:     2019/6/18 0018 23:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.dao;

import com.xbleey.entity.Project;
import com.xbleey.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/18 0018
 * @since 1.0.0
 */
public interface ProjectDao extends JpaRepository<Project, Integer> {
    Project getFirstByProjectId(Integer projectId);

    List<Project> findAllByProjectPmId(Integer pmId);

    List<Project> findAllByProjectStatus(ProjectStatus projectStatus);
}
