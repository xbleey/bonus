/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: DirectorController
 * Author:   11580
 * Date:     2019/6/24 0024 13:51
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
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/24 0024
 * @since 1.0.0
 */
@Controller
public class DirectorController {
    @Autowired
    ProjectService projectService;
    @Autowired
    PmService pmService;

    @GetMapping(value = "/director/projects")
    public String goProjects(Model model) {
        List<Project> projects = projectService.findAllProject();
        HashMap<String, ProjectStatus> names = new HashMap<>();
        names.put("submittedProjects", ProjectStatus.UNDER_REVIEW);
        names.put("dirPassProject", ProjectStatus.DIRECTOR_APPROVED);
        names.put("dirUnPassProject", ProjectStatus.DIRECTOR_REJECTED);
        projectService.classifyProject(model, projects, names);
        return "director/projects";
    }

    @DeleteMapping(value = "/pm/addProject")
    public String passProjectDir(Model model, Project project) {
        projectService.transitionStatus(project.getId(), ProjectStatus.DIRECTOR_APPROVED, null, null);
        return "redirect:/director/projects";
    }

    @PatchMapping(value = "/pm/addProject")
    public String unPassProjectDir(Model model, Project project) {
        projectService.transitionStatus(project.getId(), ProjectStatus.DIRECTOR_REJECTED, project.getDirUnPassInfo(), null);
        return "redirect:/director/projects";
    }
}
 

