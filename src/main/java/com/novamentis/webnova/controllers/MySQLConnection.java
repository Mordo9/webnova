package com.novamentis.webnova.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLConnection {
    
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection connection;
    
    // Constructor
    public MySQLConnection(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    public MySQLConnection(String database, String username, String password) {
        this("localhost", "3306", database, username, password);
    }
    
    /**
     * Establece la conexión con la base de datos MySQL
     * @return true si la conexión es exitosa, false en caso contrario
     */
    public boolean connect() {
        try {
            // URL de conexión
            String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
                                     host, port, database);
            
            // Establecer conexión
            connection = DriverManager.getConnection(url, username, password);
            
            System.out.println("Conexión establecida exitosamente con MySQL");
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Cierra la conexión con la base de datos
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si la conexión está activa
     * @return true si la conexión está activa, false en caso contrario
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Ejecuta una consulta SELECT y retorna los resultados
     * @param query Consulta SQL
     * @return Lista de mapas con los resultados
     */
    public List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        
        try {
            if (!isConnected()) {
                connect();
            }
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            // Obtener metadatos para los nombres de columnas
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Procesar resultados
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Ejecuta una consulta preparada SELECT
     * @param query Consulta SQL con parámetros (?)
     * @param parameters Parámetros para la consulta
     * @return Lista de mapas con los resultados
     */
    public List<Map<String, Object>> executeQuery(String query, Object... parameters) {
        List<Map<String, Object>> results = new ArrayList<>();
        
        try {
            if (!isConnected()) {
                connect();
            }
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Establecer parámetros
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            // Obtener metadatos
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Procesar resultados
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }
            
            rs.close();
            pstmt.close();
            
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta preparada: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Ejecuta consultas de actualización (INSERT, UPDATE, DELETE)
     * @param query Consulta SQL
     * @return Número de filas afectadas
     */
    public int executeUpdate(String query) {
        int rowsAffected = 0;
        
        try {
            if (!isConnected()) {
                connect();
            }
            
            Statement stmt = connection.createStatement();
            rowsAffected = stmt.executeUpdate(query);
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización: " + e.getMessage());
        }
        
        return rowsAffected;
    }
    
    /**
     * Ejecuta consultas de actualización preparadas
     * @param query Consulta SQL con parámetros (?)
     * @param parameters Parámetros para la consulta
     * @return Número de filas afectadas
     */
    public int executeUpdate(String query, Object... parameters) {
        int rowsAffected = 0;
        
        try {
            if (!isConnected()) {
                connect();
            }
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Establecer parámetros
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
            
            rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            System.err.println("Error al ejecutar actualización preparada: " + e.getMessage());
        }
        
        return rowsAffected;
    }
    
    /**
     * Inicia una transacción
     */
    public void beginTransaction() {
        try {
            if (!isConnected()) {
                connect();
            }
            connection.setAutoCommit(false);
            System.out.println("Transacción iniciada");
        } catch (SQLException e) {
            System.err.println("Error al iniciar transacción: " + e.getMessage());
        }
    }
    
    /**
     * Confirma la transacción
     */
    public void commit() {
        try {
            if (connection != null) {
                connection.commit();
                connection.setAutoCommit(true);
                System.out.println("Transacción confirmada");
            }
        } catch (SQLException e) {
            System.err.println("Error al confirmar transacción: " + e.getMessage());
        }
    }
    
    /**
     * Revierte la transacción
     */
    public void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("Transacción revertida");
            }
        } catch (SQLException e) {
            System.err.println("Error al revertir transacción: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene la conexión actual (para operaciones avanzadas)
     * @return Objeto Connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    // Getters y Setters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    
    public String getPort() { return port; }
    public void setPort(String port) { this.port = port; }
    
    public String getDatabase() { return database; }
    public void setDatabase(String database) { this.database = database; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public void setPassword(String password) { this.password = password; }
}
