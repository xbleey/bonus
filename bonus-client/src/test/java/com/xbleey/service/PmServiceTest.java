package com.xbleey.service;

import com.xbleey.dao.PmDao;
import com.xbleey.entity.ProjectManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class PmServiceTest {

    @Mock
    private PmDao pmDao;

    @InjectMocks
    private PmService pmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<ProjectManager> pms = new ArrayList<>();
        pms.add(new ProjectManager());
        when(pmDao.findAll()).thenReturn(pms);

        List<ProjectManager> result = pmService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetIdAndName() {
        ProjectManager pm1 = new ProjectManager();
        pm1.setPmId(1);
        pm1.setPmName("PM1");
        ProjectManager pm2 = new ProjectManager();
        pm2.setPmId(2);
        pm2.setPmName("PM2");

        List<ProjectManager> pms = new ArrayList<>();
        pms.add(pm1);
        pms.add(pm2);

        when(pmDao.findAll()).thenReturn(pms);

        HashMap<Integer, String> result = pmService.getIdAndName();
        assertEquals(2, result.size());
        assertEquals("PM1", result.get(1));
        assertEquals("PM2", result.get(2));
    }

    @Test
    void testGetPmByPmUser() {
        ProjectManager pm = new ProjectManager();
        pm.setPmUser("user1");
        when(pmDao.getFirstByPmUser("user1")).thenReturn(pm);

        ProjectManager result = pmService.getPmByPmUser("user1");
        assertNotNull(result);
        assertEquals("user1", result.getPmUser());
    }
}
