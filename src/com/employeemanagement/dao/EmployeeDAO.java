package com.employeemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employeemanagement.bean.Employee;

public class EmployeeDAO {
	private String url = "jdbc:mysql://localhost:3306/harshul?useSSL=false";
	private String username = "root";
	private String password = "Harshul1@3";
	private String driver = "com.mysql.jdbc.Driver";

	private static final String INSERT_EMPLOYEE = "INSERT INTO Employees (name, salary, position) VALUES (?, ?, ?)";

	private static final String SELECT_EMPLOYEE = "SELECT id, name, salary, position FROM Employees WHERE id =?";
	private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM Employees";
	private static final String DELETE_EMPLOYEE = "DELETE FROM Employees WHERE id = ?";
	private static final String UPDATE_EMPLOYEE = "UPDATE Employees SET name = ?, salary= ?, position =? WHERE id = ?";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertEmployee(Employee employee) throws SQLException {
		System.out.println(INSERT_EMPLOYEE);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_EMPLOYEE)) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getSalary());
			preparedStatement.setString(3, employee.getPosition());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Employee selectEmployee(int id) {
		Employee employee = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SELECT_EMPLOYEE);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				int salary = Integer.parseInt(rs.getString("salary"));
				String position = rs.getString("position");
				employee = new Employee(id, name, salary, position);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> selectAllEmployees() {

		List<Employee> employees = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SELECT_ALL_EMPLOYEES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int salary = Integer.parseInt(rs.getString("salary"));
				String position = rs.getString("position");
				employees.add(new Employee(id, name, salary, position));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public boolean deleteEmployee(int id) throws SQLException {
		boolean isDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(DELETE_EMPLOYEE);) {
			statement.setInt(1, id);
			isDeleted = statement.executeUpdate() > 0;
		}
		return isDeleted;
	}

	public boolean updateEmployee(Employee employee) throws SQLException {
		boolean isUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_EMPLOYEE);) {
			System.out.println("updated USer:" + statement);
			statement.setString(1, employee.getName());
			statement.setInt(2, employee.getSalary());
			statement.setString(3, employee.getPosition());
			statement.setInt(4, employee.getId());

			isUpdated = statement.executeUpdate() > 0;
		}
		return isUpdated;
	}
}
