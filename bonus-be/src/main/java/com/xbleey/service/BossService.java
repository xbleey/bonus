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

import com.xbleey.dao.BossDao;
import com.xbleey.entity.Boss;
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
public class BossService {
    @Autowired
    BossDao bossDao;

    public List<Boss> findAll() {
        return bossDao.findAll();
    }

    public Boss findById(Integer id) {
        return bossDao.findById(id).orElse(null);
    }

    public Boss addBoss(Boss boss) {
        return bossDao.save(boss);
    }

    public Boss updateBoss(Boss boss) {
        if (boss.getId() == null) {
            return null;
        }
        return bossDao.save(boss);
    }

    public void deleteBoss(Integer id) {
        if (id == null) {
            return;
        }
        bossDao.deleteById(id);
    }
}
 

