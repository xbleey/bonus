package com.xbleey;

import com.xbleey.dao.BossDao;
import com.xbleey.entity.Boss;
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
public class BossTests {

    @Autowired
    BossDao bossDao;

    Logger logger = LoggerFactory.getLogger(BossTests.class);

    @Test
    public void bossSelectTest() {
        List<Boss> bosses = bossDao.findAll();
        logger.info(bosses.toString());
    }

}
