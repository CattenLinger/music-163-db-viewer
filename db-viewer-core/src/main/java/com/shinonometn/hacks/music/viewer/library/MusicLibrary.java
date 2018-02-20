package com.shinonometn.hacks.music.viewer.library;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;

/**
 * Created by cattenlinger on 2018/2/20.
 */
public class MusicLibrary {
    private JdbcTemplate databaseOperator;

    private final String dbPath;

    private final File libraryDataPath;

    public MusicLibrary(File dbFile, File dataPath){
        // Initialize database connection and operator
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
        dataSource.setDriverClassName("org.sqlite.JDBC");
        databaseOperator = new JdbcTemplate(dataSource);
        dbPath = dbFile.getAbsolutePath();

        // Initialize music library path
        libraryDataPath = dataPath;
        if(!libraryDataPath.isDirectory()) throw new IllegalStateException("Target is not a directory!");
    }

    public String getDbPath() {
        return dbPath;
    }

    public File getLibraryDataPath() {
        return libraryDataPath;
    }
}
