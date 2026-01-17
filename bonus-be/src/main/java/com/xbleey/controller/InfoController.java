/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: InfoController
 * Author:   11580
 * Date:     2019/6/19 0019 12:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.Boss;
import com.xbleey.entity.Director;
import com.xbleey.entity.Engineer;
import com.xbleey.entity.ProjectManager;
import com.xbleey.service.BossService;
import com.xbleey.service.DirectorService;
import com.xbleey.service.EngineerService;
import com.xbleey.service.PmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/19 0019
 * @since 1.0.0
 */
@Controller
public class InfoController {
    @Autowired
    BossService bossService;
    @Autowired
    PmService pmService;
    @Autowired
    DirectorService directorService;
    @Autowired
    EngineerService engineerService;


    @GetMapping(value = "/allInfo")
    public String allInfo(Model model) {
        List<Boss> bosses = bossService.findAll();
        List<Director> directors = directorService.findAll();
        List<ProjectManager> projectManagers = pmService.findAll();
        List<Engineer> engineers = engineerService.findAll();

        model.addAttribute("bosses", bosses);
        model.addAttribute("directors", directors);
        model.addAttribute("pms", projectManagers);
        model.addAttribute("engineers", engineers);
        return "infos/allInfo";
    }
}
 

