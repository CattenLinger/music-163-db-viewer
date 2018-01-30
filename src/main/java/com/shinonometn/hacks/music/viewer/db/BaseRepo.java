package com.shinonometn.hacks.music.viewer.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinonometn.hacks.music.viewer.utils.SqliteDBHelper;

import java.sql.Connection;

public abstract class BaseRepo {

    protected Connection connection;
    protected ObjectMapper objectMapper;

    public BaseRepo(SqliteDBHelper dbHelper){
        this.connection = dbHelper.getConnection();
        this.objectMapper = new ObjectMapper();
    }


}
