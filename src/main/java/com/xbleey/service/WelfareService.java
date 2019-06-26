/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: WelfareService
 * Author:   11580
 * Date:     2019/6/26 0026 16:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dao.WelfareDao;
import com.xbleey.entity.Welfare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/26 0026
 * @since 1.0.0
 */
@Service
public class WelfareService {
    @Autowired
    WelfareDao welfareDao;

    public List<Welfare> findAll() {
        return welfareDao.findAll();
    }

    public void saveOneWelfare(Welfare welfare) {
        welfareDao.save(welfare);
    }

    public List<Welfare> findAllByProjectId(Integer projectId) {
        return welfareDao.findAllByWelfareProjectId(projectId);
    }
}
 

