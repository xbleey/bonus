package com.xbleey;

import com.xbleey.dao.TeamDao;
import com.xbleey.entity.Team;
import com.xbleey.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamTests {

    @Autowired
    TeamDao teamDao;
    @Autowired
    TeamService teamService;

    Logger logger = LoggerFactory.getLogger(TeamTests.class);


    @Test
    public void pssSelectTest() {
        Team t1 = new Team();
        t1.setTrueId(1);
        Team t2 = new Team();
        t2.setTrueId(1);
        Team t3 = new Team();
        t3.setTrueId(2);
        logger.info(String.valueOf(t1.equals(t2)));
        logger.info(String.valueOf(t1.equals(t3)));
        logger.info(String.valueOf(t2.equals(t3)));
    }

    @Test
    public void distinctTest() {
        List<Team> teams =teamService.FindDistinctTeam();
        logger.info(teams.toString());
    }
    @Test
    public void topTest() {
        Team team =teamService.findTopTeamByTrueId();
        logger.info(team.toString());
    }


}
