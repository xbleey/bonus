/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: PmController
 * Author:   11580
 * Date:     2019/6/23 0023 14:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.Engineer;
import com.xbleey.entity.Project;
import com.xbleey.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
public class PmController {
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

    @GetMapping(value = "/pm/addProject")
    public String addProject(Model model) {
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        return "pm/addProject";
    }

    @PostMapping(value = "/pm/addProject")
    public String saveProject(Model model, Project project, @RequestParam(value = "EngineerId") Integer[] engineerId) {
        if (project.getProjectStartMoney() != null) {

            project.setProjectStatus("未提交");
            projectService.saveProject(project);
            if (engineerId.length == 0) {
                teamService.saveNoEngineerTeam(project.getProjectId(), project.getProjectPmId());
            } else {
                teamService.saveEngineerTeam(project.getProjectId(), project.getProjectPmId(), engineerId);
            }
            return "redirect:/projects";
        } else {
            List<Engineer> engineers = engineerService.findAll();
            model.addAttribute("engineers", engineers);
            model.addAttribute("moneyMessage", "没填写项目预算  ");
            return "pm/addProject";
        }
    }

    @GetMapping(value = "/pm/projects")
    public String pmProject(Model model) {
        Integer pmId = pmService.getPmByPmUser(securityService.getUserName()).getPmId();
        List<Project> myProjects = projectService.findAllByPmId(pmId);

        HashMap<String,String> names = new HashMap<>();
        names.put("unFinProjects","未提交");
        names.put("runProjects","总经理过审");
        projectService.classifyProject(model,myProjects,names);

        myProjects.removeAll(projectService.classifyProjectByStatus(myProjects, "未提交"));
        myProjects.removeAll(projectService.classifyProjectByStatus(myProjects, "总经理过审"));

        List<Project> submittedProjects = new ArrayList<>(myProjects);

        model.addAttribute("submittedProjects", submittedProjects);
        return "pm/projects";
    }

    @PutMapping(value = "/pm/addProject")
    public String submitProject(Project project) {
        project.setProjectStatus("审核中");
        projectService.updateStatus(project.getProjectStatus(), project.getProjectId(),project.getDirUnPassInfo());
        return "redirect:/pm/projects";
    }
}
 

