package com.shinonometn.hacks.music.viewer.db.netease;

import com.shinonometn.hacks.music.viewer.db.MusicRepoFactory;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public class NeteaseMusicRepoFactory implements MusicRepoFactory {

    private DriverManagerDataSource dataSource;

    public NeteaseMusicRepoFactory() {
        dataSource = new DriverManagerDataSource();
    }

    public MusicRepo build() throws Exception {

        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:db/sqlite_storage.sqlite3");

        return new NeteaseMusicRepo(dataSource);
    }
}
