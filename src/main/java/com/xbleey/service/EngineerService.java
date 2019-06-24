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

import com.xbleey.dao.EngineerDao;
import com.xbleey.entity.Engineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class EngineerService {
    @Autowired
    EngineerDao engineerDao;

    public Boolean login(String userName, String passWord) {
        Boolean isEngineerUser = engineerDao.existsAllByEngineerUserAndEngineerPass(userName, passWord);
        return isEngineerUser;
    }

    public List<Engineer> findAll() {
        return engineerDao.findAll();
    }

    public Engineer findDistinctByEngineerUser(String engineerUser) {
        return engineerDao.findFirstByEngineerUser(engineerUser);
    }
}
 

