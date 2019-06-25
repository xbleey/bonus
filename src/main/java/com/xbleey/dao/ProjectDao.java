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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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
    public Project getFirstByProjectId(Integer projectId);

    public List<Project> findAllByProjectPmId(Integer pmId);

    public List<Project> findAllByProjectStatus(String projectStatus);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update project e set project_status=?1,project_unpass_dir=?3 where project_id=?2",nativeQuery = true)
    public int updateStatus(String status,Integer projectId,String dirUnPassInfo);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update project e set project_status=?1,project_unpass_boss=?3 where project_id=?2",nativeQuery = true)
    public int updateStatusBoss(String status,Integer projectId,String bossUnPassInfo);
}