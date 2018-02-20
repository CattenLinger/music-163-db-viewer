package com.shinonometn.hacks.music.viewer.library;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Catten Linger on 2018/2/20.
 */
public class TrackManager {
    private JdbcTemplate dbOperator;

    public TrackManager(JdbcTemplate databaseOperator) {
        this.dbOperator = databaseOperator;
    }

    private boolean queryTableExist(){
        return false;
    }
}
