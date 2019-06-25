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
        HashMap<String,String> names = new HashMap<>();
        names.put("submittedProjects","审核中");
        names.put("dirPassProject","总管过审");
        names.put("dirUnPassProject","总管拒绝通过");
        projectService.classifyProject(model,projects,names);
        return "director/projects";
    }

    @DeleteMapping(value = "/pm/addProject")
    public String passProjectDir(Model model,Project project){
        project.setProjectStatus("总管过审");
        projectService.updateStatus(project.getProjectStatus(), project.getProjectId(),null);
        return "redirect:/director/projects";
    }

    @PatchMapping(value = "/pm/addProject")
    public String unPassProjectDir(Model model,Project project){
        project.setProjectStatus("总管拒绝通过");
        projectService.updateStatus(project.getProjectStatus(), project.getProjectId(),project.getDirUnPassInfo());
        return "redirect:/director/projects";
    }
}
 

