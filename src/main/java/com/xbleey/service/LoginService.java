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
import com.xbleey.entity.Engineer;
import com.xbleey.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<UserInfo> userInfos = new ArrayList<>();

    @Autowired
    AdminDao adminDao;
    @Autowired
    EngineerDao engineerDao;

    public void init(){
        //加载Dao层
        List<Admin> admins =adminDao.findAll();
        List<Engineer> engineers = engineerDao.findAll();

        //导入ArrayList
        for(Admin a:admins){
            userInfos.add(new UserInfo(a.getAdminUser(),a.getAdminPass(),"admin"));
        }
        for(Engineer e:engineers){
            userInfos.add(new UserInfo(e.getEngineerUser(),e.getEngineerPass(),"engineer"));
        }
    }

    public ArrayList<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void destroy(){
        userInfos.clear();
    }
}
 

