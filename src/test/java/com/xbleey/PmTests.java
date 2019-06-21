package com.xbleey;

import com.xbleey.dao.EngineerDao;
import com.xbleey.dao.PmDao;
import com.xbleey.dao.ProjectDao;
import com.xbleey.entity.Engineer;
import com.xbleey.entity.Pm;
import com.xbleey.entity.Project;
import com.xbleey.service.EngineerService;
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
public class PmTests {

    @Autowired
    PmDao pmDao;
    @Autowired
    ProjectDao projectDao;

    Logger logger = LoggerFactory.getLogger(PmTests.class);

    @Test
    public void pmSelectTest() {
        List<Pm> pms = pmDao.findAll();
        logger.info(pms.toString());
    }

    @Test
    public void pssSelectTest() {
        Project project1 = new Project();
        project1.setProjectId(1);
        project1.setProjectName("123");
        Project project2 = new Project();
        project2.setProjectId(1);
        project2.setProjectName("123");
        logger.info(String.valueOf(project1.equals(project2)));
    }

}
