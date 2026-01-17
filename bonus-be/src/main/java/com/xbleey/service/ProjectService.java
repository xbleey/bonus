/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ProjectService
 * Author:   11580
 * Date:     2019/6/19 0019 22:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dao.ProjectDao;
import com.xbleey.dao.TeamDao;
import com.xbleey.entity.Project;
import com.xbleey.entity.ProjectStatus;
import com.xbleey.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/19 0019
 * @since 1.0.0
 */
@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    PmService pmService;

    public List<Project> findAllProject() {
        return projectDao.findAll();
    }

    public List<Project> findAllByProjectStatus(ProjectStatus status) {
        return projectDao.findAllByProjectStatus(status);
    }

    public Project getByProjectId(Integer projectId) {
        return projectDao.getFirstByProjectId(projectId);
    }

    public Map<Integer, String> getIdAndName() {
        List<Project> projects = projectDao.findAll();
        HashMap<Integer, String> projectNames = new HashMap<>();
        for (Project p : projects) {
            projectNames.put(p.getId(), p.getProjectName());
        }
        return projectNames;
    }

    public void saveProject(Project project) {
        projectDao.save(project);

    }

    public List<Project> findAllByEngineerId(Integer engineerId) {
        ArrayList<Project> projects = new ArrayList<>();
        List<Team> teams = teamDao.findAllByEngineerId(engineerId);
        for (Team t : teams) {
            projects.add(projectDao.getFirstByProjectId(t.getProjectId()));
        }
        return projects;
    }

    public List<Project> findAllByPmId(Integer pmId) {
        return projectDao.findAllByProjectPmId(pmId);
    }

    public List<Project> classifyProjectByStatus(List<Project> projects, ProjectStatus status) {
        List<Project> newProjects = new ArrayList<>();
        for (Project project : projects) {
            ProjectStatus projectStatus = Optional.ofNullable(project.getProjectStatus()).orElse(ProjectStatus.DRAFT);
            if (projectStatus.equals(status)) {
                newProjects.add(project);
            }
        }
        return newProjects;
    }

    public Project transitionStatus(Integer projectId, ProjectStatus targetStatus, String dirUnPassInfo, String bossUnPassInfo) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("项目不存在：" + projectId));
        ProjectStatus currentStatus = Optional.ofNullable(project.getProjectStatus()).orElse(ProjectStatus.DRAFT);
        if (!currentStatus.canTransitTo(targetStatus)) {
            throw new IllegalStateException(
                    String.format("项目%s状态无法从%s流转到%s", projectId, currentStatus, targetStatus));
        }
        project.setProjectStatus(targetStatus);
        if (ProjectStatus.DIRECTOR_REJECTED.equals(targetStatus)) {
            project.setDirUnPassInfo(dirUnPassInfo);
        }
        if (ProjectStatus.BOSS_REJECTED.equals(targetStatus)) {
            project.setBossUnPassInfo(bossUnPassInfo);
        }
        return projectDao.save(project);
    }

    public void classifyProject(Model model, List<Project> sourceProjects, Map<String, ProjectStatus> names) {
        for (Map.Entry<String, ProjectStatus> name : names.entrySet()) {
            model.addAttribute(name.getKey(), classifyProjectByStatus(sourceProjects, name.getValue()));
        }
        HashMap<Integer, String> pmMaps = pmService.getIdAndName();
        model.addAttribute("pmMaps", pmMaps);
    }
}
 

