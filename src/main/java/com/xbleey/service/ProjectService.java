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
import com.xbleey.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Project> findAllProject() {
        List<Project> projects = projectDao.findAll();
        return projects;
    }

    public List<Project> findAllByProjectStatus(String status) {
        return projectDao.findAllByProjectStatus(status);
    }

    public Project getByProjectId(Integer projectId) {
        return projectDao.getFirstByProjectId(projectId);
    }

    public HashMap<Integer, String> getIdAndName() {
        List<Project> projects = projectDao.findAll();
        HashMap<Integer, String> projectNames = new HashMap<>();
        for (Project p : projects) {
            projectNames.put(p.getProjectId(), p.getProjectName());
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

    public List<Project> classifyProjectByStatus(List<Project> projects, String status) {
        List<Project> newProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getProjectStatus().equals(status)) {
                newProjects.add(project);
            }
        }
        return newProjects;
    }

    public Integer updateStatus(String status, Integer projectId) {
        return projectDao.updateStatus(status, projectId);
    }
}
 

