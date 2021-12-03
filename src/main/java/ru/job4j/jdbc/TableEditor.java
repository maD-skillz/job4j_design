package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        connection = DriverManager.getConnection(url);
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            initConnection();
            Config config = new Config("settings.properties");
            config.load();
            String create = config.value("create_table");
            statement.execute(create);
            getTableScheme(connection, create);
        }
    }

    public void dropTable(String tableName) {
        Config config = new Config("settings.properties");
        config.load();
        config.value("drop_table");
    }

    public void addColumn(String tableName, String columnName, String type) {
        Config config = new Config("settings.properties");
        config.load();
        config.value("add_column");
    }

    public void dropColumn(String tableName, String columnName) {
        Config config = new Config("settings.properties");
        config.load();
        config.value("drop_column");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        Config config = new Config("settings.properties");
        config.load();
        config.value("rename_column");
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
