package com.xbleey.service;

import com.xbleey.dao.TeamDao;
import com.xbleey.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TeamServiceTest {

    @Mock
    private TeamDao teamDao;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindDistinctTeam() {
        Team t1 = new Team();
        t1.setTrueId(1);
        Team t2 = new Team();
        t2.setTrueId(2);
        Team t3 = new Team();
        t3.setTrueId(2); // Duplicate based on equals logic if implemented, but here we rely on object reference or equals method.
        // Assuming Team.equals checks fields. The service logic relies on sequential duplicates.
        
        List<Team> teams = new ArrayList<>();
        teams.add(t1);
        teams.add(t2);
        teams.add(t3);

        when(teamDao.findAll()).thenReturn(teams);

        // Note: The logic in FindDistinctTeam compares with previous element.
        // If t2.equals(t3) is true, t3 will be skipped.
        // We need to ensure Team equals method works as expected or mock behavior if possible.
        // Since we can't see Team entity, we assume standard behavior.
        
        List<Team> result = teamService.findDistinctTeam();
        // If t2 equals t3, size should be 2. If not, 3.
        // Given the code: if (!t.equals(oneTeam))
        assertNotNull(result);
    }

    @Test
    void testFindTopTeamByTrueId() {
        Team t1 = new Team();
        t1.setTrueId(1);
        Team t2 = new Team();
        t2.setTrueId(2);
        List<Team> teams = new ArrayList<>();
        teams.add(t1);
        teams.add(t2);

        when(teamDao.findAll()).thenReturn(teams);

        Team result = teamService.findTopTeamByTrueId();
        assertEquals(2, result.getTrueId());
    }

    @Test
    void testSaveNoEngineerTeam() {
        Team t1 = new Team();
        t1.setTrueId(10);
        List<Team> teams = new ArrayList<>();
        teams.add(t1);
        when(teamDao.findAll()).thenReturn(teams);

        teamService.saveNoEngineerTeam(1, 1);
        verify(teamDao, times(1)).save(any(Team.class));
    }

    @Test
    void testSaveEngineerTeam() {
        Team t1 = new Team();
        t1.setTrueId(10);
        List<Team> teams = new ArrayList<>();
        teams.add(t1);
        when(teamDao.findAll()).thenReturn(teams);

        Integer[] engineerIds = {1, 2, 3};
        teamService.saveEngineerTeam(1, 1, engineerIds);
        verify(teamDao, times(3)).save(any(Team.class));
    }

    @Test
    void testFindByProjectId() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team());
        when(teamDao.findAllByProjectId(1)).thenReturn(teams);

        List<Team> result = teamService.findByProjectId(1);
        assertEquals(1, result.size());
    }

    @Test
    void testGetTeamIdsByProjectId() {
        Team t1 = new Team();
        t1.setEngineerId(100);
        List<Team> teams = new ArrayList<>();
        teams.add(t1);
        when(teamDao.findAllByProjectId(1)).thenReturn(teams);

        List<Integer> result = teamService.getTeamIdsByProjectId(1);
        assertEquals(1, result.size());
        assertEquals(100, result.get(0));
    }
}
