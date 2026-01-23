package com.xbleey.service;

import com.xbleey.dao.AdminDao;
import com.xbleey.dao.EngineerDao;
import com.xbleey.entity.Admin;
import com.xbleey.entity.Boss;
import com.xbleey.entity.Director;
import com.xbleey.entity.Engineer;
import com.xbleey.entity.ProjectManager;
import com.xbleey.entity.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    @Mock
    private AdminDao adminDao;

    @Mock
    private EngineerDao engineerDao;

    @Mock
    private PmService pmService;

    @Mock
    private DirectorService directorService;

    @Mock
    private BossService bossService;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInit() {
        // Mock Admin data
        Admin admin = new Admin();
        admin.setAdminUser("adminUser");
        admin.setAdminPass("adminPass");
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);
        when(adminDao.findAll()).thenReturn(admins);

        // Mock Boss data
        Boss boss = new Boss();
        boss.setBossUser("bossUser");
        boss.setBossPass("bossPass");
        List<Boss> bosses = new ArrayList<>();
        bosses.add(boss);
        when(bossService.findAll()).thenReturn(bosses);

        // Mock Director data
        Director director = new Director();
        director.setDirectorUser("directorUser");
        director.setDirectorPass("directorPass");
        List<Director> directors = new ArrayList<>();
        directors.add(director);
        when(directorService.findAll()).thenReturn(directors);

        // Mock ProjectManager data
        ProjectManager pm = new ProjectManager();
        pm.setPmUser("pmUser");
        pm.setPmPass("pmPass");
        List<ProjectManager> pms = new ArrayList<>();
        pms.add(pm);
        when(pmService.findAll()).thenReturn(pms);

        // Mock Engineer data
        Engineer engineer = new Engineer();
        engineer.setEngineerUser("engineerUser");
        engineer.setEngineerPass("engineerPass");
        List<Engineer> engineers = new ArrayList<>();
        engineers.add(engineer);
        when(engineerDao.findAll()).thenReturn(engineers);

        // Execute init
        loginService.init();

        // Verify results
        List<UserInfo> userInfos = loginService.getUserInfos();
        assertNotNull(userInfos);
        assertEquals(5, userInfos.size());

        // Verify Admin
        UserInfo adminInfo = userInfos.stream()
                .filter(u -> "adminUser".equals(u.getUserName()))
                .findFirst()
                .orElse(null);
        assertNotNull(adminInfo);
        assertEquals("adminPass", adminInfo.getPassWord());
        assertEquals(5, adminInfo.getRole().length);

        // Verify Boss
        UserInfo bossInfo = userInfos.stream()
                .filter(u -> "bossUser".equals(u.getUserName()))
                .findFirst()
                .orElse(null);
        assertNotNull(bossInfo);
        assertEquals("bossPass", bossInfo.getPassWord());
        assertEquals(4, bossInfo.getRole().length);

        // Verify Director
        UserInfo directorInfo = userInfos.stream()
                .filter(u -> "directorUser".equals(u.getUserName()))
                .findFirst()
                .orElse(null);
        assertNotNull(directorInfo);
        assertEquals("directorPass", directorInfo.getPassWord());
        assertEquals(3, directorInfo.getRole().length);

        // Verify ProjectManager
        UserInfo pmInfo = userInfos.stream()
                .filter(u -> "pmUser".equals(u.getUserName()))
                .findFirst()
                .orElse(null);
        assertNotNull(pmInfo);
        assertEquals("pmPass", pmInfo.getPassWord());
        assertEquals(2, pmInfo.getRole().length);

        // Verify Engineer
        UserInfo engineerInfo = userInfos.stream()
                .filter(u -> "engineerUser".equals(u.getUserName()))
                .findFirst()
                .orElse(null);
        assertNotNull(engineerInfo);
        assertEquals("engineerPass", engineerInfo.getPassWord());
        assertEquals(1, engineerInfo.getRole().length);
    }

    @Test
    void testInitWithEmptyData() {
        when(adminDao.findAll()).thenReturn(new ArrayList<>());
        when(bossService.findAll()).thenReturn(new ArrayList<>());
        when(directorService.findAll()).thenReturn(new ArrayList<>());
        when(pmService.findAll()).thenReturn(new ArrayList<>());
        when(engineerDao.findAll()).thenReturn(new ArrayList<>());

        loginService.init();

        List<UserInfo> userInfos = loginService.getUserInfos();
        assertNotNull(userInfos);
        assertTrue(userInfos.isEmpty());
    }

    @Test
    void testInitWithNullData() {
        when(adminDao.findAll()).thenReturn(null);
        when(bossService.findAll()).thenReturn(null);
        when(directorService.findAll()).thenReturn(null);
        when(pmService.findAll()).thenReturn(null);
        when(engineerDao.findAll()).thenReturn(null);

        loginService.init();

        List<UserInfo> userInfos = loginService.getUserInfos();
        assertNotNull(userInfos);
        assertTrue(userInfos.isEmpty());
    }

    @Test
    void testDestroy() {
        // Setup some data first
        Admin admin = new Admin();
        admin.setAdminUser("adminUser");
        admin.setAdminPass("adminPass");
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);
        when(adminDao.findAll()).thenReturn(admins);

        loginService.init();
        assertEquals(1, loginService.getUserInfos().size());

        loginService.destroy();
        assertTrue(loginService.getUserInfos().isEmpty());
    }
}
