/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: WelfareController
 * Author:   11580
 * Date:     2019/6/26 0026 16:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.Welfare;
import com.xbleey.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/26 0026
 * @since 1.0.0
 */
@Controller
public class WelfareController {
    @Autowired
    WelfareService welfareService;

    @PostMapping(value = "/pm/addWelfare")
    public String addWelfare(Welfare welfare){
        welfareService.saveOneWelfare(welfare);
        return "redirect:/engineer/projects/"+String.valueOf(welfare.getWelfareProjectId());
    }
}
 

