/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: AdminDao
 * Author:   11580
 * Date:     2019/6/14 0014 16:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.dao;

import com.xbleey.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/14 0014
 * @since 1.0.0
 */
public interface AdminDao extends JpaRepository<Admin,Integer> {
    public List<Admin> findByAdminUser(String adminUser);
}
 

