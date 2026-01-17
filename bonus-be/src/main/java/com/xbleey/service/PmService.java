/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EngineerService
 * Author:   11580
 * Date:     2019/6/16 0016 18:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dao.PmDao;
import com.xbleey.entity.ProjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/16 0016
 * @since 1.0.0
 */
@Service
public class PmService {
    @Autowired
    PmDao pmDao;

    public List<ProjectManager> findAll() {
        return pmDao.findAll();
    }

    public HashMap<Integer, String> getIdAndName() {
        List<ProjectManager> projectManagers = pmDao.findAll();
        HashMap<Integer, String> pmNames = new HashMap<>();
        for (ProjectManager p : projectManagers) {
            pmNames.put(p.getPmId(), p.getPmName());
        }
        return pmNames;
    }

    public ProjectManager getPmByPmUser(String pmUser) {
        return pmDao.getFirstByPmUser(pmUser);
    }
}
 

