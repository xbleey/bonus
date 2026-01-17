package com.xbleey.service;

import com.xbleey.dao.BossDao;
import com.xbleey.entity.Boss;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BossServiceTest {

    @Mock
    private BossDao bossDao;

    @InjectMocks
    private BossService bossService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Boss> bosses = new ArrayList<>();
        bosses.add(new Boss());
        when(bossDao.findAll()).thenReturn(bosses);

        List<Boss> result = bossService.findAll();
        assertEquals(1, result.size());
    }
}
