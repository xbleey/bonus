package com.xbleey;

import com.xbleey.dao.DirectorDao;
import com.xbleey.dao.PmDao;
import com.xbleey.entity.Director;
import com.xbleey.entity.Pm;
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
public class DirectorTests {

    @Autowired
    DirectorDao directorDao;

    Logger logger = LoggerFactory.getLogger(DirectorTests.class);

    @Test
    public void directorSelectTest() {
        List<Director> directors = directorDao.findAll();
        logger.info(directors.toString());
    }

}
