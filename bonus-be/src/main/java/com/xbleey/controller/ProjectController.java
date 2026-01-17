/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ProjectController
 * Author:   11580
 * Date:     2019/6/20 0020 22:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.service.EngineerService;
import com.xbleey.service.PmService;
import com.xbleey.service.ProjectService;
import com.xbleey.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/20 0020
 * @since 1.0.0
 */
@Controller
public class ProjectController {
    @Autowired
    EngineerService engineerService;
    @Autowired
    TeamService teamService;
    @Autowired
    ProjectService projectService;
    @Autowired
    PmService pmService;


    @GetMapping(value = "/projects")
    public String redirectProject() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getAuthorities().size() == 1) {
            return "redirect:/engineer/projects";
        }
        if (userDetails.getAuthorities().size() == 2) {
            return "redirect:/pm/projects";
        }
        if (userDetails.getAuthorities().size() == 3) {
            return "redirect:/director/projects";
        }
        if (userDetails.getAuthorities().size() == 4) {
            return "redirect:/boss/projects";
        }
        if (userDetails.getAuthorities().size() == 5) {
            return "redirect:/admin/projects";
        }
        return "index";
    }


}



