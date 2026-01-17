package com.xbleey.service;

import com.xbleey.dao.ProjectDao;
import com.xbleey.dao.TeamDao;
import com.xbleey.entity.Project;
import com.xbleey.entity.ProjectStatus;
import com.xbleey.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @Mock
    private ProjectDao projectDao;

    @Mock
    private TeamDao teamDao;

    @Mock
    private PmService pmService;

    @Mock
    private Model model;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllProject() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectDao.findAll()).thenReturn(projects);

        List<Project> result = projectService.findAllProject();
        assertEquals(1, result.size());
    }

    @Test
    void testFindAllByProjectStatus() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectDao.findAllByProjectStatus(ProjectStatus.DRAFT)).thenReturn(projects);

        List<Project> result = projectService.findAllByProjectStatus(ProjectStatus.DRAFT);
        assertEquals(1, result.size());
    }

    @Test
    void testGetByProjectId() {
        Project project = new Project();
        project.setId(1);
        when(projectDao.getFirstByProjectId(1)).thenReturn(project);

        Project result = projectService.getByProjectId(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetIdAndName() {
        Project p1 = new Project();
        p1.setId(1);
        p1.setProjectName("P1");
        List<Project> projects = new ArrayList<>();
        projects.add(p1);
        when(projectDao.findAll()).thenReturn(projects);

        Map<Integer, String> result = projectService.getIdAndName();
        assertEquals(1, result.size());
        assertEquals("P1", result.get(1));
    }

    @Test
    void testSaveProject() {
        Project project = new Project();
        projectService.saveProject(project);
        verify(projectDao).save(project);
    }

    @Test
    void testFindAllByEngineerId() {
        Team t1 = new Team();
        t1.setProjectId(1);
        List<Team> teams = new ArrayList<>();
        teams.add(t1);
        when(teamDao.findAllByEngineerId(100)).thenReturn(teams);

        Project p1 = new Project();
        p1.setId(1);
        when(projectDao.getFirstByProjectId(1)).thenReturn(p1);

        List<Project> result = projectService.findAllByEngineerId(100);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void testFindAllByPmId() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectDao.findAllByProjectPmId(1)).thenReturn(projects);

        List<Project> result = projectService.findAllByPmId(1);
        assertEquals(1, result.size());
    }

    @Test
    void testClassifyProjectByStatus() {
        Project p1 = new Project();
        p1.setProjectStatus(ProjectStatus.DRAFT);
        Project p2 = new Project();
        p2.setProjectStatus(ProjectStatus.BOSS_APPROVED);
        List<Project> projects = new ArrayList<>();
        projects.add(p1);
        projects.add(p2);

        List<Project> result = projectService.classifyProjectByStatus(projects, ProjectStatus.DRAFT);
        assertEquals(1, result.size());
        assertEquals(ProjectStatus.DRAFT, result.get(0).getProjectStatus());
    }

    @Test
    void testTransitionStatus() {
        Project project = new Project();
        project.setId(1);
        project.setProjectStatus(ProjectStatus.DRAFT);

        when(projectDao.findById(1)).thenReturn(Optional.of(project));
        when(projectDao.save(project)).thenReturn(project);

        Project result = projectService.transitionStatus(1, ProjectStatus.UNDER_REVIEW, null, null);
        assertEquals(ProjectStatus.UNDER_REVIEW, result.getProjectStatus());
    }

    @Test
    void testClassifyProject() {
        List<Project> sourceProjects = new ArrayList<>();
        Project p1 = new Project();
        p1.setProjectStatus(ProjectStatus.DRAFT);
        sourceProjects.add(p1);

        HashMap<String, ProjectStatus> names = new HashMap<>();
        names.put("key1", ProjectStatus.DRAFT);

        HashMap<Integer, String> pmMaps = new HashMap<>();
        when(pmService.getIdAndName()).thenReturn(pmMaps);

        projectService.classifyProject(model, sourceProjects, names);

        verify(model).addAttribute(eq("key1"), any(List.class));
        verify(model).addAttribute("pmMaps", pmMaps);
    }
}
