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
  private PreparedStatement preparedStatement;

  public CustomerRepositoryImpl(@Value("${spring.datasource.url}") String url,
                                @Value("${spring.datasource.username}") String username,
                                @Value("${spring.datasource.password}") String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  /* 1. Read all the customers in the database, this display their: Id, first name, last name, country, postal code,
  phone number and email.*/
  @Override
  public List<Customer> findAll() {
    Customer customer = null;
    List<Customer> customerList = new ArrayList<>();
    String sql = "SELECT * FROM customer ORDER BY customer_id ASC";
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);

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

  /* 4. Read all the customers in the database, this display their: Id, first name, last name, country, postal code,
phone number and email. Whit limit & offsets*/
  @Override
  public List<Customer> findAll(int rowLimit, int offsetLimit) {
    Customer customer = null;
    List<Customer> customerList = new ArrayList<>();
    String sql = "SELECT * FROM customer LIMIT ? OFFSET ?";
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);
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

  /* 2. Read a specific customer from the database (by Id), this display their: Id, first name, last name, country, postal code,
  phone number and email.*/
  @Override
  public Customer findById(Integer id) {
    Customer customer = null;
    String sql = "SELECT * FROM customer WHERE customer_id = ?";
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);
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

  /* 3. Read a specific customer by name.*/
  @Override
  public Customer findByName(String name) {
    Customer customer = null;
    String sql = "SELECT * FROM customer WHERE first_name LIKE ? ORDER BY customer_id ASC";
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);
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

  /* 5. Add a new customer to the database.*/
  @Override
  public void insert(Customer customer) {
    String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?)";

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);
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

  /* 6. Update an existing customer. */
  @Override
  public int update(Customer customer) {
    String sql = "UPDATE customer SET first_name = ?, " +
            "last_name = ?, " +
            "country = ?, " +
            "postal_code = ?, " +
            "phone = ?, " +
            "email = ? " +
            "WHERE customer_id = ?";
    int rowAffected = 0;
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);
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

  /* 7. Return the country with the most customers.*/
  @Override
  public String countCountry() {
    String country = "";
    String sql = "SELECT MAX(country) FROM customer";

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
      preparedStatement = conn.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        country = resultSet.getString("max");
      }
      preparedStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return country;
  }

  /* 8. Customer who is the highest spender (total in invoice table is the largest).*/
  @Override
  public Customer highestSpender() {
    Customer customer = null;
    String sql = "SELECT invoice.invoice_id, customer.first_name, invoice.total, customer.customer_id, customer.last_name, customer.country, customer.postal_code, customer.phone, customer.email" +
            "\tFROM invoice\n" +
            "\tINNER JOIN customer\n" +
            "\tON invoice.customer_id = customer.customer_id  \n" +
            "\tWHERE total = (SELECT MAX(total) FROM invoice) ORDER BY total";

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
      }
      preparedStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;

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
