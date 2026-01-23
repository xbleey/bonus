package com.xbleey.service;

import com.xbleey.dao.DirectorDao;
import com.xbleey.entity.Director;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DirectorServiceTest {

    @Mock
    private DirectorDao directorDao;

    @InjectMocks
    private DirectorService directorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Director> directors = new ArrayList<>();
        directors.add(new Director());
        when(directorDao.findAll()).thenReturn(directors);

        List<Director> result = directorService.findAll();
        assertEquals(1, result.size());
    }
}
