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


    public List<Team> findDistinctTeam() {
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

    public void saveNoEngineerTeam(Integer projectId, Integer pmId) {
        int trueId = findTopTeamByTrueId().getTrueId() + 1;
        Team team = new Team();
        team.setTrueId(trueId);
        team.setProjectId(projectId);
        team.setPmId(pmId);


        teamDao.save(team);
    }

    public void saveEngineerTeam(Integer projectId, Integer pmId, Integer[] engineerId) {
        int trueId = findTopTeamByTrueId().getTrueId() + 1;
        for (Integer integer : engineerId) {
            Team team = new Team();
            team.setTrueId(trueId);
            team.setProjectId(projectId);
            team.setPmId(pmId);
            team.setEngineerId(integer);

            teamDao.save(team);
        }
    }

    public List<Team> findByProjectId(Integer projectId) {
        return teamDao.findAllByProjectId(projectId);
    }

    public List<Integer> getTeamIdsByProjectId(Integer projectId) {
        List<Team> teams = findByProjectId(projectId);
        List<Integer> teamIds = new ArrayList<>();
        for (Team t : teams) {
            teamIds.add(t.getEngineerId());
        }
        return teamIds;
    }
}
 

