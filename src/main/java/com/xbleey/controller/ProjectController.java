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

import com.xbleey.entity.Engineer;
import com.xbleey.entity.Project;
import com.xbleey.service.EngineerService;
import com.xbleey.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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


    @GetMapping(value = "/pm/addProject")
    public String goProject(Model model) {
        List<Engineer> engineers = engineerService.findAll();
        model.addAttribute("engineers", engineers);
        return "pm/addProject";
    }

    @PostMapping(value = "/pm/addProject")
    public String addProject(Model model, Project project, @RequestParam(value = "EngineerId") String[] engineerId) {
        System.out.println(engineerId);
        if(project.getProjectStartMoney()!=null){

            return "redirect:/allTeam";
        }
        else {
            List<Engineer> engineers = engineerService.findAll();
            model.addAttribute("engineers", engineers);
            model.addAttribute("moneyMessage","没填写项目预算  ");
            return "pm/addProject";
        }
    }
}
 

