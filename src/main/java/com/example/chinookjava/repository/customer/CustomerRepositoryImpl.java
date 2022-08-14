package com.example.chinookjava.repository.customer;

import com.example.chinookjava.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private String url;
	private String username;
	private String password;

	public CustomerRepositoryImpl(@Value("${spring.datasource.url}") String url,
			@Value("${spring.datasource.username}") String username,
			@Value("${spring.datasource.password}") String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public List<Customer> findAll() {
		Customer customer = null;
		List<Customer> customerList = new ArrayList<>();
		String sql = "SELECT * FROM customer ORDER BY customer_id ASC";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customer(
						resultSet.getInt("customer_id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("country"),
						resultSet.getString("postal_code"),
						resultSet.getString("phone"),
						resultSet.getString("email")
						);
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	@Override
	public List<Customer> findAll(int rowLimit, int offsetLimit) {
		Customer customer = null;
		List<Customer> customerList = new ArrayList<>();
		String sql = "SELECT * FROM customer LIMIT ? OFFSET ?";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, rowLimit);
			preparedStatement.setInt(2, offsetLimit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customer(
						resultSet.getInt("customer_id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("country"),
						resultSet.getString("postal_code"),
						resultSet.getString("phone"),
						resultSet.getString("email")
						);
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	public Customer findById(Integer id) {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE customer_id = ?";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customer(
						resultSet.getInt("customer_id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("country"),
						resultSet.getString("postal_code"),
						resultSet.getString("phone"),
						resultSet.getString("email")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Customer findByName(String name) {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE first_name LIKE ? ORDER BY customer_id ASC";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customer(
						resultSet.getInt("customer_id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("country"),
						resultSet.getString("postal_code"),
						resultSet.getString("phone"),
						resultSet.getString("email")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public void insert(Customer customer) {
		String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, customer.first_name());
			preparedStatement.setString(2, customer.last_name());
			preparedStatement.setString(3, customer.country());
			preparedStatement.setString(4, customer.postal_code());
			preparedStatement.setString(5, customer.phone());
			preparedStatement.setString(6, customer.email());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int update(Customer customer) {
		String sql = "UPDATE customer SET first_name=?, last_name=?, country=?, postal_code=?, phone=?, email=? WHERE customer_id=?";
		int rowAffected = 0;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, customer.first_name());
			preparedStatement.setString(2, customer.last_name());
			preparedStatement.setString(3, customer.country());
			preparedStatement.setString(4, customer.postal_code());
			preparedStatement.setString(5, customer.phone());
			preparedStatement.setString(6, customer.email());
			preparedStatement.setInt(7, customer.customer_id());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	@Override
	public int delete(Customer object) {
		return 0;
	}

	@Override
	public int deleteByID(Integer integer) {
		return 0;
	}
}
