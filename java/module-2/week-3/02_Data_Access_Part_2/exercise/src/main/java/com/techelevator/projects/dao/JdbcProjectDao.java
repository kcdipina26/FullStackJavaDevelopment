package com.techelevator.projects.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final String PROJECT_SELECT = "SELECT p.project_id, p.name, p.from_date, p.to_date FROM project p";

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProjectById(int projectId) {
		Project project = null;
		String sql = PROJECT_SELECT +
				" WHERE p.project_id=?";
		try {
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
			if (results.next()) {
				project = mapRowToProject(results);

			}

		} catch (DataAccessException e) {
			throw new DaoException("Error accessing database: " + e.getMessage(), e);
		}

		return project;
	}

	@Override
	public List<Project> getProjects() {
		List<Project> allProjects = new ArrayList<>();
		String sql = PROJECT_SELECT;
        try {

			SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			while (results.next()) {
				Project projectResult = mapRowToProject(results);
				allProjects.add(projectResult);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Error accessing database: " + e.getMessage(), e);
		}

		return allProjects;
	}

	@Override
	public Project createProject(Project newProject) {
		String sql = "INSERT INTO project (name, from_date, to_date) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(sql, new String[]{"project_id"});
				ps.setString(1, newProject.getName());
				ps.setDate(2, newProject.getFromDate() != null ? java.sql.Date.valueOf(newProject.getFromDate()) : null);
				ps.setDate(3, newProject.getToDate() != null ? java.sql.Date.valueOf(newProject.getToDate()) : null);
				return ps;
			}, keyHolder);

			if (keyHolder.getKey() != null) {
				newProject.setId(keyHolder.getKey().intValue());
			} else {
				throw new DaoException("No key generated for the new project");
			}
		} catch (Exception e) {  // Catching a general Exception for diagnostic purposes
			throw new DaoException("Error creating new project: " + e.getMessage(), e);
		}

		return newProject;
	}

	
	@Override
	public void linkProjectEmployee(int projectId, int employeeId) {
		String sql = "INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?)";
		try {
			jdbcTemplate.update(sql, projectId, employeeId);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Unable to reach the database: " + e.getMessage(), e);
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Data integrity violation: " + e.getMessage(), e);
		}
	}

	@Override
	public void unlinkProjectEmployee(int projectId, int employeeId) {
		String sql = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?";
		try {
			jdbcTemplate.update(sql, projectId, employeeId);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Unable to reach the database: " + e.getMessage(), e);
		} catch (DataAccessException e) {
			throw new DaoException("Error unlinking project and employee: " + e.getMessage(), e);
		}
	}

	@Override
	public Project updateProject(Project project) {
		String sql = "UPDATE project SET name = ?, from_date = ?, to_date = ? WHERE project_id = ?";
		try {
			jdbcTemplate.update(sql, project.getName(), project.getFromDate(), project.getToDate(), project.getId());
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Unable to reach the database: " + e.getMessage(), e);
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Data integrity violation: " + e.getMessage(), e);
		}
		return project;
	}

	@Override
	public int deleteProjectById(int projectId) {
		// First, delete references in the project_employee table
		String deleteReferencesSql = "DELETE FROM project_employee WHERE project_id = ?";
		try {
			jdbcTemplate.update(deleteReferencesSql, projectId);

			// Then, delete the project itself
			String deleteProjectSql = "DELETE FROM project WHERE project_id = ?";
			return jdbcTemplate.update(deleteProjectSql, projectId);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Unable to reach the database: " + e.getMessage(), e);
		} catch (DataAccessException e) {
			throw new DaoException("Error deleting project: " + e.getMessage(), e);
		}
	}
	
	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getInt("project_id"));
		project.setName(results.getString("name"));
		if (results.getDate("from_date") != null) {
			project.setFromDate(results.getDate("from_date").toLocalDate());
		}
		if (results.getDate("to_date") != null) {
			project.setToDate(results.getDate("to_date").toLocalDate());
		}
		return project;
	}

}
