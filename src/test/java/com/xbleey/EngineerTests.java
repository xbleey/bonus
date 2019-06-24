package com.xbleey;

import com.xbleey.dao.AdminDao;
import com.xbleey.dao.EngineerDao;
import com.xbleey.entity.Admin;
import com.xbleey.entity.Engineer;
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
public class EngineerTests {

    @Autowired
    EngineerDao engineerDao;
    @Autowired
    EngineerService engineerService;

    Logger logger = LoggerFactory.getLogger(EngineerTests.class);

    @Test
    public void engineerSelectTest() {
        List<Engineer> engineers = engineerDao.findAll();
        logger.info(String.valueOf(engineers.size()));
    }

    @Test
    public void engineerLoginTest() {
        Boolean isEngineer = engineerService.login("Apple","123");
        logger.info(String.valueOf(isEngineer));
    }

    @Test
    public void engineerNameTest() {
        Engineer engineer = engineerService.findDistinctByEngineerUser("apple");
        logger.info(engineer.toString());
    }


}
