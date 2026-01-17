/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: BossController
 * Author:   11580
 * Date:     2019/6/25 0025 21:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.Project;
import com.xbleey.entity.ProjectStatus;
import com.xbleey.service.PmService;
import com.xbleey.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/25 0025
 * @since 1.0.0
 */
@Controller
public class BossController {
    @Autowired
    ProjectService projectService;
    @Autowired
    PmService pmService;

    @GetMapping(value = "/boss/projects")
    public String goProjects(Model model) {
        List<Project> projects = projectService.findAllProject();
        HashMap<String, ProjectStatus> names = new HashMap<>();
        names.put("submittedProjects", ProjectStatus.DIRECTOR_APPROVED);
        names.put("bossPassProject", ProjectStatus.BOSS_APPROVED);
        names.put("bossUnPassProject", ProjectStatus.BOSS_REJECTED);
        projectService.classifyProject(model, projects, names);
        return "boss/projects";
    }

    @PostMapping(value = "boss/addProject")
    public String bossPassPro(Project project) {
        projectService.transitionStatus(project.getId(), ProjectStatus.BOSS_APPROVED, null, null);
        return "redirect:/boss/projects";
    }

    @DeleteMapping(value = "boss/addProject")
    public String bossUnPassPro(Project project) {
        projectService.transitionStatus(project.getId(), ProjectStatus.BOSS_REJECTED, null, project.getBossUnPassInfo());
        return "redirect:/boss/projects";
    }
}
 

