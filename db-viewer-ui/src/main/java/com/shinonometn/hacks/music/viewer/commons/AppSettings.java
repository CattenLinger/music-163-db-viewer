package com.shinonometn.hacks.music.viewer.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class AppSettings {

    public final static String FILE_NAME = "settings.json";
    public final static String DB_FILE_NAME = "database.sqlite3";

    /**
     *  The app storage database path
     * */
    private String databasePath;

    /**
     *  Custom track locations
     * */
    private List<String> outerTrackPaths;

    @JsonIgnore
    public File getTracksFolder(){
        return new File(databasePath,"tracks");
    }
}
