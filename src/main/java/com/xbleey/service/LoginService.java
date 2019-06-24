/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: LoginService
 * Author:   11580
 * Date:     2019/6/17 0017 14:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dao.AdminDao;
import com.xbleey.dao.EngineerDao;
import com.xbleey.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/17 0017
 * @since 1.0.0
 */
@Service
public class LoginService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    EngineerDao engineerDao;
    @Autowired
    PmService pmService;
    @Autowired
    DirectorService directorService;
    @Autowired
    BossService bossService;

    private ArrayList<UserInfo> userInfos = new ArrayList<>();

    public void init() {
        //加载Dao层
        List<Admin> admins = adminDao.findAll();
        List<Engineer> engineers = engineerDao.findAll();
        List<Pm> pms = pmService.findAll();
        List<Director> directors = directorService.findAll();
        List<Boss> bosses = bossService.findAll();

        //导入ArrayList
        for (Admin a : admins) {
            userInfos.add(new UserInfo(a.getAdminUser(), a.getAdminPass(), new String[]{"admin","engineer", "pm", "director", "boss"}));
        }
        for (Boss b : bosses) {
            userInfos.add(new UserInfo(b.getBossUser(), b.getBossPass(), new String[]{"engineer", "pm", "director", "boss"}));
        }
        for (Director d : directors) {
            userInfos.add(new UserInfo(d.getDirectorUser(), d.getDirectorPass(), new String[]{"engineer", "pm", "director"}));
        }
        for (Pm p : pms) {
            userInfos.add(new UserInfo(p.getPmUser(), p.getPmPass(), new String[]{"engineer", "pm"}));
        }
        for (Engineer e : engineers) {
            userInfos.add(new UserInfo(e.getEngineerUser(), e.getEngineerPass(), new String[]{"engineer"}));
        }

    }

    public ArrayList<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void destroy() {
        userInfos.clear();
    }
}
 

