package com.poc.game.backend.infra.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Database {
    private static Database instance;
    private final String url;
    private final String username;
    private final String password;

    private Database(){
        Dotenv dotenv = Dotenv.configure().load();

        this.url = "jdbc:mysql://" + dotenv.get("DB_HOST", "localhost") + ":" + dotenv.get("DB_PORT", "3306") + "/" + dotenv.get("DB_DATABASE", "poc");
        this.username = dotenv.get("DB_USERNAME", "root");
        this.password = dotenv.get("DB_PASSWORD", "root");
    }

    public static synchronized Database getInstance(){
        if(instance == null){
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
        }
        catch (ClassNotFoundException err){
            throw new SQLException("MySQL JDBC Driver not found", err);
        }
    }

    public void closeConnection(Connection connection){
        try {
            if(connection != null){
                connection.close();
            }
        }
        catch (SQLException err){
            System.out.println("Error to close connection");
        }
    }
    public void closeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            // Trate exceções apropriadas aqui
            e.printStackTrace();
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            // Trate exceções apropriadas aqui
            e.printStackTrace();
        }
    }
}
