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
import com.techelevator.projects.model.Employee;

public class JdbcEmployeeDao implements EmployeeDao {

	private final String EMPLOYEE_SELECT = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, " +
				"e.birth_date, e.hire_date FROM employee e ";

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		String sql = EMPLOYEE_SELECT + " WHERE e.employee_id=?";

		try {
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
			if (results.next()) {
				employee = mapRowToEmployee(results);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Unable to reach the database or execute query: " + e.getMessage(), e);
		}

		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT;

		try {
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			while (results.next()) {
				Employee employeeResult = mapRowToEmployee(results);
				allEmployees.add(employeeResult);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Unable to reach the database or execute query: " + e.getMessage(), e);
		}

		return allEmployees;
	}
	@Override
	public List<Employee> getEmployeesByFirstNameLastName(String firstName, String lastName) {
		List<Employee> employees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT + " WHERE first_name = ? AND last_name = ?";

		try {
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstName, lastName);
			while (results.next()) {
				Employee employeeResult = mapRowToEmployee(results);
				employees.add(employeeResult);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Unable to reach the database or execute query: " + e.getMessage(), e);
		}

		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT +
				"JOIN project_employee pe ON e.employee_id = pe.employee_id " +
				"WHERE pe.project_id = ?";

		try {
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
			while (results.next()) {
				Employee employeeResult = mapRowToEmployee(results);
				allEmployees.add(employeeResult);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Unable to reach the database or execute query: " + e.getMessage(), e);
		}

		return allEmployees;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT +
				" WHERE e.employee_id NOT IN (SELECT DISTINCT employee_id FROM project_employee)";

		try {
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			while (results.next()) {
				Employee employeeResult = mapRowToEmployee(results);
				allEmployees.add(employeeResult);
			}
		} catch (DataAccessException e) {
			throw new DaoException("Unable to reach the database or execute query: " + e.getMessage(), e);
		}

		return allEmployees;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		String sql = "INSERT INTO employee (first_name, last_name, department_id, birth_date, hire_date) VALUES (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(sql, new String[]{"employee_id"});
				ps.setString(1, employee.getFirstName());
				ps.setString(2, employee.getLastName());
				ps.setInt(3, employee.getDepartmentId());
				ps.setDate(4, java.sql.Date.valueOf(employee.getBirthDate())); // Convert LocalDate to sql.Date
				ps.setDate(5, java.sql.Date.valueOf(employee.getHireDate()));  // Convert LocalDate to sql.Date
				return ps;
			}, keyHolder);

			if (keyHolder.getKey() != null) {
				employee.setId(keyHolder.getKey().intValue());
			} else {
				throw new DaoException("Failed to obtain new employee_id");
			}
		} catch (DataAccessException e) {
			throw new DaoException("Error creating new employee: " + e.getMessage(), e);
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// Update the SQL statement to include birth_date and hire_date
		String sql = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, birth_date = ?, hire_date = ? WHERE employee_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql,
					employee.getFirstName(),
					employee.getLastName(),
					employee.getDepartmentId(),
					employee.getBirthDate(), // Assuming getBirthDate() exists in your Employee class
					employee.getHireDate(),  // Assuming getHireDate() exists in your Employee class
					employee.getId());

			if (rowsAffected == 0) {
				throw new DaoException("No employee found with ID: " + employee.getId());
			}
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Unable to reach the database: " + e.getMessage(), e);
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Data integrity violation for employee ID " + employee.getId() + ": " + e.getMessage(), e);
		}
		return employee;
	}

	@Override
	public int deleteEmployeeById(int id) {
		// First, delete references in the project_employee table
		String deleteReferencesSql = "DELETE FROM project_employee WHERE employee_id = ?";
		try {
			jdbcTemplate.update(deleteReferencesSql, id);

			// Then, delete the employee
			String deleteEmployeeSql = "DELETE FROM employee WHERE employee_id = ?";
			int rowsAffected = jdbcTemplate.update(deleteEmployeeSql, id);
			return rowsAffected;

		} catch(CannotGetJdbcConnectionException e) {
			throw new DaoException("Unable to reach the database; " + e.getMessage(), e);
		} catch (DataAccessException e) {
			throw new DaoException("Error deleting employee: " + e.getMessage(), e);
		}
	}


	@Override
	public int deleteEmployeesByDepartmentId(int departmentId) {
		// First, delete references in the project_employee table
		String deleteReferencesSql = "DELETE FROM project_employee WHERE employee_id IN (SELECT employee_id FROM employee WHERE department_id = ?)";
		try {
			jdbcTemplate.update(deleteReferencesSql, departmentId);

			// Then, delete the employees
			String deleteEmployeeSql = "DELETE FROM employee WHERE department_id = ?";
			int rowsAffected = jdbcTemplate.update(deleteEmployeeSql, departmentId);
			if (rowsAffected == 0) {
				// Handle the case where no employees are found in the specified department
				// This might not be an error depending on your application's requirements
			}
			return rowsAffected;
		} catch (DataAccessException e) {
			throw new DaoException("Error deleting employees by department id: " + e.getMessage(), e);
		}
	}

	private Employee mapRowToEmployee(SqlRowSet result) {
		Employee employee = new Employee();
		employee.setId(result.getInt("employee_id"));
		employee.setDepartmentId(result.getInt("department_id"));
		employee.setFirstName(result.getString("first_name"));
		employee.setLastName(result.getString("last_name"));
		employee.setBirthDate(result.getDate("birth_date").toLocalDate());
		employee.setHireDate(result.getDate("hire_date").toLocalDate());
		return employee;
	}
}
