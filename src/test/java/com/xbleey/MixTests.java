package com.xbleey;

import com.xbleey.entity.Engineer;
import com.xbleey.entity.Team;
import com.xbleey.service.EngineerService;
import com.xbleey.service.PmService;
import com.xbleey.service.ProjectService;
import com.xbleey.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MixTests {

    @Autowired
    EngineerService engineerService;
    @Autowired
    TeamService teamService;
    @Autowired
    ProjectService projectService;
    @Autowired
    PmService pmService;

    Logger logger = LoggerFactory.getLogger(MixTests.class);


    @Test
    public void containTest() {
        List<Engineer> engineers = engineerService.findAll();
        List<Team> teams = teamService.findByProjectId(3);

        List<Integer> teamIds = new ArrayList<>();
        for (Team t : teams) {
            teamIds.add(t.getEngineerId());
        }
        for (Engineer e : engineers) {
            logger.info(String.valueOf(teamIds.contains(e.getEngineerId())));
        }
    }
}
