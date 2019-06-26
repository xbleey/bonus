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
import com.xbleey.entity.Welfare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Project> projects = projectDao.findAll();
        List<Project> finishAllProjects = projectDao.findAllByProjectStatusAndProjectFinish("总经理过审", true);
        projects.removeAll(finishAllProjects);
        return projects;
    }

    public List<Project> findFinishAllProjects() {
        return projectDao.findAllByProjectStatusAndProjectFinish("总经理过审", true);
    }

    public List<Project> findAllByProjectStatus(String status) {
        return projectDao.findAllByProjectStatus(status);
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
        List<Project> projects =projectDao.findAllByProjectPmId(pmId);
        projects.removeAll(projectDao.findAllByProjectStatusAndProjectFinish("总经理过审", true));
        return projects;
    }

    public List<Project> findAllByFinish(Boolean finish) {
        return projectDao.findAllByProjectFinish(finish);
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

    public Integer updateStatus(String status, Integer projectId, String dirUnPassInfo) {
        return projectDao.updateStatus(status, projectId, dirUnPassInfo);
    }

    public Integer updateStatusBoss(String status, Integer projectId, String bossUnPassInfo) {
        return projectDao.updateStatusBoss(status, projectId, bossUnPassInfo);
    }

    public Integer updateFinishPm(boolean finish, Integer projectTrueMoney, Integer projectTotalMoney, Integer projectId) {
        return projectDao.updateFinishPm(finish, projectTrueMoney, projectTotalMoney, projectId);
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

    public void classifyProject(Model model, List<Project> sourceProjects, HashMap<String, String> names) {

        for (Map.Entry<String, String> name : names.entrySet()) {
            if (name.getKey().equals("finishAllProjects")) {
                model.addAttribute(name.getKey(), findFinishAllProjects());
            } else {
                model.addAttribute(name.getKey(), classifyProjectByStatus(sourceProjects, name.getValue()));
            }
        }
        HashMap<Integer, String> pmMaps = pmService.getIdAndName();
        model.addAttribute("pmMaps", pmMaps);
    }

    public void setTrueMoney(Project project, List<Welfare> welfares) {
        int total = 0;
        total = +project.getProjectStartMoney();
        for (Welfare welfare : welfares) {
            total = total + welfare.getWelfareNumber();
        }
        project.setProjectTrueMoney(total);
    }

    public void setTotalMoney(Project project, Integer[] bonuses) {
        if (bonuses != null) {
            int total = 0;
            total = project.getProjectTrueMoney() - project.getProjectStartMoney();
            for (int i = 0; i < bonuses.length; i++) {
                total = total + bonuses[i];
            }
            project.setProjectTotalMoney(total);
        }
    }
}
 

