package com.xbleey.service;

import com.xbleey.dao.EngineerDao;
import com.xbleey.entity.Engineer;
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

class EngineerServiceTest {

    @Mock
    private EngineerDao engineerDao;

    @InjectMocks
    private EngineerService engineerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        when(engineerDao.existsAllByEngineerUserAndEngineerPass("user", "pass")).thenReturn(true);
        Boolean result = engineerService.login("user", "pass");
        assertTrue(result);
    }

    @Test
    void testFindAll() {
        List<Engineer> engineers = new ArrayList<>();
        engineers.add(new Engineer());
        when(engineerDao.findAll()).thenReturn(engineers);

        List<Engineer> result = engineerService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindDistinctByEngineerUser() {
        Engineer engineer = new Engineer();
        engineer.setEngineerUser("user");
        when(engineerDao.findFirstByEngineerUser("user")).thenReturn(engineer);

        Engineer result = engineerService.findDistinctByEngineerUser("user");
        assertNotNull(result);
        assertEquals("user", result.getEngineerUser());
    }
}
