/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EngineerController
 * Author:   11580
 * Date:     2019/6/23 0023 15:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.Engineer;
import com.xbleey.entity.Project;
import com.xbleey.entity.ProjectManager;
import com.xbleey.service.EngineerService;
import com.xbleey.service.PmService;
import com.xbleey.service.ProjectService;
import com.xbleey.service.SecurityService;
import com.xbleey.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/23 0023
 * @since 1.0.0
 */
@Controller
public class EngineerController {
    @Autowired
    EngineerService engineerService;
    @Autowired
    TeamService teamService;
    @Autowired
    ProjectService projectService;
    @Autowired
    PmService pmService;
    @Autowired
    SecurityService securityService;

    @GetMapping(value = "/engineer/projects")
    public String engineerProject(Model model) {
        Integer engineerId = engineerService.findDistinctByEngineerUser(securityService.getUserName()).getId();
        List<Project> myProjects = projectService.findAllByEngineerId(engineerId);

        List<ProjectManager> projectManagers = pmService.findAll();
        HashMap<Integer, String> pmMaps = new HashMap<>();
        for (ProjectManager p : projectManagers) {
            pmMaps.put(p.getPmId(), p.getPmName());
        }

        model.addAttribute("myProjects", myProjects);
        model.addAttribute("pmMaps", pmMaps);
        return "project/projects";
    }

    @GetMapping(value = "/engineer/projects/{id}")
    public String viewEngProject(Model model, @PathVariable(value = "id") Integer projectId) {
        Project project = projectService.getByProjectId(projectId);

        HashMap<Integer, String> pmMaps = pmService.getIdAndName();

        List<Engineer> engineers = engineerService.findAll();

        List<Integer> teamIds = teamService.getTeamIdsByProjectId(projectId);

        Integer role = securityService.getRole();

        model.addAttribute("project", project);
        model.addAttribute("role", role);
        model.addAttribute("pmMaps", pmMaps);
        model.addAttribute("engineers", engineers);
        model.addAttribute("teamIds", teamIds);
        return "engineer/addProject";
    }

}
 

