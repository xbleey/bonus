/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: LoginController
 * Author:   11580
 * Date:     2019/6/14 0014 17:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/14 0014
 * @since 1.0.0
 */
@Controller
public class LoginController {
    @GetMapping(value = "/engineer/{path}")
    public String goEngineer(Model model, @PathVariable(value = "path") String path) {
        return "/engineer/"+ path;
    }

}
 

