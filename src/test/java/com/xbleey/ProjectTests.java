package com.xbleey;

import com.xbleey.entity.Project;
import com.xbleey.service.ProjectService;
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
public class ProjectTests {

    @Autowired
    ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(ProjectTests.class);


    @Test
    public void projectSelectTest() {
        List<Project> projects = projectService.classifyProjectByStatus(projectService.findAllByPmId(1), "审核中");
        logger.info(projects.toString());
    }

    @Test
    public void findAllTest() {
        List<Project> projects = projectService.findAllProject();
        logger.info(projects.toString());
    }



}
