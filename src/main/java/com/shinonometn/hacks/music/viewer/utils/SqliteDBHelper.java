package com.shinonometn.hacks.music.viewer.utils;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SqliteDBHelper {

    private final static Logger logger = LoggerFactory.getLogger(SqliteDBHelper.class);

    @Getter
    private Connection connection = null;

    private SqliteDBHelper() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }

    public static SqliteDBHelper build(String filename){
        try {
            SqliteDBHelper helper = new SqliteDBHelper();

            helper.init(filename);

            return helper;
        }catch (Exception e){
            logger.error("Create connection failed.", e);
            return null;
        }
    }

    public Set<String> getTableList(){
        try{
            Statement statement;
            ResultSet resultSet;

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT tbl_name from sqlite_master");

            Set<String> strings = new HashSet<>();

            while (resultSet.next()) {
                strings.add(resultSet.getString(1));
            }

            resultSet.close();
            statement.close();

            return strings;
        }catch (Exception e){
            logger.error("Could not get the list of tables.", e);
            return new HashSet<>();
        }
    }

    /*
    *
    *
    * Private procedure
    *
    *
    * */
    private void init(String filename) throws SQLException {
        String cs = String.format("jdbc:sqlite:%s",filename);
        connection = DriverManager.getConnection(cs);
        logger.debug(String.format("Connection created successful(%s)", cs));
    }
}
