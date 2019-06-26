/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TeamDao
 * Author:   11580
 * Date:     2019/6/18 0018 23:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.dao;

import com.xbleey.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/18 0018
 * @since 1.0.0e
 */
public interface TeamDao extends JpaRepository<Team, Integer> {
    public List<Team> findAllByEngineerId(Integer engineerId);

    public List<Team> findAllByProjectId(Integer engineerId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update team e set engineer_money=?1 where engineer_id=?2 and project_id=?3", nativeQuery = true)
    public int updateStatus(Integer engineerMoney, Integer engineerId, Integer projectId);
}