package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Department;


public class JdbcDepartmentDao implements DepartmentDao {

	private final String DEPARTMENT_SELECT = "SELECT d.department_id, d.name FROM department d ";

	private final JdbcTemplate jdbcTemplate;

	public JdbcDepartmentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Department getDepartmentById(int id) {

		try {
			Department department = null;
			String sql = DEPARTMENT_SELECT + " WHERE d.department_id=?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
			if (results.next()) {
				department = mapRowToDepartment(results);
			}
			return department;
		} catch (DataAccessException e) {
			throw new DaoException("Error accessing data: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Department> getDepartments() {
		try {
			List<Department> departments = new ArrayList<>();
			String sql = DEPARTMENT_SELECT; // No WHERE clause needed
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			while (results.next()) {
				departments.add(mapRowToDepartment(results));
			}
			return departments;
		} catch (DataAccessException e) {
			throw new DaoException("Error accessing data: " + e.getMessage(), e);
		}
	}

	@Override
	public Department createDepartment(Department department) {
		try {
			String sql = "INSERT INTO department (name) VALUES (?) RETURNING department_id";
			int departmentId = jdbcTemplate.queryForObject(sql, Integer.class, department.getName());
			department.setId(departmentId);
			return department;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Error creating department: " + e.getMessage(), e);
		} catch (DataAccessException e) {
			throw new DaoException("Error accessing data: " + e.getMessage(), e);
		}
	}

	@Override
	public Department updateDepartment(Department department) {
		try {
			String sql = "UPDATE department SET name = ? WHERE department_id = ?";
			jdbcTemplate.update(sql, department.getName(), department.getId());
			return department;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Error updating department: " + e.getMessage(), e);
		} catch (DataAccessException e) {
			throw new DaoException("Error accessing data: " + e.getMessage(), e);
		}

	}

	@Override
	public int deleteDepartmentById(int id) {
		try {
			// Delete references in the project_employee table
			String deleteProjectEmployeeSql = "DELETE FROM project_employee WHERE employee_id IN (SELECT employee_id FROM employee WHERE department_id = ?)";
			jdbcTemplate.update(deleteProjectEmployeeSql, id);

			// Delete employees assigned to the department
			String deleteEmployeesSql = "DELETE FROM employee WHERE department_id = ?";
			jdbcTemplate.update(deleteEmployeesSql, id);

			// Then delete the department
			String deleteDepartmentSql = "DELETE FROM department WHERE department_id = ?";
			return jdbcTemplate.update(deleteDepartmentSql, id); // Returns the number of rows affected
		} catch (DataAccessException e) {
			throw new DaoException("Error accessing data: " + e.getMessage(), e);
		}
	}

	@Override
	public int deleteDepartmentById(int id, int newDepartmentId) {
		return 0;
	}


	private Department mapRowToDepartment(SqlRowSet results) {
		Department department = new Department();
		department.setId(results.getInt("department_id"));
		department.setName(results.getString("name"));
		return department;
	}

}
