/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: TeamService
 * Author:   11580
 * Date:     2019/6/20 0020 9:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dao.TeamDao;
import com.xbleey.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/20 0020
 * @since 1.0.0
 */
@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;


    public List<Team> FindDistinctTeam() {
        List<Team> teams = teamDao.findAll();

        ArrayList<Team> distinctTeams = new ArrayList<>();
        Team oneTeam = new Team();
        for (Team t : teams) {
            if (!t.equals(oneTeam)) {
                distinctTeams.add(t);
            }
            oneTeam = t;
        }
        return distinctTeams;
    }

    public Team findTopTeamByTrueId() {
        List<Team> teams = teamDao.findAll();
        Team team = new Team();
        if (!teams.isEmpty()) {
            team = teams.get(teams.size() - 1);
        }
        return team;
    }
}
 

