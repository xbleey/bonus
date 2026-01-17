/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TeamController
 * Author:   11580
 * Date:     2019/6/19 0019 21:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.Team;
import com.xbleey.service.PmService;
import com.xbleey.service.ProjectService;
import com.xbleey.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
@Controller
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    ProjectService projectService;
    @Autowired
    PmService pmService;

    @GetMapping(value = "/allTeam")
    public String allTeam(Model model, Map map) {
        List<Team> distinctTeams = teamService.findDistinctTeam();
        model.addAttribute("teams", distinctTeams);

        Map<Integer, String> projectNames = projectService.getIdAndName();
        model.addAttribute("projectNames", projectNames);

        HashMap<Integer, String> pmNames = pmService.getIdAndName();
        model.addAttribute("pmNames", pmNames);
        return "team/allTeam";
    }
}
 

