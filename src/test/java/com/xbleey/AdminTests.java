package com.xbleey;

import com.xbleey.dao.AdminDao;
import com.xbleey.entity.Admin;
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
public class AdminTests {

    @Autowired
    AdminDao adminDao;

    Logger logger = LoggerFactory.getLogger(AdminTests.class);

    @Test
    public void adminTest() {
        List<Admin> admins = adminDao.findByAdminUser("admin");
        logger.info(admins.get(0).toString());
    }

}
