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
import com.xbleey.entity.Admin;
import com.xbleey.entity.Boss;
import com.xbleey.entity.Director;
import com.xbleey.entity.Engineer;
import com.xbleey.entity.ProjectManager;
import com.xbleey.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2025/12/30 11:29
 * @since 2.0.0
 */
@Service
public class LoginService {

    private final AdminDao adminDao;
    private final EngineerDao engineerDao;
    private final PmService pmService;
    private final DirectorService directorService;
    private final BossService bossService;

    private final ArrayList<UserInfo> userInfos = new ArrayList<>();

    @Autowired
    public LoginService(AdminDao adminDao, EngineerDao engineerDao,
                        PmService pmService, DirectorService directorService,
                        BossService bossService) {
        this.adminDao = adminDao;
        this.engineerDao = engineerDao;
        this.pmService = pmService;
        this.directorService = directorService;
        this.bossService = bossService;
    }


    public void init() {
        // Clear existing data first
        userInfos.clear();

        try {
            // Load data with null safety
            loadAdmins();
            loadBosses();
            loadDirectors();
            loadProjectManagers();
            loadEngineers();

        } catch (Exception e) {
            userInfos.clear();
            throw new RuntimeException("Failed to initialize LoginService", e);
        }
    }

    private void loadAdmins() {
        List<Admin> admins = adminDao != null ? adminDao.findAll() : null;
        if (admins != null) {
            for (Admin admin : admins) {
                if (admin != null && isValidCredentials(admin.getAdminUser(), admin.getAdminPass())) {
                    userInfos.add(new UserInfo(
                            admin.getAdminUser(),
                            admin.getAdminPass(),
                            new String[]{"admin", "engineer", "pm", "director", "boss"}
                    ));
                }
            }
        }
    }

    private void loadBosses() {
        List<Boss> bosses = bossService != null ? bossService.findAll() : null;
        if (bosses != null) {
            for (Boss boss : bosses) {
                if (boss != null && isValidCredentials(boss.getBossUser(), boss.getBossPass())) {
                    userInfos.add(new UserInfo(
                            boss.getBossUser(),
                            boss.getBossPass(),
                            new String[]{"engineer", "pm", "director", "boss"}
                    ));
                }
            }
        }
    }

    private void loadDirectors() {
        List<Director> directors = directorService != null ? directorService.findAll() : null;
        if (directors != null) {
            for (Director director : directors) {
                if (director != null && isValidCredentials(director.getDirectorUser(), director.getDirectorPass())) {
                    userInfos.add(new UserInfo(
                            director.getDirectorUser(),
                            director.getDirectorPass(),
                            new String[]{"engineer", "pm", "director"}
                    ));
                }
            }
        }
    }

    private void loadProjectManagers() {
        List<ProjectManager> projectManagers = pmService != null ? pmService.findAll() : null;
        if (projectManagers != null) {
            for (ProjectManager pm : projectManagers) {
                if (pm != null && isValidCredentials(pm.getPmUser(), pm.getPmPass())) {
                    userInfos.add(new UserInfo(
                            pm.getPmUser(),
                            pm.getPmPass(),
                            new String[]{"engineer", "pm"}
                    ));
                }
            }
        }
    }

    private void loadEngineers() {
        List<Engineer> engineers = engineerDao != null ? engineerDao.findAll() : null;
        if (engineers != null) {
            for (Engineer engineer : engineers) {
                if (engineer != null && isValidCredentials(engineer.getEngineerUser(), engineer.getEngineerPass())) {
                    userInfos.add(new UserInfo(
                            engineer.getEngineerUser(),
                            engineer.getEngineerPass(),
                            new String[]{"engineer"}
                    ));
                }
            }
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return username != null && !username.trim().isEmpty() &&
                password != null && !password.trim().isEmpty();
    }

    public List<UserInfo> getUserInfos() {
        return new ArrayList<>(userInfos);
    }

    public void destroy() {
        userInfos.clear();
    }
}
 

